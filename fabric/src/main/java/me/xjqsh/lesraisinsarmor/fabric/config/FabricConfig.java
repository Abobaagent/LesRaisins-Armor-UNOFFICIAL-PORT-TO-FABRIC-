package me.xjqsh.lesraisinsarmor.fabric.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FabricConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "lrarmor.json";

    public static boolean enableArmorSetEffect = true;
    public static boolean enableArmorAttribute = true;

    private FabricConfig() {
    }

    public static void load() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);
        if (!Files.exists(path)) {
            save(path);
            return;
        }
        try {
            String json = Files.readString(path, StandardCharsets.UTF_8);
            Data data = GSON.fromJson(json, Data.class);
            if (data != null) {
                enableArmorSetEffect = data.enableArmorSetEffect;
                enableArmorAttribute = data.enableArmorAttribute;
            }
        } catch (IOException e) {
            // If config fails to load, keep defaults.
        }
    }

    private static void save(Path path) {
        Data data = new Data();
        data.enableArmorSetEffect = enableArmorSetEffect;
        data.enableArmorAttribute = enableArmorAttribute;
        try {
            Files.writeString(path, GSON.toJson(data), StandardCharsets.UTF_8);
        } catch (IOException e) {
            // Ignore write failures.
        }
    }

    private static final class Data {
        boolean enableArmorSetEffect = true;
        boolean enableArmorAttribute = true;
    }
}
