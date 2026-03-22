package me.xjqsh.lesraisinsarmor.platform;

import me.xjqsh.lesraisinsarmor.item.ForgeLrArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ForgePlatformServices implements PlatformServices {
    @Override
    public Item createArmorItem(String suitIdf, ArmorItem.Type slot, Item.Properties properties, @Nullable Supplier<MobEffect> suitEffect) {
        return new ForgeLrArmorItem(suitIdf, slot, properties, suitEffect);
    }

    @Override
    public Player getClientPlayer() {
        return ClientHooksImpl.getClientPlayer();
    }

    @Override
    public boolean enableArmorAttribute() {
        return ConfigHooksImpl.enableArmorAttribute();
    }

    @Override
    public boolean enableArmorSetEffect() {
        return ConfigHooksImpl.enableArmorSetEffect();
    }

    @Override
    public Ingredient ingredientFromJson(JsonElement json) {
        return IngredientHooksImpl.ingredientFromJson(json);
    }
}
