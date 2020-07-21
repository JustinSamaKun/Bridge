package net.eterniamc.bridge.implementation.sponge;

import lombok.Getter;
import net.eterniamc.bridge.APIController;
import org.spongepowered.api.plugin.PluginContainer;

@Getter
public class SpongeController implements APIController {
    private SpongeCurrencyController currencyController;

    private Object mod;

    @Override
    public void initialize(Object mod) {
        this.mod = mod;
        currencyController = new SpongeCurrencyController();
    }
}
