package me.krynox.elderthings.datagen;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.setup.Registration;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnglishLanguageProvider extends LanguageProvider {

    private final boolean AMERICAN;

    public ModEnglishLanguageProvider(PackOutput output, String locale) {
        super(output, ElderThings.MODID, locale);

        AMERICAN = locale.equals("en_us");
    }

    @Override
    protected void addTranslations() {
        add("elderthings.creative_tab.creativetab", "Elder Things");

        add(Registration.MYSTERIOUS_CERAMIC_BLOCK.get(), "Mysterious Ceramic");
        add(Registration.SPAWNING_POOL_FLUID_BLOCK.get(), "Spawning Pool Fluid");
        add(Registration.SPAWNING_POOL_FLUID_BUCKET.get(), "Bucket of Spawning Pool Fluid");

        if(AMERICAN) {
            // American spellings here
        } else {
            // British/Aussie/Canuck spellings here
        }
    }
}
