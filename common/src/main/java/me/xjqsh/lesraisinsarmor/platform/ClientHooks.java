package me.xjqsh.lesraisinsarmor.platform;

import net.minecraft.world.entity.player.Player;

public final class ClientHooks {
    private ClientHooks() {
    }

    public static Player getClientPlayer() {
        return Services.platform().getClientPlayer();
    }
}
