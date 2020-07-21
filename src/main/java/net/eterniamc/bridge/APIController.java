package net.eterniamc.bridge;

public interface APIController {

    void initialize(Object modInstance);

    CurrencyController getCurrencyController();
}
