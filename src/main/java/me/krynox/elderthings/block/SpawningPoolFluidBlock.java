package me.krynox.elderthings.block;

import me.krynox.elderthings.setup.Registration;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Material;

public class SpawningPoolFluidBlock extends LiquidBlock {
    public SpawningPoolFluidBlock() {
        super(Registration.SPAWNNING_POOL_FLUID, Properties.of(Material.WATER));
    }
}
