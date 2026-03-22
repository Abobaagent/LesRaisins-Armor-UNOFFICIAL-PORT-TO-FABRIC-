package me.xjqsh.lesraisinsarmor.platform;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

@Environment(EnvType.CLIENT)
public final class ClientHooksImpl {
    private ClientHooksImpl() {
    }

    public static Player getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
