package net.eterniamc.bridge.implementation.forge;

import lombok.Getter;
import net.eterniamc.bridge.APIController;
import net.eterniamc.bridge.CurrencyController;
import net.eterniamc.bridge.implementation.pixelmon.PixelmonCurrencyController;

@Getter
public class ForgeController implements APIController {

    @Override
    public void initialize(Object modInstance) {

    }

    @Override
    public CurrencyController getCurrencyController() {
        throw new UnsupportedOperationException("Neither bukkit, sponge or pixelmon are loaded. Currency system can not be used");
    }
}
