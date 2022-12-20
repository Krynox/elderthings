package me.krynox.elderthings.fluid;

import me.krynox.elderthings.setup.Registration;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class SpawningPoolFluid extends ForgeFlowingFluid {

    private static final Properties PROPERTIES
            = new Properties(Registration.SPAWNING_POOL_FLUID_TYPE, Registration.SPAWNNING_POOL_FLUID, Registration.SPAWNNING_POOL_FLUID_FLOWING)
            .block(Registration.SPAWNING_POOL_FLUID_BLOCK)
            .bucket(Registration.SPAWNING_POOL_FLUID_BUCKET);

    protected SpawningPoolFluid() {
        super(PROPERTIES);
    }

    public static class Source extends ForgeFlowingFluid.Source {
        public Source() {
            super(PROPERTIES);
        }
    }

    public static class Flowing extends ForgeFlowingFluid.Flowing {
        public Flowing() {
            super(PROPERTIES);
        }
    }
}
