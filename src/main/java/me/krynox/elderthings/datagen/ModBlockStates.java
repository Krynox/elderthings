package me.krynox.elderthings.datagen;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.setup.Registration;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStates extends BlockStateProvider {
    public ModBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ElderThings.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.MYSTERIOUS_CERAMIC_BLOCK.get());

        //todo - clean up later. this was just to get it working asap
        ModelFile fluidModel = models().getBuilder(Registration.SPAWNING_POOL_FLUID_BLOCK.getId().getPath()).texture("particle", mcLoc("block/water_still"));
        getVariantBuilder(Registration.SPAWNING_POOL_FLUID_BLOCK.get()).partialState().setModels(new ConfiguredModel(fluidModel));
    }
}
