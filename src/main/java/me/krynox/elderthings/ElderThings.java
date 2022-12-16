package me.krynox.elderthings;

import com.mojang.logging.LogUtils;
import me.krynox.elderthings.setup.Registration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ElderThings.MODID)
public class ElderThings
{
    public static final String MODID = "elderthings";
    public static final Logger LOGGER = LogUtils.getLogger();


    public ElderThings()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.registerAll(modEventBus);
    }

    public static ResourceLocation newResourceLocation(String name) {
        return new ResourceLocation(MODID, name);
    }
}
