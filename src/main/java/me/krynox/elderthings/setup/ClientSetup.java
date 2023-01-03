package me.krynox.elderthings.setup;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.entity.client.EggRenderer;
import me.krynox.elderthings.entity.client.PuniRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = ElderThings.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        Stream.of(Registration.SPAWNNING_POOL_FLUID, Registration.SPAWNNING_POOL_FLUID_FLOWING)
                .map(RegistryObject::get)
                .forEach(fluid -> ItemBlockRenderTypes.setRenderLayer(fluid, RenderType.translucent()));

        EntityRenderers.register(Registration.EGG_ENTITY.get(), EggRenderer::new);
        EntityRenderers.register(Registration.PUNI_ENTITY.get(), PuniRenderer::new);
    }
}
