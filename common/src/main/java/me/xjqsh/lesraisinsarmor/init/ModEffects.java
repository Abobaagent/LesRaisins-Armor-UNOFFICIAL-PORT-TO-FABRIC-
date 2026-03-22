package me.xjqsh.lesraisinsarmor.init;

import me.xjqsh.lesraisinsarmor.ModConstants;
import me.xjqsh.lesraisinsarmor.effect.SuitEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public final class ModEffects {
    public static final MobEffect TOUGH_EFFECT = new SuitEffect(MobEffectCategory.BENEFICIAL, 0x000000, "attacker")
            .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "62E0C0F3-D94E-3821-BF1C-B5F17CB7F971",
                    15, Operation.ADDITION)
            .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "0BCE518E-9D05-CB4A-C435-C68BEEE57650",
                    0.25d, Operation.ADDITION);
    public static final MobEffect LIGHT_LEG_EFFECT = new SuitEffect(MobEffectCategory.BENEFICIAL, 0x000000, "scout")
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, "FC303D2A-7C7F-FFAB-2B4E-A5EF09BB2AF1",
                    0.025, Operation.ADDITION);
    public static final MobEffect HEAVY_EFFECT = new SuitEffect(MobEffectCategory.BENEFICIAL, 0x000000, "defender")
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, "37DAE46A-CB52-2E2C-71FC-4AE7348B0B8D",
                    -0.02, Operation.ADDITION);
    public static final MobEffect RESCUE_EFFECT = new SuitEffect(MobEffectCategory.BENEFICIAL, 0x000000, "medical");
    public static final MobEffect RESCUE_COOLDOWN_EFFECT = new MobEffect(MobEffectCategory.HARMFUL, 0x000000) {
    };

    private static final Map<String, Supplier<MobEffect>> FACTORIES = new LinkedHashMap<>();

    static {
        FACTORIES.put("tough", () -> TOUGH_EFFECT);
        FACTORIES.put("light_leg", () -> LIGHT_LEG_EFFECT);
        FACTORIES.put("heavy_armor", () -> HEAVY_EFFECT);
        FACTORIES.put("rescue", () -> RESCUE_EFFECT);
        FACTORIES.put("rescue_cooldown", () -> RESCUE_COOLDOWN_EFFECT);
    }

    private ModEffects() {
    }

    public static void registerAll(BiConsumer<String, Supplier<MobEffect>> registrar) {
        FACTORIES.forEach(registrar);
    }

    public static MobEffect tough() {
        return get("tough");
    }

    public static MobEffect lightLeg() {
        return get("light_leg");
    }

    public static MobEffect heavyArmor() {
        return get("heavy_armor");
    }

    public static MobEffect rescue() {
        return get("rescue");
    }

    public static MobEffect rescueCooldown() {
        return get("rescue_cooldown");
    }

    private static MobEffect get(String id) {
        ResourceLocation rl = new ResourceLocation(ModConstants.MOD_ID, id);
        return BuiltInRegistries.MOB_EFFECT.get(rl);
    }
}
