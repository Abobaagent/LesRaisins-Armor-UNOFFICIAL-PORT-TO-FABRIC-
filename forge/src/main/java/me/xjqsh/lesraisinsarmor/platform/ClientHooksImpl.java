package me.xjqsh.lesraisinsarmor.platform;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public final class ClientHooksImpl {
    private ClientHooksImpl() {
    }

    public static Player getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
