package me.krynox.elderthings.block;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.setup.Registration;
import me.krynox.elderthings.util.LevelUtil;
import me.krynox.elderthings.util.One;
import me.krynox.elderthings.util.TriResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;

public class MysteriousCeramicBlock extends Block {
    public MysteriousCeramicBlock() {
        super(Properties.of(Material.STONE));
    }


    /**
     * Returns empty if invalid, or the number of water blocks in the line if valid.
     * 0 is a valid number of water blocks. This just means we're on an edge of the pool.
     */
    private static Optional<Integer> isWaterLine(Level level, BlockPos pos, Direction dir) {

        BiFunction<Level, BlockPos, Integer> f = (l, p) -> {
            BlockState b = level.getBlockState(p);

            return b.is(Blocks.WATER) ? 1 : 0; //need to have this check, because it will be called for the final ceramic too
        };

        BiFunction<Level, BlockPos, TriResult> shouldStop = (l, p) -> {
            BlockState b = level.getBlockState(p);
            BlockState left = level.getBlockState(p.relative(dir.getCounterClockWise()));
            BlockState right = level.getBlockState(p.relative(dir.getClockWise()));

            if(!left.is(Registration.MYSTERIOUS_CERAMIC.get()) & !left.is(Blocks.WATER)) {
                return TriResult.STOP_FAIL;
            }

            if(!right.is(Registration.MYSTERIOUS_CERAMIC.get()) & !right.is(Blocks.WATER)) {
                return TriResult.STOP_FAIL;
            }

            if(b.is(Blocks.WATER)) {
                return TriResult.CONTINUE;
            } else if(b.is(Registration.MYSTERIOUS_CERAMIC.get())) {
                return TriResult.STOP_PASS;
            } else {
                return TriResult.STOP_FAIL;
            }
        };

        return LevelUtil.forEachBlockInLine(f, shouldStop, 20, Integer::sum, level, pos, dir);
    }

    //NB rootpos is the first water, not the clicked ceramic.
    private static boolean validShape(Level level, BlockPos rootPos, Direction dir) {
        if(dir == Direction.UP | dir == Direction.DOWN) {
            ElderThings.LOGGER.error("Tried to check invalid direction when spawning birthing pool.");
            return false;
        }

        // Block at rootPos needs to be water.
        if(level.getBlockState(rootPos).is(Blocks.WATER)) {
            Optional<Integer> result = isWaterLine(level, rootPos, dir);

            if(result.isPresent()) {
                for(int j = 0; j <= result.get(); j++) {
                    Optional<Integer> rCW = isWaterLine(level, rootPos.relative(dir, j), dir.getClockWise());
                    if(rCW.isEmpty()) return false;

                    Optional<Integer> rCCW = isWaterLine(level, rootPos.relative(dir, j), dir.getCounterClockWise());
                    if(rCCW.isEmpty()) return false;
                }

                return true;
            }
        }

        return false;
    }


    private static ImmutablePair<Boolean, Direction> validShape(Level level, BlockPos pos) {
        boolean northValid = validShape(level, pos.relative(Direction.NORTH), Direction.NORTH);
        if(northValid) {
            ElderThings.LOGGER.debug("North valid");
            return new ImmutablePair<>(true, Direction.NORTH);
        }

        boolean eastValid  = validShape(level, pos.relative(Direction.EAST), Direction.EAST);
        if(eastValid) {
            ElderThings.LOGGER.debug("East valid");
            return new ImmutablePair<>(true, Direction.EAST);
        }

        boolean southValid = validShape(level, pos.relative(Direction.SOUTH), Direction.SOUTH);
        if(southValid) {
            ElderThings.LOGGER.debug("South valid");
            return new ImmutablePair<>(true, Direction.SOUTH);
        }

        boolean westValid  = validShape(level, pos.relative(Direction.WEST), Direction.WEST);
        if(westValid) {
            ElderThings.LOGGER.debug("West valid");
            return new ImmutablePair<>(true, Direction.WEST);
        } else {
            return new ImmutablePair<>(false, null);
        }
    }

    private static void replaceWaterLine(Level level, BlockPos pos, Direction dir, BlockState newBlock) {

        // So this is funny, this function only exists for side effects, so in java terms ought to be a
        // "bi-consumer". But void is a special snowflake, so had to roll my own unit type.
        BiFunction<Level, BlockPos, One> f = (l, p) -> {
            if(level.getBlockState(p).is(Blocks.WATER)) level.setBlockAndUpdate(p, newBlock);
            return One.TT;
        };

        BiFunction<Level, BlockPos, TriResult> shouldStop = (l, p) -> {
            BlockState b = level.getBlockState(p);

            if(b.is(Blocks.WATER) | b == newBlock) {
                return TriResult.CONTINUE;
            } else if(b.is(Registration.MYSTERIOUS_CERAMIC.get())) {
                return TriResult.STOP_PASS;
            } else {
                return TriResult.STOP_FAIL;
            }
        };

        LevelUtil.forEachBlockInLine(f, shouldStop, 20, (a,b) -> One.TT , level, pos, dir);

    }

    private static void replaceWater(Level level, BlockPos pos, Direction dir, BlockState newBlock) {
        if(dir == Direction.UP | dir == Direction.DOWN) {
            ElderThings.LOGGER.error("Tried to check invalid direction when spawning birthing pool.");
            return;
        }

        // Block at rootPos needs to be water.
        if(level.getBlockState(pos).is(Blocks.WATER)) {
            Optional<Integer> result = isWaterLine(level, pos, dir);

            level.setBlockAndUpdate(pos, newBlock);

            if(result.isPresent()) {
                replaceWaterLine(level, pos, dir, newBlock);

                for(int j = 0; j <= result.get(); j++) {
                    replaceWaterLine(level, pos.relative(dir,j), dir.getClockWise(), newBlock);
                    replaceWaterLine(level, pos.relative(dir,j), dir.getCounterClockWise(), newBlock);
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult res) {
        if(!level.isClientSide & player.getItemInHand(hand).is(Items.CHORUS_FRUIT)) {
            ImmutablePair<Boolean, Direction> r = validShape(level, pos);
            if(r.left) replaceWater(level, pos.relative(r.right), r.right, Blocks.DIAMOND_BLOCK.defaultBlockState());
        }
        return InteractionResult.PASS;
    }
    /*
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult res) {
        if (!level.isClientSide & player.getItemInHand(player.getUsedItemHand()).is(Items.CHORUS_FRUIT) ){
            ImmutablePair<Boolean, Direction> result = validShape(level, pos);
            if(result.left) {
                ElderThings.LOGGER.info("Spawning birthing pool.");
                replaceWater(level, pos, result.right);
                return InteractionResult.SUCCESS;
            } else {
                ElderThings.LOGGER.info("Birthing pool structure ill-formed, not spawned.");
                return InteractionResult.FAIL;
            }
        }

        return InteractionResult.PASS;
    }

     */
}
