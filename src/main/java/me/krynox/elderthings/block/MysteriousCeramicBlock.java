package me.krynox.elderthings.block;

import me.krynox.elderthings.ElderThings;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class MysteriousCeramicBlock extends Block {
    public MysteriousCeramicBlock() {
        super(Properties.of(Material.STONE));
    }


    private static boolean validShape(Level level, BlockPos pos) {
        return true;
    }



    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult res) {
        if (!level.isClientSide & player.getItemInHand(player.getUsedItemHand()).is(Items.CHORUS_FRUIT) & validShape(level, pos)){
            ElderThings.LOGGER.info("Spawning birthing pool.");
        }
        //if the player is holding the right item
        //and the shape is correct
        //then fill it with birthing pool fluid

        return InteractionResult.SUCCESS;
    }
}
