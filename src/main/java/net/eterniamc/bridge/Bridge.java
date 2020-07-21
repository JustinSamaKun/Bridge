package net.eterniamc.bridge;

import net.eterniamc.bridge.commands.AddCurrencyCommand;
import net.eterniamc.bridge.commands.BalanceCommand;
import net.eterniamc.bridge.commands.RemoveCurrencyCommand;
import net.eterniamc.bridge.commands.SetCurrencyCommand;
import net.minecraft.command.CommandHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.io.File;

public enum Bridge {
    INSTANCE;

    public APIController API;

    public void initialize(Object modInstance) throws Exception {
        APIProvider provider = APIProvider.getCurrentAPI();
        provider.initialize(modInstance);
        API = provider.getController();
        CommandHandler handler = (CommandHandler) FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager();
        handler.registerCommand(new BalanceCommand());
        handler.registerCommand(new SetCurrencyCommand());
        handler.registerCommand(new AddCurrencyCommand());
        handler.registerCommand(new RemoveCurrencyCommand());
    }

    public void createConfigFolder() {
        File file = new File("./config/Bridge");
        if (!file.exists()) {
            file.mkdirs();
            file.mkdir();
        }
    }
}
