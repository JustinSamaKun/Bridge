package net.eterniamc.bridge.implementation.pixelmon;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.economy.IPixelmonBankAccount;
import net.eterniamc.bridge.implementation.custom.CustomCurrencyController;
import net.minecraft.entity.player.EntityPlayerMP;

public class PixelmonCurrencyController extends CustomCurrencyController {
    private IPixelmonBankAccount getPlayerAccount(EntityPlayerMP player) {
        return Pixelmon.moneyManager.getBankAccount(player).orElseThrow(() -> new Error("Could not get bank account for " + player.getName()));
    }

    @Override
    public double getPlayerBalance(String currency, EntityPlayerMP player) {
        if (currency.toLowerCase().startsWith("coin")) {
            return getPlayerAccount(player).getMoney();
        } else {
            return super.getPlayerBalance(currency, player);
        }
    }

    @Override
    public void setPlayerBalance(String currency, EntityPlayerMP player, double balance) {
        if (currency.toLowerCase().startsWith("coin")) {
            getPlayerAccount(player).setMoney((int) balance);
        } else {
            super.setPlayerBalance(currency, player, balance);
        }
    }
}
