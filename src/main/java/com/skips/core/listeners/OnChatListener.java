package com.skips.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        Player player = event.getPlayer();

        Bukkit.broadcastMessage(ChatColor.WHITE + ChatColor.BOLD.toString() +
                player.getName().toLowerCase() + ": " + ChatColor.WHITE + event.getMessage());
    }
}
