package me.xjqsh.lesraisinsarmor.platform;

public final class ConfigHooks {
    private ConfigHooks() {
    }

    public static boolean enableArmorAttribute() {
        return Services.platform().enableArmorAttribute();
    }

    public static boolean enableArmorSetEffect() {
        return Services.platform().enableArmorSetEffect();
    }
}
