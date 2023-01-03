package me.krynox.elderthings.setup;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.block.MysteriousCeramicBlock;
import me.krynox.elderthings.entity.EggEntity;
import me.krynox.elderthings.entity.PuniEntity;
import me.krynox.elderthings.fluid.SpawningPoolFluid;
import me.krynox.elderthings.fluid.SpawningPoolFluidType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ElderThings.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ElderThings.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ElderThings.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ElderThings.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ElderThings.MODID);

    public static void registerAll(IEventBus bus) {
        FLUID_TYPES.register(bus);
        FLUIDS.register(bus);
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ENTITIES.register(bus);
    }

    public static final BlockBehaviour.Properties DEFAULT_BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();
    public static final Item.Properties DEFAULT_ITEM_PROPERTIES = new Item.Properties();

    //////////////////////////
    //// HELPER FUNCTIONS ////
    //////////////////////////

    /**
     * Create a default BlockItem for a given Block.
     */
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), DEFAULT_ITEM_PROPERTIES));
    }

    /**
     * Create a default BucketItem for a given Fluid.
     */
    public static <F extends Fluid> RegistryObject<Item> bucketFor(RegistryObject<F> fluid) {
        return ITEMS.register(fluid.getId().getPath() + "_bucket", ()
                -> new BucketItem(SPAWNNING_POOL_FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    }

    /////////////////////////////////////////////
    ///////// Registration Starts Here //////////

    ////////////////////////////////////////////////////////////
    //// FLUIDS, FLUID_TYPES, FLUID_BLOCKS, + RELATED STUFF ////
    ////////////////////////////////////////////////////////////

    public static final RegistryObject<FluidType> SPAWNING_POOL_FLUID_TYPE
            = FLUID_TYPES.register("spawning_pool_fluid", SpawningPoolFluidType::new);
    public static final RegistryObject<FlowingFluid> SPAWNNING_POOL_FLUID
            = FLUIDS.register("spawning_pool_fluid", SpawningPoolFluid.Source::new);
    public static final RegistryObject<FlowingFluid> SPAWNNING_POOL_FLUID_FLOWING
            = FLUIDS.register("spawning_pool_fluid_flowing", SpawningPoolFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> SPAWNING_POOL_FLUID_BLOCK
            = BLOCKS.register("spawning_pool_fluid", () ->
                new LiquidBlock(SPAWNNING_POOL_FLUID, BlockBehaviour.Properties.of(Material.WATER)));
    public static final RegistryObject<Item> SPAWNING_POOL_FLUID_BUCKET
            = bucketFor(SPAWNNING_POOL_FLUID);


    ///////////////
    //// ITEMS ////
    ///////////////



    //////////////////////////////
    //// BLOCKS & BLOCK_ITEMS ////
    //////////////////////////////

    public static final RegistryObject<Block> MYSTERIOUS_CERAMIC_BLOCK
            = BLOCKS.register("mysterious_ceramic", MysteriousCeramicBlock::new);
    public static final RegistryObject<Item> MYSTERIOUS_CERAMIC_ITEM
            = fromBlock(MYSTERIOUS_CERAMIC_BLOCK);


    //////////////////
    //// ENTITIES ////
    //////////////////

    // NB: Don't forget to also register EntityAttributes in CommonSetup for LivingEntities.

    public static final RegistryObject<EntityType<EggEntity>> EGG_ENTITY
            = ENTITIES.register("eldritch_egg", () -> EntityType.Builder.of(EggEntity::new, MobCategory.MISC)
            //todo - check out other available properties
            .sized(1f,1f)
            .build("egg"));

    public static final RegistryObject<EntityType<PuniEntity>> PUNI_ENTITY
            = ENTITIES.register("puni", () -> EntityType.Builder.of(PuniEntity::new, MobCategory.MISC)
            .sized(1f,1f)
            .build("puni"));
    //public static final RegistryObject<Item> EGG_EGG = ITEMS.register ("eldritch_egg", new ForgeSpawnEggItem(EGG_ENTITY, 0xff0000, 0x00ff00, DEFAULT_ITEM_PROPERTIES));

}
