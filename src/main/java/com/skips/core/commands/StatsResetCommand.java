package com.skips.core.commands;

import com.skips.core.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class StatsResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("stats")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Cannot execute command from console!");
                return true;
            }
        }
        Player player = (Player) sender;
        if (player.isOp()) {
            if (args.length == 0 || !args[0].equalsIgnoreCase("reset")) {
                player.sendMessage(ChatColor.RED + "Please use /stats <reset> <username>");
                return true;
            }
            if (args.length != 2) {
                player.sendMessage(ChatColor.RED + "Please use /stats <reset> <username>");
                return true;
            }
            if (!Main.playerStatsData.getConfig("playerStats.yml").contains(args[1])) {
                player.sendMessage(ChatColor.RED + "Designated player has NOT played before!");
                return true;
            }
            else {
                FileConfiguration pSD = Main.playerStatsData.getConfig("playerStats.yml");
                Player playerToChange = Main.getPlugin().getServer().getPlayer(args[1]);

                pSD.set(playerToChange.getName() + ".kills", 0);
                pSD.set(playerToChange.getName() + ".deaths", 0);
                pSD.set(playerToChange.getName() + ".killstreak", 0);
                pSD.set(playerToChange.getName() + ".kdr", 0.0);

                Main.playerStatsData.saveConfig("playerStats.yml");

                playerToChange.getScoreboard().getTeam("killsScore").setSuffix(ChatColor.WHITE.toString() +
                        Main.playerStatsData.getConfig("playerStats.yml").get(playerToChange.getName() + ".kills"));

                playerToChange.getScoreboard().getTeam("deathsScore").setSuffix(ChatColor.WHITE.toString() +
                        Main.playerStatsData.getConfig("playerStats.yml").get(playerToChange.getName() + ".deaths"));

                playerToChange.getScoreboard().getTeam("killStreakScore").setSuffix(ChatColor.WHITE.toString() +
                        Main.playerStatsData.getConfig("playerStats.yml").get(playerToChange.getName() + ".killstreak"));

                playerToChange.getScoreboard().getTeam("kdrScore").setSuffix(ChatColor.WHITE.toString() +
                        Main.playerStatsData.getConfig("playerStats.yml").get(playerToChange.getName() + ".kdr"));

                player.sendMessage(ChatColor.WHITE + "Reset the stats of " + ChatColor.GOLD + args[1].toLowerCase() + ChatColor.WHITE + ".");
            }


        }
        return true;
    }
}
