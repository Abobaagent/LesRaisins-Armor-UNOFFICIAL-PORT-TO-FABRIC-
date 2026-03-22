package me.xjqsh.lesraisinsarmor.fabric.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import me.xjqsh.lesraisinsarmor.fabric.client.FabricClientHandler;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {
    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"))
    private void lrarmor$beforeRender(LivingEntity entity, float entityYaw, float tickDelta, PoseStack matrices, MultiBufferSource buffer, int light, CallbackInfo ci) {
        FabricClientHandler.beforeRender(entity, (LivingEntityRenderer<?, ?>) (Object) this, tickDelta, matrices, buffer, light);
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("TAIL"))
    private void lrarmor$afterRender(LivingEntity entity, float entityYaw, float tickDelta, PoseStack matrices, MultiBufferSource buffer, int light, CallbackInfo ci) {
        FabricClientHandler.afterRender(entity, (LivingEntityRenderer<?, ?>) (Object) this, tickDelta, matrices, buffer, light);
    }
}
