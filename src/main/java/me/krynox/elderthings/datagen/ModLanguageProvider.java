package me.krynox.elderthings.datagen;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.setup.Registration;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, ElderThings.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(Registration.MYSTERIOUS_CERAMIC.get(), "Mysterious Ceramic");
    }
}
