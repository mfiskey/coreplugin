package com.skips.core.main;

import com.skips.core.commands.SetArenaCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("******* CORE plugin up and running! *******");
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("arena").setExecutor(new SetArenaCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
