package me.krynox.elderthings.client.localisation;

import me.krynox.elderthings.ElderThings;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

/**
 A class for providing utility methods to help with creating unlocalised names (known in the current iteration of
 forge/mc as a "Component"). Things upstream change like crazy, so it's good to have some indirection.
  */
public class LocalisationHelper {

    public static MutableComponent newUnlocName(LocalisedTextCategory cat, String name) {
        return Component.translatable(ElderThings.MODID + "." + cat.toString().toLowerCase() + "." + name);
    }
}
