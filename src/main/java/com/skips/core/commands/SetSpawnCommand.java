package com.skips.core.commands;

import com.skips.core.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("setspawn")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Cannot execute command from console!");
                return true;
            }
        }
        Player player = (Player) sender;
        if (!player.isOp()) {
            sender.sendMessage(ChatColor.RED + "You must have operator to be able to perform this command!");
            return true;
        }
        Location location = player.getLocation();

        Main.getPlugin().getConfig().set("spawn", location);
        Main.getPlugin().saveConfig();

        player.sendMessage(ChatColor.WHITE + "Spawn location has been set!");
        return true;
    }
}
