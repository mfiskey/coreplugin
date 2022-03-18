package com.skips.core.commands;

import com.skips.core.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
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
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(args[1]).getUniqueId());
            if (Main.getPlugin().getServer().getPlayer(args[1]) == null && !Main.playerStatsData.getConfig("playerStats.yml").contains(offlinePlayer.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "Designated player has NOT played before!");
                return true;
            }
            else {
                FileConfiguration pSD = Main.playerStatsData.getConfig("playerStats.yml");
                if (Main.getPlugin().getServer().getPlayer(args[1]) != null) {
                    Player playerToChange = Main.getPlugin().getServer().getPlayer(args[1]);
                    pSD.set(playerToChange.getUniqueId() + ".kills", 0);
                    pSD.set(playerToChange.getUniqueId() + ".deaths", 0);
                    pSD.set(playerToChange.getUniqueId() + ".killstreak", 0);
                    pSD.set(playerToChange.getUniqueId() + ".kdr", 0.0);
                    Main.playerStatsData.saveConfig("playerStats.yml");

                    playerToChange.getScoreboard().getTeam("killsScore").setSuffix(ChatColor.WHITE.toString() +
                            Main.playerStatsData.getConfig("playerStats.yml").get(playerToChange.getUniqueId() + ".kills"));

                    playerToChange.getScoreboard().getTeam("deathsScore").setSuffix(ChatColor.WHITE.toString() +
                            Main.playerStatsData.getConfig("playerStats.yml").get(playerToChange.getUniqueId() + ".deaths"));

                    playerToChange.getScoreboard().getTeam("killStreakScore").setSuffix(ChatColor.WHITE.toString() +
                            Main.playerStatsData.getConfig("playerStats.yml").get(playerToChange.getUniqueId() + ".killstreak"));

                    playerToChange.getScoreboard().getTeam("kdrScore").setSuffix(ChatColor.WHITE.toString() +
                            Main.playerStatsData.getConfig("playerStats.yml").get(playerToChange.getUniqueId() + ".kdr"));

                }
                else {
                    OfflinePlayer playerToChange = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                    pSD.set(playerToChange.getUniqueId() + ".kills", 0);
                    pSD.set(playerToChange.getUniqueId() + ".deaths", 0);
                    pSD.set(playerToChange.getUniqueId() + ".killstreak", 0);
                    pSD.set(playerToChange.getUniqueId() + ".kdr", 0.0);
                    Main.playerStatsData.saveConfig("playerStats.yml");
                }
                player.sendMessage(ChatColor.WHITE + "Reset the stats of " + ChatColor.GOLD + args[1].toLowerCase() + ChatColor.WHITE + ".");
                return true;
            }
        }
        else {
            player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
        }
        return true;
    }
}
