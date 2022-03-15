package com.skips.core.procedures;

import com.skips.core.data.DataManager;
import com.skips.core.data.SpawnDelayData;
import com.skips.core.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SpawnDelayProcedure {

    public static HashMap<UUID, Integer> delay = new HashMap<>();
    private static int counter = 10;

    public static void teleport(Player player) {
        if (!delay.containsKey(player.getUniqueId())) {
            player.sendMessage(SpawnDelayData.tpDelayMsg);
            int task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                if (delay.containsKey(player.getUniqueId())) {
                    delay.remove(player.getUniqueId());
                }
                if (Main.spawnData.getConfig("spawn.yml").getLocation("spawn") == null) {
                    player.sendMessage(ChatColor.RED + "Spawn has not been set! Contact the owner for help.");
                    Main.ccs.sendMessage(ChatColor.RED + player.getName() +
                            " tried executing /spawn, but there is no spawn set!");
                }
                else{
                    player.teleport(Main.spawnData.getConfig("spawn.yml").getLocation("spawn"));
                }

            }, (SpawnDelayData.delay * 20L));

            delay.put(player.getUniqueId(), task);
            counter = 10;
        }
    }
    public static void cancelTeleport(Player player) {
        if (delay.containsKey(player.getUniqueId())) {
            int task = ((Integer) delay.get(player.getUniqueId()));
            Bukkit.getScheduler().cancelTask(task);
            delay.remove(player.getUniqueId());
            if (Bukkit.getOnlinePlayers().contains(player)) player.sendMessage(SpawnDelayData.tpCancelMsg);
        }
    }
}