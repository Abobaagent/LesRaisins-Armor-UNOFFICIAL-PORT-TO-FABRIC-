package me.xjqsh.lesraisinsarmor.fabric.registry;

import me.xjqsh.lesraisinsarmor.ModConstants;
import me.xjqsh.lesraisinsarmor.init.ModCreativeTabs;
import me.xjqsh.lesraisinsarmor.init.ModEffects;
import me.xjqsh.lesraisinsarmor.init.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public final class FabricRegistrar {
    private FabricRegistrar() {
    }

    public static void register() {
        ModEffects.registerAll((id, supplier) -> Registry.register(
                BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation(ModConstants.MOD_ID, id),
                supplier.get()
        ));

        ModItems.registerAll((id, supplier) -> Registry.register(
                BuiltInRegistries.ITEM,
                new ResourceLocation(ModConstants.MOD_ID, id),
                supplier.get()
        ));

        CreativeModeTab tab = FabricItemGroup.builder()
                .title(ModCreativeTabs.title())
                .icon(ModCreativeTabs::icon)
                .displayItems((context, output) -> ModCreativeTabs.fill(output))
                .build();

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ModCreativeTabs.id(), tab);
    }
}
