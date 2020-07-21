package net.eterniamc.bridge.implementation.bukkit;

import me.xanium.gemseconomy.api.GemsEconomyAPI;
import me.xanium.gemseconomy.economy.Currency;
import net.eterniamc.bridge.CurrencyController;
import net.eterniamc.bridge.implementation.custom.CustomCurrencyController;
import net.minecraft.entity.player.EntityPlayerMP;

public class GemsEconomyCurrencyController implements CurrencyController {
    private final GemsEconomyAPI api = new GemsEconomyAPI();

    @Override
    public double getPlayerBalance(String currency, EntityPlayerMP player) {
        return api.getBalance(player.getUniqueID(), api.getCurrency(currency));
    }

    @Override
    public void setPlayerBalance(String currency, EntityPlayerMP player, double balance) {
        api.withdraw(player.getUniqueID(), getPlayerBalance(currency, player));
        api.deposit(player.getUniqueID(), balance, api.getCurrency(currency));
    }

    @Override
    public void addPlayerBalance(String currency, EntityPlayerMP player, double balance) {
        api.deposit(player.getUniqueID(), balance, api.getCurrency(currency));
    }

    @Override
    public void removePlayerBalance(String currency, EntityPlayerMP player, double balance) {
        api.withdraw(player.getUniqueID(), balance, api.getCurrency(currency));
    }
}
