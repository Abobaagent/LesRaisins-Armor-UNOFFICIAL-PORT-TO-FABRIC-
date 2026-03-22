package me.xjqsh.lesraisinsarmor.platform;

import com.google.gson.JsonElement;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CraftingHelper;

public final class IngredientHooksImpl {
    private IngredientHooksImpl() {
    }

    public static Ingredient ingredientFromJson(JsonElement json) {
        return CraftingHelper.getIngredient(json, true);
    }
}
