package net.eterniamc.bridge.commands;

import net.eterniamc.bridge.Bridge;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AddCurrencyCommand extends CommandBase {
    @Override
    public String getName() {
        return "addcurrency";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "addcurrency <player> <amount> <currency...>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 3) {
            sender.sendMessage(new TextComponentString(getUsage(sender)));
            return;
        }

        EntityPlayerMP player = getPlayer(server, sender, args[0]);
        double amount = Double.parseDouble(args[1]);
        String currency = Arrays.stream(args, 2, args.length).collect(Collectors.joining(" "));

        Bridge.INSTANCE.API.getCurrencyController().addPlayerBalance(currency, player, amount);
    }
}
