package net.eterniamc.bridge;

import net.minecraft.entity.player.EntityPlayerMP;

public interface CurrencyController {

    double getPlayerBalance(String currency, EntityPlayerMP player);

    void setPlayerBalance(String currency, EntityPlayerMP player, double balance);

    default void addPlayerBalance(String currency, EntityPlayerMP player, double balance) {
        setPlayerBalance(currency, player, getPlayerBalance(currency, player) + balance);
    }

    default void removePlayerBalance(String currency, EntityPlayerMP player, double balance) {
        setPlayerBalance(currency, player, getPlayerBalance(currency, player) - balance);
    }
}
