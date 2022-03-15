package com.skips.core.procedures;

import com.skips.core.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ClearEntitiesProcedure {
    public static void clearAllEntities() {
        Main.getPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
            Bukkit.getWorld("world").getEntities().forEach(entity -> {
                if(!(entity instanceof Player)) {
                    entity.remove();
                }
            });

        }, 6000, 6000);
    }
}
