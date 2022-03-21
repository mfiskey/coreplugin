package com.skips.core.procedures;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitClassProcedureTemp implements Listener {

    public static void setKit(Player player) {
        ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack bow = new ItemStack(Material.BOW, 1);

        ItemMeta helmetDamage = helmet.getItemMeta();
        ItemMeta chestplateDamage = chestplate.getItemMeta();
        ItemMeta leggingsDamage = leggings.getItemMeta();
        ItemMeta bootsDamage = boots.getItemMeta();
        ItemMeta swordDamage = sword.getItemMeta();
        ItemMeta bowDamage = bow.getItemMeta();

        helmetDamage.setUnbreakable(true);
        chestplateDamage.setUnbreakable(true);
        leggingsDamage.setUnbreakable(true);
        bootsDamage.setUnbreakable(true);
        swordDamage.setUnbreakable(true);
        bowDamage.setUnbreakable(true);

        helmet.setItemMeta(helmetDamage);
        chestplate.setItemMeta(chestplateDamage);
        leggings.setItemMeta(leggingsDamage);
        boots.setItemMeta(bootsDamage);
        sword.setItemMeta(swordDamage);
        bow.setItemMeta(bowDamage);

        player.getInventory().clear();
        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
        player.getInventory().setItem(0, sword);
        player.getInventory().setItem(1, bow);
        player.getInventory().setItem(2, new ItemStack(Material.ARROW, 16));

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = event.getEntity();
            player.getInventory().clear();
            KitClassProcedureTemp.setKit(player);
        }
    }
}
