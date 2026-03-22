package me.xjqsh.lesraisinsarmor;

import com.mojang.logging.LogUtils;
import me.xjqsh.lesraisinsarmor.config.CommonConfig;
import me.xjqsh.lesraisinsarmor.registry.ForgeRegistrar;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(ModConstants.MOD_ID)
public class LesRaisinsArmor {
    public static final Logger LOGGER = LogUtils.getLogger();

    public LesRaisinsArmor(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.init());
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ForgeRegistrar.register(bus);
        bus.addListener(this::onClientSetup);

        CommonInit.init();
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            CommonInit.initClient();
        });
    }
}
