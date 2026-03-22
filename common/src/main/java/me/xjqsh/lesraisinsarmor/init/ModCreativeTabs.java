package me.xjqsh.lesraisinsarmor.init;

import me.xjqsh.lesraisinsarmor.ModConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class ModCreativeTabs {
    public static final String MAIN_ID = "other";

    private ModCreativeTabs() {
    }

    public static ResourceLocation id() {
        return new ResourceLocation(ModConstants.MOD_ID, MAIN_ID);
    }

    public static Component title() {
        return Component.translatable("itemGroup." + ModConstants.MOD_ID);
    }

    public static ItemStack icon() {
        Item item = ModItems.get("chemical_protective_chestplate");
        if (item == Items.AIR) {
            return Items.IRON_CHESTPLATE.getDefaultInstance();
        }
        return item.getDefaultInstance();
    }

    public static void fill(CreativeModeTab.Output output) {
        for (Item item : ModItems.all()) {
            output.accept(item);
        }
    }
}
