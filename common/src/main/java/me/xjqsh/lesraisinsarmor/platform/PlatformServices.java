package me.xjqsh.lesraisinsarmor.platform;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public interface PlatformServices {
    Item createArmorItem(String suitIdf, ArmorItem.Type slot, Item.Properties properties, @Nullable Supplier<MobEffect> suitEffect);

    Player getClientPlayer();

    boolean enableArmorAttribute();

    boolean enableArmorSetEffect();

    Ingredient ingredientFromJson(JsonElement json);
}
