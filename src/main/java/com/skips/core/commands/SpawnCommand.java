package com.skips.core.commands;

import com.skips.core.main.Main;
import com.skips.core.methods.SpawnDelayMethod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("setspawn")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Cannot execute command from console!");
                return true;
            }
        }
        Player player = (Player) sender;

        if (Main.getPlugin().getConfig().getLocation("spawn") == null) {
            player.sendMessage(ChatColor.RED + "There needs to be a spawn point set before using this command. " +
                    "Use /setspawn to do so.");
            return true;
        }
        else {
            SpawnDelayMethod.teleport(player);
        }
        return false;
    }
}
