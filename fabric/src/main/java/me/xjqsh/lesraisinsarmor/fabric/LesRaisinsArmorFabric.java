package me.xjqsh.lesraisinsarmor.fabric;

import me.xjqsh.lesraisinsarmor.CommonInit;
import me.xjqsh.lesraisinsarmor.fabric.config.FabricConfig;
import me.xjqsh.lesraisinsarmor.fabric.handler.FabricSuitHandler;
import me.xjqsh.lesraisinsarmor.fabric.network.FabricNetworkHandler;
import me.xjqsh.lesraisinsarmor.fabric.resource.ArmorDataReloadListener;
import me.xjqsh.lesraisinsarmor.fabric.registry.FabricRegistrar;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;

public final class LesRaisinsArmorFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FabricRegistrar.register();
        CommonInit.init();
        FabricConfig.load();
        FabricSuitHandler.register();

        ResourceManagerHelper.get(PackType.SERVER_DATA)
                .registerReloadListener(new ArmorDataReloadListener());

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
                FabricNetworkHandler.sendArmorData(handler.player));

        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {
            if (success) {
                FabricNetworkHandler.broadcastArmorData(server);
            }
        });
    }
}
