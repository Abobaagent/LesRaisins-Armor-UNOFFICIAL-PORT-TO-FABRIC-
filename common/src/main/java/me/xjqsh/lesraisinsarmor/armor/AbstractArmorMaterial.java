package me.xjqsh.lesraisinsarmor.armor;

import me.xjqsh.lesraisinsarmor.item.LrArmorItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

/**
 * е‡єдєЋе…је®№жЂ§иЂѓи™‘дїќз•™зљ„з±»
 * жЉЉе±ћжЂ§иЋ·еЏ–е…ЁйѓЁд»Јзђ†з»™з‰©е“Ѓе¤„зђ†
 * ж­Јеёёжѓ…е†µдёЌеє”иЇҐдёЂдёЄйЂљиї‡иї™дёЄз±»и®їй—®е±ћжЂ§
 */
public class AbstractArmorMaterial implements ArmorMaterial {
    private final LrArmorItem item;
    public AbstractArmorMaterial(LrArmorItem item){
        this.item = item;
    }
    @Override
    public int getDurabilityForType(@NotNull ArmorItem.Type pType) {
        return item.getMaxDurability();
    }

    @Override
    public int getDefenseForType(@NotNull ArmorItem.Type pType) {
        return item.getDefense();
    }

    @Override
    public int getEnchantmentValue() {
        return item.getEnchantmentValue();
    }

    @NotNull
    @Override
    public SoundEvent getEquipSound() {
        return item.getEquipSound();
    }

    @NotNull
    @Override
    public Ingredient getRepairIngredient() {
        Ingredient ingredient = item.getIngredient();
        return ingredient == null ? Ingredient.EMPTY : ingredient;
    }

    @NotNull
    @Override
    public String getName() {
        return item.getSuitIdf();
    }

    @Override
    public float getToughness() {
        return item.getToughness();
    }

    @Override
    public float getKnockbackResistance() {
        return item.getKnockbackResistance();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AbstractArmorMaterial material){
            return material.item.getSuitIdf().equals(this.item.getSuitIdf());
        }
        return false;
    }
}
