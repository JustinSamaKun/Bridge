package net.eterniamc.bridge.commands;

import net.eterniamc.bridge.Bridge;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class RemoveCurrencyCommand extends CommandBase {
    @Override
    public String getName() {
        return "removecurrency";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "removecurrency <player> <currency> <amount>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP player = getPlayer(server, sender, args[0]);
        String currency = args[1];
        double amount = Double.parseDouble(args[2]);

        Bridge.INSTANCE.API.getCurrencyController().removePlayerBalance(currency, player, amount);
    }
}
