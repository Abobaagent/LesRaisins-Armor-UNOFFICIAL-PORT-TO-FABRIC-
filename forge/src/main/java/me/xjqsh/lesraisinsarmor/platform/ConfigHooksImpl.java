package me.xjqsh.lesraisinsarmor.platform;

import me.xjqsh.lesraisinsarmor.config.CommonConfig;

public final class ConfigHooksImpl {
    private ConfigHooksImpl() {
    }

    public static boolean enableArmorAttribute() {
        return CommonConfig.enableArmorAttribute.get();
    }

    public static boolean enableArmorSetEffect() {
        return CommonConfig.enableArmorSetEffect.get();
    }
}
