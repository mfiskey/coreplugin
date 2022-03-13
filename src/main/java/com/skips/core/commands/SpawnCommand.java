package com.skips.core.commands;

import com.skips.core.main.Main;
import com.skips.core.methods.SpawnDelay;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final Main plugin;

    public SpawnCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("setspawn")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Cannot execute command from console!");
                return true;
            }
        }
        Player player = (Player) sender;
        Location location = plugin.getConfig().getLocation("spawn");

        if (location == null) {
            player.sendMessage(ChatColor.RED + "There needs to be a spawn point set before using this command. " +
                    "Use /setspawn to do so.");
            return true;
        }
        else {
            SpawnDelay.teleport(player);
        }
        return false;
    }
}
