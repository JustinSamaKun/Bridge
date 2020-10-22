package net.eterniamc.bridge.implementation.bukkit;

import net.eterniamc.bridge.CurrencyController;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class GemsEconomyCurrencyController implements CurrencyController {
    private final Plugin api;
    private Class<?> currencyClass;

    public GemsEconomyCurrencyController() {
        api = Bukkit.getServer().getPluginManager().getPlugin("GemsEconomy");
        try {
            System.out.println(getAccountManager());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getAccountManager() throws Exception {
        return api.getClass().getDeclaredMethod("getAccountManager").invoke(api);
    }

    private Object getAccount(EntityPlayerMP player) throws Exception {
        Object manager = getAccountManager();
        return manager.getClass().getDeclaredMethod("getAccount", UUID.class).invoke(manager, player.getUniqueID());
    }

    private Object getCurrencyManager() throws Exception {
        return api.getClass().getDeclaredMethod("getCurrencyManager").invoke(api);
    }

    private Object getCurrency(String name) throws Exception {
        Object manager = getCurrencyManager();
        Object currency = manager.getClass().getDeclaredMethod("getCurrency", String.class).invoke(manager, name);
        if (currencyClass == null && currency != null) {
            currencyClass = currency.getClass();
        }
        return currency;
    }

    @Override
    public double getPlayerBalance(String currency, EntityPlayerMP player) {
        try {
            Object account = getAccount(player);
            Object currencyInstance = getCurrency(currency);
            return (double) account.getClass().getDeclaredMethod("getBalance", currencyInstance.getClass()).invoke(account, currencyInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setPlayerBalance(String currency, EntityPlayerMP player, double balance) {
        removePlayerBalance(currency, player, getPlayerBalance(currency, player));
        addPlayerBalance(currency, player, balance);
    }

    @Override
    public void addPlayerBalance(String currency, EntityPlayerMP player, double balance) {
        try {
            Object account = getAccount(player);
            Object currencyInstance = getCurrency(currency);
            account.getClass().getDeclaredMethod("deposit", currencyInstance.getClass(), double.class).invoke(account, currencyInstance, balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePlayerBalance(String currency, EntityPlayerMP player, double balance) {
        try {
            Object account = getAccount(player);
            Object currencyInstance = getCurrency(currency);
            account.getClass().getDeclaredMethod("withdraw", currencyInstance.getClass(), double.class).invoke(account, currencyInstance, balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
