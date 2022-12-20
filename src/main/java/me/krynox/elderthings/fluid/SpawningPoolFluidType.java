package me.krynox.elderthings.fluid;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.vertex.PoseStack;
import me.krynox.elderthings.ElderThings;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

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
                   STILL = new ResourceLocation("block/water_still"),
                   FLOW = new ResourceLocation("minecraft", "block/water_flow");

           @Override
           public int getTintColor() {
               return 0xe0408000;
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
