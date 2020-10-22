package net.eterniamc.bridge;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum APIProvider {
    SPONGE("net.eterniamc.bridge.implementation.sponge.SpongeController"),
    BUKKIT("net.eterniamc.bridge.implementation.bukkit.BukkitController"),
    PIXELMON("net.eterniamc.bridge.implementation.pixelmon.PixelmonController"),
    FORGE("net.eterniamc.bridge.implementation.forge.ForgeController");

    private final String controllerClass;
    @Getter
    private APIController controller;

    public static APIProvider getCurrentAPI() {
        if (classExists("org.spongepowered.api.Sponge")) {
            return SPONGE;
        } else if (classExists("org.bukkit.Bukkit")) {
            return BUKKIT;
        } else if (classExists("com.pixelmonmod.pixelmon.Pixelmon")) {
            return PIXELMON;
        } else {
            return FORGE;
        }
    }

    private static boolean classExists(String name) {
        try {
            Class.forName(name, false, APIProvider.class.getClassLoader());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void initialize(Object modInstance) throws Exception {
        controller = (APIController) Class.forName(controllerClass).newInstance();
        Class.forName(controllerClass).getDeclaredMethod("initialize", Object.class).invoke(controller, modInstance);
    }
}
