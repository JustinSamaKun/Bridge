package net.eterniamc.bridge.implementation.bukkit;

import net.eterniamc.bridge.CurrencyController;
import net.eterniamc.bridge.implementation.custom.CustomCurrencyController;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;

public class VaultCurrencyController extends CustomCurrencyController {
    private Economy economy;

    public VaultCurrencyController() {
        economy = Bukkit.getServer().getServicesManager().getRegistration(Economy.class).getProvider();
    }

    @Override
    public double getPlayerBalance(String currency, EntityPlayerMP player) {
        if (!currency.equalsIgnoreCase(economy.currencyNameSingular()) || !currency.equalsIgnoreCase(economy.currencyNamePlural())) {
            return super.getPlayerBalance(currency, player);
        }
        return economy.getBalance(player.getName());
    }

    @Override
    public void setPlayerBalance(String currency, EntityPlayerMP player, double balance) {
        if (!currency.equalsIgnoreCase(economy.currencyNameSingular()) || !currency.equalsIgnoreCase(economy.currencyNamePlural())) {
            super.setPlayerBalance(currency, player, balance);
            return;
        }
        economy.depositPlayer(player.getName(), balance - economy.getBalance(player.getName()));
    }
}
