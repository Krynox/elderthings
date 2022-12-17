package me.krynox.elderthings.datagen;

import me.krynox.elderthings.ElderThings;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, ElderThings.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

    }
}
