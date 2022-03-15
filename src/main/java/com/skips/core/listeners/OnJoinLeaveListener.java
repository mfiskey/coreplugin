package com.skips.core.listeners;

import com.skips.core.procedures.KitClassProcedureTemp;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class OnJoinLeaveListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        String joinMessage = "";
        Player player = event.getPlayer();
        String mainTitle = ChatColor.GOLD + "" + ChatColor.BOLD + "KIT PVP SERVER";

        player.sendTitle(mainTitle,null,40,60,10);

        if (!player.hasPlayedBefore()) {
            joinMessage = ChatColor.WHITE + "" + ChatColor.BOLD + "WELCOME " + player.getName().toUpperCase() + " :)";
        }
        else {
            joinMessage = ChatColor.WHITE + player.getName().toUpperCase() + " HAS JOINED!";
        }

        event.setJoinMessage(joinMessage);
        KitClassProcedureTemp.setKit(event.getPlayer());

    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String leaveMessage = ChatColor.WHITE + "" + player.getName().toUpperCase() + " HAS LEFT!";

        event.setQuitMessage(leaveMessage);

    }

}
