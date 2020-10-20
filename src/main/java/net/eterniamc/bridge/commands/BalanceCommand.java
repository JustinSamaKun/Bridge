package net.eterniamc.bridge.commands;

import com.google.common.collect.Lists;
import net.eterniamc.bridge.Bridge;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BalanceCommand extends CommandBase {
    @Override
    public String getName() {
        return "balance";
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList("bal", "bbal");
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "balance <player> <currency...>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 2) {
            sender.sendMessage(new TextComponentString(getUsage(sender)));
            return;
        }

        EntityPlayerMP player = getPlayer(server, sender, args[0]);
        String currency = Arrays.stream(args, 1, args.length).collect(Collectors.joining(" "));

        double amount = Bridge.INSTANCE.API.getCurrencyController().getPlayerBalance(currency, player);
        sender.sendMessage(new TextComponentString(player.getName() + "'s " + currency + " balance: " + TextFormatting.GREEN + amount));
    }
}
