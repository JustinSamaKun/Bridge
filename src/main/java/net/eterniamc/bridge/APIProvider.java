package net.eterniamc.bridge;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.eterniamc.bridge.implementation.forge.ForgeController;
import net.eterniamc.bridge.implementation.pixelmon.PixelmonController;
import net.eterniamc.bridge.implementation.bukkit.BukkitController;
import net.eterniamc.bridge.implementation.sponge.SpongeController;

@RequiredArgsConstructor
public enum APIProvider {
    SPONGE(SpongeController.class),
    BUKKIT(BukkitController.class),
    PIXELMON(PixelmonController.class),
    FORGE(ForgeController.class);

    private final Class<? extends APIController> controllerClass;
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
        }
        return false;
    }

    public void initialize(Object modInstance) throws Exception {
        controller = controllerClass.newInstance();
        controllerClass.getDeclaredMethod("initialize", Object.class).invoke(controller, modInstance);
    }
}
