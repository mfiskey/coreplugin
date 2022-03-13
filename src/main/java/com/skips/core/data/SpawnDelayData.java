package com.skips.core.data;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitTask;

public class SpawnDelayData {

    public static int delay = 10;
    public static String tpDelayMsg = ChatColor.WHITE + "Teleporting in "
            + ChatColor.GOLD + delay + ChatColor.WHITE + " seconds.";
    public static String tpCancelMsg = ChatColor.RED + "Teleportation was canceled. DO NOT MOVE!";
}