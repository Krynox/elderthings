package me.krynox.elderthings.setup;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.localisation.LocalisationHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.krynox.elderthings.localisation.LocalisedTextCategory.CREATIVE_TAB;

@Mod.EventBusSubscriber(modid = ElderThings.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTab {
    @SubscribeEvent
    public static void registerCreativeTab(CreativeModeTabEvent.Register e) {
        //todo - creative tab isn't appearing, maybe this event handler is improperly registered, maybe it's just not doing the right thing

        e.registerCreativeModeTab(ElderThings.resLoc("creativetab"), builder -> builder
                .icon(() -> new ItemStack(Items.ACACIA_BOAT))
                .title(LocalisationHelper.newUnlocName(CREATIVE_TAB, "creativetab"))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(Registration.MYSTERIOUS_CERAMIC_BLOCK.get());
                    output.accept(Registration.SPAWNING_POOL_FLUID_BUCKET.get());
                }));
    }

}
