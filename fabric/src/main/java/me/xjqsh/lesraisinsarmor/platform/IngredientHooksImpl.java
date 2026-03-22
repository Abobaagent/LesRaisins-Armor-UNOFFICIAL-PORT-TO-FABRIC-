package me.xjqsh.lesraisinsarmor.platform;

import com.google.gson.JsonElement;
import net.minecraft.world.item.crafting.Ingredient;

public final class IngredientHooksImpl {
    private IngredientHooksImpl() {
    }

    public static Ingredient ingredientFromJson(JsonElement json) {
        return Ingredient.fromJson(json);
    }
}
