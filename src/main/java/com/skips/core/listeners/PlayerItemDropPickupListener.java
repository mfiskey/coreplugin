package com.skips.core.listeners;

import com.skips.core.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerItemDropPickupListener implements Listener {
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (!player.isOp()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onArrowPickup(PlayerPickupArrowEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player)event.getWhoClicked();
            if (event.getSlotType().equals(InventoryType.SlotType.ARMOR)) {
                if (!player.isOp()) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
