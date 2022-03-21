package com.skips.core.listeners;

import com.skips.core.procedures.ArrowRechargeProcedure;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class OnShootListener implements Listener {

    public static HashMap<UUID, Integer> inProgress = new HashMap<>();

    @EventHandler
    public void onArrowShoot(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = ((Player) event.getEntity());

//            ItemStack itemInHand = p.getInventory().getItemInMainHand();
//            ItemMeta itemInHandItemMeta = itemInHand.getItemMeta();
//            ((Damageable) itemInHandItemMeta).setDamage(0);
//            itemInHand.setItemMeta(itemInHandItemMeta);

            inProgress.putIfAbsent(p.getUniqueId(), 1);
            if (inProgress.get(p.getUniqueId()) == 1) {
                ArrowRechargeProcedure.arrowRecharge(p);
            }
        }
    }
}
