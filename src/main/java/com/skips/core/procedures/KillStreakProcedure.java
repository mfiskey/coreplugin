package com.skips.core.procedures;

import com.skips.core.listeners.SideBarListener;
import com.skips.core.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;


public class KillStreakProcedure {

    public static void killStreakAnnouncer(Player player) {
        if (player.getKiller() == null) {
            return;
        }
        if (SideBarListener.killStreakMap.get(player.getKiller().getUniqueId()) == 3) {
            Main.getPlugin().getServer().broadcastMessage(ChatColor.WHITE + ChatColor.BOLD.toString()
                    + "MID! " + ChatColor.GOLD + player.getKiller().getName().toLowerCase()
                    + ChatColor.WHITE + " is on a " + ChatColor.GOLD + "3 "
                    + ChatColor.WHITE + "player kill streak!");
            playSoundAll(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, (float)2.0);
        }
        else if (SideBarListener.killStreakMap.get(player.getKiller().getUniqueId()) == 5) {
            Main.getPlugin().getServer().broadcastMessage(ChatColor.WHITE + ChatColor.BOLD.toString()
                    + "OK! " + ChatColor.GOLD + player.getKiller().getName().toLowerCase()
                    + ChatColor.WHITE + " is on a " + ChatColor.GOLD + "5 "
                    + ChatColor.WHITE + "player kill streak!");
            playSoundAll(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, (float)1.0);
        }
        else if (SideBarListener.killStreakMap.get(player.getKiller().getUniqueId()) == 10) {
            Main.getPlugin().getServer().broadcastMessage(ChatColor.WHITE + ChatColor.BOLD.toString()
                    + "DAYUM! " + ChatColor.GOLD + player.getKiller().getName().toLowerCase()
                    + ChatColor.WHITE + " is on a " + ChatColor.GOLD + "10 "
                    + ChatColor.WHITE + "player kill streak!");
            playSoundAll(Sound.ENTITY_PLAYER_LEVELUP, (float)1.0);
        }
        else if (SideBarListener.killStreakMap.get(player.getKiller().getUniqueId()) == 20) {
            Main.getPlugin().getServer().broadcastMessage(ChatColor.WHITE + ChatColor.BOLD.toString()
                    + "GODLIKE! " + ChatColor.GOLD + player.getKiller().getName().toLowerCase()
                    + ChatColor.WHITE + " is on a " + ChatColor.GOLD + "20 "
                    + ChatColor.WHITE + "player kill streak!");
            playSoundAll(Sound.ENTITY_WITHER_SPAWN, (float)1.0);
        }
        else if (SideBarListener.killStreakMap.get(player.getKiller().getUniqueId()) == 30) {
            Main.getPlugin().getServer().broadcastMessage(ChatColor.WHITE + ChatColor.BOLD.toString()
                    + "TRYHARD! " + ChatColor.GOLD + player.getKiller().getName().toLowerCase()
                    + ChatColor.WHITE + " is on a " + ChatColor.GOLD + "30 "
                    + ChatColor.WHITE + "player kill streak!");
            playSoundAll(Sound.ENTITY_ENDER_DRAGON_GROWL, (float)1.0);
        }
        return;
    }

    public static void playSoundAll(Sound sound, Float pitch) {
        for (Player p: Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), sound, (float)16, pitch);
        }
    }
}
