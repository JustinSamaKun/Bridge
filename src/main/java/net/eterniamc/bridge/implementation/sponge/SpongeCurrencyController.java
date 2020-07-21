package net.eterniamc.bridge.implementation.sponge;

import net.eterniamc.bridge.CurrencyController;
import net.eterniamc.bridge.implementation.custom.CustomCurrencyController;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;

import java.math.BigDecimal;

public class SpongeCurrencyController implements CurrencyController {
    private final EconomyService service = Sponge.getServiceManager().provideUnchecked(EconomyService.class);

    private Currency getCurrency(String currency) {
        return service.getCurrencies().stream()
                .filter(c -> c.getDisplayName().toPlain().equalsIgnoreCase(currency))
                .findFirst()
                .orElseThrow(() -> new Error("Could not find requested currency"));
    }

    @Override
    public double getPlayerBalance(String currency, EntityPlayerMP player) {
        return service.getOrCreateAccount(player.getUniqueID())
                .map(account -> account.getBalance(getCurrency(currency)).doubleValue())
                .orElse(0.0);
    }

    @Override
    public void setPlayerBalance(String currency, EntityPlayerMP player, double balance) {
        service.getOrCreateAccount(player.getUniqueID())
                .orElseThrow(() -> new Error("Could not get Player's account"))
                .setBalance(getCurrency(currency), new BigDecimal(balance), Cause.of(EventContext.empty(), player));
    }
}
