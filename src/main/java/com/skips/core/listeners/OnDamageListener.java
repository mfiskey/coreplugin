package com.skips.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class OnDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
//            Player player = (Player) event.getEntity();
//            ItemStack helmet = new ItemStack(player.getInventory().getHelmet());
//            ItemStack chestplate = new ItemStack(player.getInventory().getChestplate());
//            ItemStack leggings = new ItemStack(player.getInventory().getLeggings());
//            ItemStack boots = new ItemStack(player.getInventory().getBoots());
//
//            ItemMeta helmetDamage = helmet.getItemMeta();
//            ItemMeta chestplateDamage = chestplate.getItemMeta();
//            ItemMeta leggingsDamage = leggings.getItemMeta();
//            ItemMeta bootsDamage = boots.getItemMeta();
//
//            helmetDamage.setUnbreakable(true);

//            ((Damageable) helmetDamage).setDamage(0);
//            ((Damageable) chestplateDamage).setDamage(0);
//            ((Damageable) leggingsDamage).setDamage(0);
//            ((Damageable) bootsDamage).setDamage(0);

//            helmet.setItemMeta(helmetDamage);
//            chestplate.setItemMeta(chestplateDamage);
//            leggings.setItemMeta(leggingsDamage);
//            boots.setItemMeta(bootsDamage);
        }
        if (event.getDamager() instanceof Player) {
            Player player = ((Player) event.getDamager()).getPlayer();
//            ItemStack itemInHand = ((Player) event.getDamager()).getPlayer().getInventory().getItemInMainHand();
//            ItemMeta itemInHandItemMeta = itemInHand.getItemMeta();
//            ((Damageable) itemInHandItemMeta).setDamage(0);
//            itemInHand.setItemMeta(itemInHandItemMeta);
        }
    }
}
