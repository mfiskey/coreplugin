package com.skips.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("******* CORE plugin up and running! Working! *******");
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("arena").setExecutor(new SetArenaCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
