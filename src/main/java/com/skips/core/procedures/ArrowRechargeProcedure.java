package com.skips.core.procedures;

import com.skips.core.listeners.OnShootListener;
import com.skips.core.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.UUID;

public class ArrowRechargeProcedure {

    public static final HashMap<UUID, Float> xpBarStatus = new HashMap<>();

    public static void arrowRecharge(Player p) {
        xpBarStatus.putIfAbsent(p.getUniqueId(), 0.0F);
        if (!p.getInventory().containsAtLeast(new ItemStack(Material.ARROW), 17)) {

            new BukkitRunnable() {
                @Override
                public void run() {
                    OnShootListener.inProgress.put(p.getUniqueId(), 2);
                    xpBarStatus.put(p.getUniqueId(), xpBarStatus.get(p.getUniqueId()) + 0.01F);
                    p.setExp(xpBarStatus.get(p.getUniqueId()));
                    if (xpBarStatus.get(p.getUniqueId()) >= 0.98F) {
                        xpBarStatus.put(p.getUniqueId(), 0.0F);
                        p.setExp(xpBarStatus.get(p.getUniqueId()));
                        p.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                    }
                    if (p.getInventory().contains(Material.ARROW, 16)) {
                        OnShootListener.inProgress.remove(p.getUniqueId());
                        this.cancel();
                    }
                    if (!p.isOnline()) {
                        OnShootListener.inProgress.remove(p.getUniqueId());
                        ArrowRechargeProcedure.xpBarStatus.put(p.getUniqueId(), 0.0F);
                        this.cancel();
                    }
                }
            }.runTaskTimer(Main.getPlugin(), 0, 2);
        }
    }
}
