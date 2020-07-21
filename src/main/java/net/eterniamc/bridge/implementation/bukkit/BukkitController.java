package net.eterniamc.bridge.implementation.bukkit;

import lombok.Getter;
import net.eterniamc.bridge.APIController;
import net.eterniamc.bridge.CurrencyController;
import net.eterniamc.bridge.implementation.custom.CustomCurrencyController;
import org.bukkit.Bukkit;

@Getter
public class BukkitController implements APIController {
    private CurrencyController currencyController;

    @Override
    public void initialize(Object mod) {
        if (Bukkit.getServer().getPluginManager().getPlugin("GemsEconomy") != null) {
            currencyController = new GemsEconomyCurrencyController();
        } else if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            currencyController = new VaultCurrencyController();
        } else {
            currencyController = new CustomCurrencyController();
        }
    }
}
