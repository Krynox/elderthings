package me.krynox.elderthings.setup;

import me.krynox.elderthings.ElderThings;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = ElderThings.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {

    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent e) {

    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent e) {
        e.put(Registration.EGG_ENTITY.get(), LivingEntity.createLivingAttributes().build());
        e.put(Registration.PUNI_ENTITY.get(), Mob.createMobAttributes().build());
    }
}
