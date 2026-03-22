package me.xjqsh.lesraisinsarmor.platform;

import com.google.gson.JsonElement;
import net.minecraft.world.item.crafting.Ingredient;

public final class IngredientHooks {
    private IngredientHooks() {
    }

    public static Ingredient ingredientFromJson(JsonElement json) {
        return Services.platform().ingredientFromJson(json);
    }
}
