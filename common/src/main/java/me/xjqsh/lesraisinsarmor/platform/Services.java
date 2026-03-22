package me.xjqsh.lesraisinsarmor.platform;

import java.util.ServiceLoader;

public final class Services {
    private static final PlatformServices PLATFORM = load(PlatformServices.class);

    private Services() {
    }

    public static PlatformServices platform() {
        return PLATFORM;
    }

    private static <T> T load(Class<T> service) {
        return ServiceLoader.load(service)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Missing service implementation for " + service.getName()));
    }
}
