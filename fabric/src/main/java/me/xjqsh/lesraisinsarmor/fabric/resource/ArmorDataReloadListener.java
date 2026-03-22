package me.xjqsh.lesraisinsarmor.fabric.resource;

import me.xjqsh.lesraisinsarmor.ModConstants;
import me.xjqsh.lesraisinsarmor.resource.ArmorDataManager;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public final class ArmorDataReloadListener implements IdentifiableResourceReloadListener {
    private final ArmorDataManager delegate = ArmorDataManager.getInstance();

    @Override
    public ResourceLocation getFabricId() {
        return new ResourceLocation(ModConstants.MOD_ID, "armor_data");
    }

    @Override
    public CompletableFuture<Void> reload(PreparationBarrier barrier, ResourceManager manager, ProfilerFiller prepProfiler,
                                          ProfilerFiller applyProfiler, Executor backgroundExecutor, Executor gameExecutor) {
        return delegate.reload(barrier, manager, prepProfiler, applyProfiler, backgroundExecutor, gameExecutor);
    }
}
