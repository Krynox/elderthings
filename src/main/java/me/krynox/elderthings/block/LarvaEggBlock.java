package me.krynox.elderthings.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;


public class LarvaEggBlock extends Block implements LiquidBlockContainer {
    public LarvaEggBlock() {
        super(Properties.of(Material.FROGSPAWN));
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter getter, BlockPos pos, BlockState blockState, Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor levelAccessor, BlockPos pos, BlockState blockState, FluidState fluidState) {
        return false;
    }
}
