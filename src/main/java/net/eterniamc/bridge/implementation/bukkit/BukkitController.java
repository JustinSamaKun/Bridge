package net.eterniamc.bridge.implementation.bukkit;

import lombok.Getter;
import net.eterniamc.bridge.APIController;
import net.eterniamc.bridge.CurrencyController;
import net.eterniamc.bridge.implementation.custom.CustomCurrencyController;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class BukkitController extends JavaPlugin implements APIController {
    private CurrencyController currencyController;

    @Override
    public void initialize(Object mod) throws Exception {
        if (isClassPresent("me.xanium.gemseconomy.api.GemsEconomyAPI")) {
            currencyController = (CurrencyController) Class.forName("net.eterniamc.bridge.implementation.bukkit.GemsEconomyCurrencyController").newInstance();
        } else if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            currencyController = (CurrencyController) Class.forName("net.eterniamc.bridge.implementation.bukkit.VaultCurrencyController").newInstance();
        } else {
            currencyController = new CustomCurrencyController();
        }
    }

    private boolean isClassPresent(String clazz) {
        try {
            Class.forName(clazz);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
