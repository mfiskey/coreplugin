package com.skips.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            assert killer != null;
            if (killer.getHealth() <= (float)16.0) killer.setHealth(killer.getHealth() + (float)4.0);
            else killer.setHealth((float)20.0);
        }
    }
}
