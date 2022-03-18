package com.skips.core.procedures;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class KitClassProcedureTemp implements Listener {
    public static void setKit(Player player) {
        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));
        player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
        player.getInventory().setItem(1, new ItemStack(Material.BOW, 1));
        player.getInventory().setItem(2, new ItemStack(Material.ARROW, 16));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player)event.getEntity();
            player.getInventory().clear();
            KitClassProcedureTemp.setKit(player);
        }

    }
}
