package me.xjqsh.lesraisinsarmor.fabric.network;

import me.xjqsh.lesraisinsarmor.ModConstants;
import me.xjqsh.lesraisinsarmor.resource.ArmorDataManager;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;

public final class FabricNetworkHandler {
    public static final ResourceLocation ARMOR_DATA_SYNC = new ResourceLocation(ModConstants.MOD_ID, "armor_data_sync");

    private FabricNetworkHandler() {
    }

    public static void initClient() {
        ClientPlayNetworking.registerGlobalReceiver(ARMOR_DATA_SYNC, (client, handler, buf, responseSender) -> {
            Map<ResourceLocation, String> data = readMap(buf);
            client.execute(() -> ArmorDataManager.fromNetwork(data));
        });
    }

    public static void sendArmorData(ServerPlayer player) {
        if (player == null) return;
        Map<ResourceLocation, String> data = ArmorDataManager.getInstance().getNetworkCache();
        if (data == null) return;
        FriendlyByteBuf buf = PacketByteBufs.create();
        writeMap(buf, data);
        ServerPlayNetworking.send(player, ARMOR_DATA_SYNC, buf);
    }

    public static void broadcastArmorData(MinecraftServer server) {
        if (server == null) return;
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            sendArmorData(player);
        }
    }

    private static void writeMap(FriendlyByteBuf buf, Map<ResourceLocation, String> map) {
        buf.writeVarInt(map.size());
        for (var entry : map.entrySet()) {
            buf.writeResourceLocation(entry.getKey());
            buf.writeUtf(entry.getValue());
        }
    }

    private static Map<ResourceLocation, String> readMap(FriendlyByteBuf buf) {
        int size = buf.readVarInt();
        Map<ResourceLocation, String> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            ResourceLocation key = buf.readResourceLocation();
            String value = buf.readUtf();
            map.put(key, value);
        }
        return map;
    }
}
