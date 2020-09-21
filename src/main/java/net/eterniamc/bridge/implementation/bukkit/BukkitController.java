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
    public void initialize(Object mod) throws Exception {
        if (Bukkit.getServer().getPluginManager().getPlugin("GemsEconomy") != null) {
            currencyController = (CurrencyController) Class.forName("net.eterniamc.bridge.implementation.bukkit.GemsEconomyCurrencyController").newInstance();
        } else if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            currencyController = (CurrencyController) Class.forName("net.eterniamc.bridge.implementation.bukkit.VaultCurrencyController").newInstance();
        } else {
            currencyController = new CustomCurrencyController();
        }
    }
}
