package me.krynox.elderthings.datagen;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.setup.Registration;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {
    public ModItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ElderThings.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.MYSTERIOUS_CERAMIC_ITEM.getId().getPath(), modLoc("block/mysterious_ceramic"));
        withExistingParent(Registration.SPAWNING_POOL_FLUID_BUCKET.getId().getPath(), mcLoc("item/bucket"));
    }
}
