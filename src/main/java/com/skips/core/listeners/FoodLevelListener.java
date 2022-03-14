package com.skips.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelListener implements Listener {

    @EventHandler
    public void cancelFoodHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
