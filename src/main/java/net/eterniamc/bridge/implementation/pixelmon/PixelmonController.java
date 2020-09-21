package net.eterniamc.bridge.implementation.pixelmon;

import lombok.Getter;
import net.eterniamc.bridge.implementation.forge.ForgeController;

@Getter
public class PixelmonController extends ForgeController {
    private PixelmonCurrencyController currencyController;

    @Override
    public void initialize(Object modInstance) {
        super.initialize(modInstance);
        currencyController = new PixelmonCurrencyController();
    }
}
