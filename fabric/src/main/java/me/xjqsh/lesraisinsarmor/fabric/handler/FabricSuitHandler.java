package me.xjqsh.lesraisinsarmor.fabric.handler;

import me.xjqsh.lesraisinsarmor.init.ModEffects;
import me.xjqsh.lesraisinsarmor.item.LrArmorItem;
import me.xjqsh.lesraisinsarmor.platform.ConfigHooks;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public final class FabricSuitHandler {
    private static final ThreadLocal<Boolean> REDUCING_DAMAGE = ThreadLocal.withInitial(() -> false);

    private FabricSuitHandler() {
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                onPlayerTick(player);
            }
        });
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> onAllowDamage(entity, source, amount));
    }

    private static void onPlayerTick(Player player) {
        if (!ConfigHooks.enableArmorSetEffect()) return;
        if (player.level().isClientSide()) return;
        if (player.tickCount % 10 != 0) return;

        Item item = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
        if (item instanceof LrArmorItem armorItem) {
            if (armorItem.getSuitCount(player) == 4) {
                if ("medical".equals(armorItem.getSuitIdf())) {
                    if (player.getEffect(ModEffects.rescueCooldown()) == null) {
                        armorItem.applyEffect(player);
                    }
                } else {
                    armorItem.applyEffect(player);
                }
            }
        }
    }

    private static boolean onAllowDamage(net.minecraft.world.entity.LivingEntity entity, DamageSource source, float amount) {
        if (entity.level().isClientSide()) return true;

        if (entity.hasEffect(ModEffects.heavyArmor())) {
            if (!REDUCING_DAMAGE.get()) {
                REDUCING_DAMAGE.set(true);
                try {
                    entity.hurt(source, amount * 0.85f);
                } finally {
                    REDUCING_DAMAGE.set(false);
                }
                return false;
            }
        }

        if (entity instanceof Player player && ConfigHooks.enableArmorSetEffect()) {
            if (player.getHealth() - amount < player.getMaxHealth() * 0.3f && player.hasEffect(ModEffects.rescue())) {
                player.removeEffect(ModEffects.rescue());
                player.addEffect(new MobEffectInstance(ModEffects.rescueCooldown(), 600));
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 3));
            }
        }
        return true;
    }
}
