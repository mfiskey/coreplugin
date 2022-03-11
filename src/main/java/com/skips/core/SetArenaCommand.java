package com.skips.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class SetArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("arena")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Cannot execute command from console!");
                return true;
            }
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "You must specify an action: /arena <set/delete/move> <name>");
                } else if (args.length == 1) {
                    if (!(args[0].equalsIgnoreCase("set")) && !(args[0].equalsIgnoreCase("delete"))
                            && !(args[0].equalsIgnoreCase("move"))) {
                        player.sendMessage(ChatColor.RED + "You must specify an action: /arena <set/delete/move> <name>");
                    } else {
                        player.sendMessage(ChatColor.RED + "You must specify a name: /arena " + args[0].toLowerCase() + " <name>");
                    }
                } else if (args.length == 2) {
                    if (!(args[0].equalsIgnoreCase("set")) && !(args[0].equalsIgnoreCase("delete"))) {
                        player.sendMessage(ChatColor.RED + "You must specify an action: /arena <set/delete/move> <name>");
                    }
                    String arenaName = args[1].substring(0, 1).toUpperCase() + args[1].substring(1).toLowerCase();
                    if (args[0].equalsIgnoreCase("set")) {
                        player.sendMessage(ChatColor.WHITE + "The arena " + ChatColor.GOLD + arenaName + ChatColor.WHITE + " has been set.");
                        //TODO: set logic here.
                    } else if (args[0].equalsIgnoreCase("delete")) {
                        player.sendMessage(ChatColor.WHITE + "The arena " + ChatColor.GOLD + arenaName + ChatColor.WHITE + " has been deleted.");
                        //TODO: delete logic here
                    } else if (args[0].equalsIgnoreCase("move")) {
                        player.sendMessage(ChatColor.WHITE + "The arena " + ChatColor.GOLD + arenaName + ChatColor.WHITE + " has been moved.");
                        //TODO: move logic here.
                    }
                } else if (args.length > 2) {
                    player.sendMessage(ChatColor.RED + "The command must be executed in the following format: /arena <set/delete/move> <name>");
                } else {
                    player.sendMessage(ChatColor.DARK_RED + "Error occurred trying to use /arena.");
                }
            }
        }
        return false;
    }
}
