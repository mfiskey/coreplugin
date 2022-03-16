package com.skips.core.procedures;

import com.skips.core.data.Cuboid;
import com.skips.core.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SetArenaSpawnProcedure {
    public static Cuboid cuboid;

    public static void setArenaSpawn() {
        FileConfiguration arenaSpawnData = Main.arenaSpawnData.getConfig("arenaSpawn.yml");
        if (arenaSpawnData.get("pos1.x") == null || arenaSpawnData.get("pos1.y") == null || arenaSpawnData.get("pos1.z") == null
                || arenaSpawnData.get("pos2.x") == null || arenaSpawnData.get("pos2.y") == null || arenaSpawnData.get("pos2.z") == null) {
            Main.ccs.sendMessage(ChatColor.RED + "Please set an arena spawn!");

            arenaSpawnData.set("pos1.x", 0);
            arenaSpawnData.set("pos1.y", 0);
            arenaSpawnData.set("pos1.z", 0);
            arenaSpawnData.set("pos2.x", 0);
            arenaSpawnData.set("pos2.y", 0);
            arenaSpawnData.set("pos2.z", 0);
            Main.arenaSpawnData.saveConfig("arenaSpawn.yml");
        }
        else {
            int pos1X = (Integer) arenaSpawnData.get("pos1.x");
            int pos1Y = (Integer) arenaSpawnData.get("pos1.y");
            int pos1Z = (Integer) arenaSpawnData.get("pos1.z");
            int pos2X = (Integer) arenaSpawnData.get("pos2.x");
            int pos2Y = (Integer) arenaSpawnData.get("pos2.y");
            int pos2Z = (Integer) arenaSpawnData.get("pos2.z");

            cuboid = new Cuboid(
                    new Location(Bukkit.getWorld("world"), pos1X, pos1Y, pos1Z),
                    new Location(Bukkit.getWorld("world"), pos2X, pos2Y, pos2Z));

        }
    }
}
