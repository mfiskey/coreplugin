package com.skips.core.methods;

import com.skips.core.data.SpawnDelayData;
import com.skips.core.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class SpawnDelay {

    public static HashMap<UUID, Integer> delay = new HashMap<>();

    public static void teleport(Player player) {
        if (!delay.containsKey(player.getUniqueId())) {
            player.sendMessage("Teleporting you to spawn in 10 seconds...");
            int i = SpawnDelayData.delay *= 20;
            int task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                if (delay.containsKey(player.getUniqueId())) {
                    delay.remove(player.getUniqueId());
                }
                if (!Main.getPlugin().getConfig().contains("spawn")) {
                    player.sendMessage(ChatColor.RED + "Spawn has not been set! Contact the owner for help.");
                    Main.ccs.sendMessage(ChatColor.RED + player.getName() +
                            " tried executing /spawn, but there is no spawn set!");
                }
                else {
                    player.teleport(Main.getPlugin().getConfig().getLocation("spawn"));
                }

            }, (SpawnDelayData.delay * 20));

            delay.put(player.getUniqueId(), Integer.valueOf(task));
        }
    }
    public static void cancelTeleport(Player player) {
        if (delay.containsKey(player.getUniqueId())) {
            int task = ((Integer)delay.get(player.getUniqueId()).intValue());
            Bukkit.getScheduler().cancelTask(task);
            delay.remove(player.getUniqueId());
            if (Bukkit.getOnlinePlayers().contains(player)) player.sendMessage(SpawnDelayData.tpCancelMsg);
        }
    }
}