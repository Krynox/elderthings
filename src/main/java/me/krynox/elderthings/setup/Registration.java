package me.krynox.elderthings.setup;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.block.MysteriousCeramicBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ElderThings.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ElderThings.MODID);

    public static void registerAll(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }

    public static final BlockBehaviour.Properties DEFAULT_BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();
    public static final Item.Properties DEFAULT_ITEM_PROPERTIES = new Item.Properties();

    // Convenience function: Take a RegistryObject<Block> and make a corresponding RegistryObject<Item> from it
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), DEFAULT_ITEM_PROPERTIES));
    }

    /////////////////////////////////////
    ///// Registration Starts Here //////
    /////////////////////////////////////

    public static final RegistryObject<Block> MYSTERIOUS_CERAMIC
            = BLOCKS.register("mysterious_ceramic", MysteriousCeramicBlock::new);
    public static final RegistryObject<Item> MYSTERIOUS_CERAMIC_ITEM = fromBlock(MYSTERIOUS_CERAMIC);
}
