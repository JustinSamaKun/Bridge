package net.eterniamc.bridge.implementation.forge;

import lombok.Getter;
import net.eterniamc.bridge.APIController;
import net.eterniamc.bridge.CurrencyController;
import net.eterniamc.bridge.implementation.custom.CustomCurrencyController;

@Getter
public class ForgeController implements APIController {
    private CurrencyController currencyController;

    @Override
    public void initialize(Object modInstance) {
        currencyController = new CustomCurrencyController();
    }
}
