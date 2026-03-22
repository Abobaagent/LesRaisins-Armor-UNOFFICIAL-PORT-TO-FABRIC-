package me.xjqsh.lesraisinsarmor.registry;

import me.xjqsh.lesraisinsarmor.ModConstants;
import me.xjqsh.lesraisinsarmor.init.ModCreativeTabs;
import me.xjqsh.lesraisinsarmor.init.ModEffects;
import me.xjqsh.lesraisinsarmor.init.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ForgeRegistrar {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModConstants.MOD_ID);
    private static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ModConstants.MOD_ID);
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModConstants.MOD_ID);

    static {
        ModItems.registerAll((id, supplier) -> ITEMS.register(id, supplier));
        ModEffects.registerAll((id, supplier) -> EFFECTS.register(id, supplier));
        TABS.register(ModCreativeTabs.MAIN_ID, () -> CreativeModeTab.builder()
                .title(ModCreativeTabs.title())
                .icon(ModCreativeTabs::icon)
                .displayItems((params, output) -> ModCreativeTabs.fill(output))
                .build());
    }

    private ForgeRegistrar() {
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        EFFECTS.register(bus);
        TABS.register(bus);
    }
}
