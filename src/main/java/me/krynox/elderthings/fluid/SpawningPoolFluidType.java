package me.krynox.elderthings.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;

import java.util.function.Consumer;

public class SpawningPoolFluidType extends FluidType {

    public static final Properties PROPERTIES = Properties
            .create()
            .canConvertToSource(false)
            .canSwim(true)
            .canDrown(false)
            .supportsBoating(false);

    public SpawningPoolFluidType() {
        super(PROPERTIES);
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
       consumer.accept(new IClientFluidTypeExtensions() {
           private static final ResourceLocation
                   STILL = new ResourceLocation("minecraft", "block/water_still"),
                   FLOW = new ResourceLocation("minecraft", "block/water_flow");

           @Override
           public int getTintColor() {
               return 0xff9eff3d;
           }

           @Override
           public ResourceLocation getStillTexture() {
               return STILL;
           }

           @Override
           public ResourceLocation getFlowingTexture() {
               return FLOW;
           }

       });


    }
}
