package me.xjqsh.lesraisinsarmor.compat;

import me.xjqsh.lesraisinsarmor.LesRaisinsArmor;
import net.minecraftforge.fml.loading.FMLEnvironment;

public final class EpicFightCompat {
    private EpicFightCompat() {
    }

    public static void init() {
        if (FMLEnvironment.dist.isClient()) {
            LesRaisinsArmor.LOGGER.info("EpicFight compat disabled: optional dependency not on compile classpath.");
        }
    }
}
