package me.xjqsh.lesraisinsarmor.item;

import me.xjqsh.lesraisinsarmor.client.renderer.BedrockArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ForgeLrArmorItem extends LrArmorItem {
    public ForgeLrArmorItem(String suitIdf, ArmorItem.Type slot, Item.Properties properties, @Nullable Supplier<MobEffect> suitEffect) {
        super(suitIdf, slot, properties, suitEffect);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
                if (renderer == null) {
                    renderer = new BedrockArmorRenderer(getSuitIdf());
                }
                renderer.prepForRender(entity, stack, slot, original);
                return renderer;
            }
        });
    }
}
