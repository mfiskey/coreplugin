package com.skips.core.listeners;

import com.skips.core.data.DataManager;
import com.skips.core.main.Main;
import com.skips.core.procedures.KitClassProcedureTemp;
import com.skips.core.procedures.SpawnDelayProcedure;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SpawnListener implements Listener {

    private DataManager spawnData;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Location location = Main.spawnData.getConfig("spawn.yml").getLocation("spawn");
        if (location != null) {
            player.teleport(location);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Location location = Main.spawnData.getConfig("spawn.yml").getLocation("spawn");
        if (location != null) {
            e.setRespawnLocation(location);
        }
        KitClassProcedureTemp.setKit(e.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getFrom().getZ() != e.getTo().getZ() && e.getFrom().getX() != e.getTo().getX()) {
            SpawnDelayProcedure.cancelTeleport(e.getPlayer());
        }
    }
}
