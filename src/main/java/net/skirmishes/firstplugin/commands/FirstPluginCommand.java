package net.skirmishes.firstplugin.commands;

import net.skirmishes.firstplugin.Core;
import net.skirmishes.firstplugin.managers.GUICreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FirstPluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (!commandSender.hasPermission("firstplugin.use"))
        {
            commandSender.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" &cNo permission."));
            return true;
        }

        if (!(commandSender instanceof Player))
        {
            commandSender.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" &cThis command is only for in game players."));
            return true;
        }

        Player player = (Player) commandSender;

        if (args.length == 0)
        {
            player.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" &cWrong usage do /"+command.getLabel()+" [test, hello]"));
            return true;
        }

        String subCommand = args[0];

        if (subCommand.equalsIgnoreCase("hello"))
        {
            player.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" &aHello, &b"+player.getDisplayName()));
            return true;
        }
        if (subCommand.equalsIgnoreCase("test"))
        {
            player.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" &aTest, &b"+player.getAllowFlight()));
            return true;
        }
        if (subCommand.equalsIgnoreCase("reload"))
        {
            Core.plugin.reloadConfigs();
            player.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" Reloading plugin."));
            return true;
        }
        if (subCommand.equalsIgnoreCase("gui"))
        {
            player.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" &bOpening custom GUI."));
            GUICreator.createPrivateGUI(player);
            return true;
        }
        player.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" &cUnknown subcommand."));
        return true;
    }
}
