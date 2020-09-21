package net.eterniamc.bridge;

public interface APIController {

    void initialize(Object modInstance) throws Exception;

    CurrencyController getCurrencyController();
}
