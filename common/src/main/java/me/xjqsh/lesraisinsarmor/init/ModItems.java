package me.xjqsh.lesraisinsarmor.init;

import me.xjqsh.lesraisinsarmor.ModConstants;
import me.xjqsh.lesraisinsarmor.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public final class ModItems {
    private static final Map<String, Supplier<Item>> FACTORIES = new LinkedHashMap<>();

    static {
        registerInBatch("armored_chemical");
        registerInBatch("attacker", ModEffects::tough);
        registerInBatch("chemical_protective");
        registerInBatch("defender", ModEffects::heavyArmor);
        registerInBatch("medical", ModEffects::rescue);
        registerInBatch("scout", ModEffects::lightLeg);
        registerInBatch("sniper");
        registerInBatch("dea_armed");
        registerInBatch("dea");
        registerInBatch("atf");
        registerInBatch("atf_vest");
        registerInBatch("irs");
        registerInBatch("fbi");
        registerInBatch("fbi_armed");
        registerInBatch("joker");
        registerSingle(ArmorItem.Type.HELMET, "joker_armed");
        registerSingle(ArmorItem.Type.CHESTPLATE, "joker_armed");
    }

    private ModItems() {
    }

    public static void registerAll(BiConsumer<String, Supplier<Item>> registrar) {
        FACTORIES.forEach(registrar);
    }

    public static Item get(String id) {
        ResourceLocation rl = new ResourceLocation(ModConstants.MOD_ID, id);
        Item item = BuiltInRegistries.ITEM.get(rl);
        return item == null ? Items.AIR : item;
    }

    public static List<Item> all() {
        List<Item> items = new ArrayList<>();
        for (String id : FACTORIES.keySet()) {
            Item item = get(id);
            if (item != Items.AIR) {
                items.add(item);
            }
        }
        return items;
    }

    public static Map<String, Supplier<Item>> entries() {
        return Collections.unmodifiableMap(FACTORIES);
    }

    public static void registerSingle(ArmorItem.Type slotType, String name) {
        String slotName = slotType.getName();
        registerItem(name + "_" + slotName, () -> Services.platform().createArmorItem(name, slotType,
                new Item.Properties(), null));
    }

    public static void registerInBatch(String name) {
        for (ArmorItem.Type slotType : ArmorItem.Type.values()) {
            String slotName = slotType.getName();
            registerItem(name + "_" + slotName,
                    () -> Services.platform().createArmorItem(name, slotType, new Item.Properties(), null));
        }
    }

    public static void registerInBatch(String name, Supplier<MobEffect> supplier) {
        for (ArmorItem.Type slotType : ArmorItem.Type.values()) {
            String slotName = slotType.getName();
            registerItem(name + "_" + slotName,
                    () -> Services.platform().createArmorItem(name, slotType, new Item.Properties(), supplier));
        }
    }

    private static void registerItem(String id, Supplier<Item> supplier) {
        FACTORIES.put(id, supplier);
    }
}
