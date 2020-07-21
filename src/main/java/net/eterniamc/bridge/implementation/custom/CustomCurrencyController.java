package net.eterniamc.bridge.implementation.custom;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import net.eterniamc.bridge.Bridge;
import net.eterniamc.bridge.CurrencyController;
import net.minecraft.entity.player.EntityPlayerMP;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CustomCurrencyController implements CurrencyController {
    private final File FILE = new File("./config/Bridge/economy.json");

    private final JsonObject data;

    @SneakyThrows
    public CustomCurrencyController() {
        if (FILE.exists()) {
            data = new JsonParser().parse(new FileReader(FILE)).getAsJsonObject();
        } else {
            data = new JsonObject();
        }
    }

    @SneakyThrows
    public void doSave() {
        Bridge.INSTANCE.createConfigFolder();
        FileWriter writer = new FileWriter(FILE);
        writer.write(data.toString());
        writer.flush();
        writer.close();
    }

    @Override
    public double getPlayerBalance(String currency, EntityPlayerMP player) {
        JsonObject currencyObject = data.getAsJsonObject(currency);
        if (currencyObject == null) {
            return 0;
        }
        JsonElement rawBalance = currencyObject.get(player.getUniqueID().toString());
        return rawBalance == null || rawBalance.isJsonNull() ? 0 : rawBalance.getAsDouble();
    }

    @Override
    public void setPlayerBalance(String currency, EntityPlayerMP player, double balance) {
        JsonObject currencyObject = data.getAsJsonObject(currency);
        if (currencyObject == null) {
            currencyObject = new JsonObject();
            data.add(currency, currencyObject);
        }
        currencyObject.remove(player.getUniqueID().toString());
        currencyObject.addProperty(player.getUniqueID().toString(), balance);
        doSave();
    }
}
