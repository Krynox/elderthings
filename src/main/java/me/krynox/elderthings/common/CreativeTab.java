package me.krynox.elderthings.common;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.client.localisation.LocalisationHelper;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.krynox.elderthings.client.localisation.LocalisedTextCategory.CREATIVE_TAB;
import static me.krynox.elderthings.setup.Registration.EXAMPLE_BLOCK_ITEM;

@Mod.EventBusSubscriber(modid = ElderThings.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CreativeTab {
    @SubscribeEvent
    public static void registerCreativeTab(CreativeModeTabEvent.Register e) {
        //todo - creative tab isn't appearing, maybe this event handler is improperly registered, maybe it's just not doing the right thing
        ElderThings.LOGGER.debug("ElderThings handling CreativeModeTabEvent.Register");

        e.registerCreativeModeTab(ElderThings.newResourceLocation("elderthings_creativetab"), builder -> builder
                .icon(() -> new ItemStack(Items.ACACIA_BOAT))
                .title(LocalisationHelper.newUnlocName(CREATIVE_TAB, "elderthings_tab")));
    }

    @SubscribeEvent
    public static void populateCreativeTab(CreativeModeTabEvent.BuildContents e) {
        if (e.getTab() == CreativeModeTabs.BUILDING_BLOCKS)
            e.accept(EXAMPLE_BLOCK_ITEM);

    }
}
