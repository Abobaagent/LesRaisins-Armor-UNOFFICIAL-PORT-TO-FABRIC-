package me.xjqsh.lesraisinsarmor.platform;

import me.xjqsh.lesraisinsarmor.fabric.config.FabricConfig;

public final class ConfigHooksImpl {
    private ConfigHooksImpl() {
    }

    public static boolean enableArmorAttribute() {
        return FabricConfig.enableArmorAttribute;
    }

    public static boolean enableArmorSetEffect() {
        return FabricConfig.enableArmorSetEffect;
    }
}
