package me.krynox.elderthings.common;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.client.localisation.LocalisationHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static me.krynox.elderthings.client.localisation.LocalisedTextCategory.CREATIVE_TAB;
import static me.krynox.elderthings.setup.Registration.EXAMPLE_BLOCK_ITEM;

@Mod.EventBusSubscriber(modid = ElderThings.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTab {
    @SubscribeEvent
    public static void registerCreativeTab(CreativeModeTabEvent.Register e) {
        //todo - creative tab isn't appearing, maybe this event handler is improperly registered, maybe it's just not doing the right thing

        e.registerCreativeModeTab(ElderThings.newResourceLocation("creativetab"), builder -> builder
                .icon(() -> new ItemStack(Items.ACACIA_BOAT))
                .title(LocalisationHelper.newUnlocName(CREATIVE_TAB, "creativetab"))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(ModItems.EXAMPLE_BLOCK);
                }));
    }

}
