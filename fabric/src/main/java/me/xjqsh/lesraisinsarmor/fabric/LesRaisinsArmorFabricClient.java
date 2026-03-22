package me.xjqsh.lesraisinsarmor.fabric;

import me.xjqsh.lesraisinsarmor.CommonInit;
import me.xjqsh.lesraisinsarmor.fabric.resource.ArmorRenderConfigReloadListener;
import me.xjqsh.lesraisinsarmor.fabric.network.FabricNetworkHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;

public final class LesRaisinsArmorFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CommonInit.initClient();
        FabricNetworkHandler.initClient();
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES)
                .registerReloadListener(new ArmorRenderConfigReloadListener());
    }
}
