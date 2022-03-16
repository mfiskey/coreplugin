package com.skips.core.listeners;

import com.skips.core.procedures.SetArenaSpawnProcedure;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ArenaSpawnListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (SetArenaSpawnProcedure.cuboid.contains(event.getEntity().getLocation())) {
            event.setCancelled(true);
        }
    }
}
