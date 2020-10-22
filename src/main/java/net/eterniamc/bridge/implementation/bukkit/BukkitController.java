package net.eterniamc.bridge.implementation.bukkit;

import lombok.Getter;
import net.eterniamc.bridge.APIController;
import net.eterniamc.bridge.CurrencyController;
import net.eterniamc.bridge.implementation.custom.CustomCurrencyController;
import net.eterniamc.bridge.implementation.pixelmon.PixelmonCurrencyController;
import org.bukkit.Bukkit;

@Getter
public class BukkitController implements APIController {
    private CurrencyController currencyController;

    @Override
    public void initialize(Object mod) throws Exception {
        if (Bukkit.getServer().getPluginManager().getPlugin("GemsEconomy") != null) {
            currencyController = (CurrencyController) Class.forName("net.eterniamc.bridge.implementation.bukkit.GemsEconomyCurrencyController").newInstance();
        } else if (isPixelmonPresent()) {
            currencyController = new PixelmonCurrencyController();
        } else {
            currencyController = new CustomCurrencyController();
        }
    }

    private boolean isPixelmonPresent() {
        try {
            Class.forName("com.pixelmonmod.pixelmon.Pixelmon");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
