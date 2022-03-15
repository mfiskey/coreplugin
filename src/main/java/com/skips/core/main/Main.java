package com.skips.core.main;
import com.skips.core.commands.ArenaCommand;
import com.skips.core.commands.SetSpawnCommand;
import com.skips.core.commands.SpawnCommand;
import com.skips.core.data.DataManager;
import com.skips.core.listeners.*;
import com.skips.core.procedures.ClearEntitiesProcedure;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin implements Listener {

    public static final ConsoleCommandSender ccs = Bukkit.getConsoleSender();
    private static Main plugin;

    public DataManager config;
    public static DataManager spawnData;
    public static DataManager playerStatsData;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.ccs.sendMessage(ChatColor.GOLD +"***********************************************");
        this.ccs.sendMessage(ChatColor.GOLD + "******** " + ChatColor.WHITE + "CORE plugin up and running!" + ChatColor.GOLD + " **********");
        this.ccs.sendMessage(ChatColor.GOLD + "***********************************************");

        this.plugin = this;

        this.config = new DataManager(this, "config.yml");
        this.spawnData = new DataManager(this, "spawn.yml");
        this.playerStatsData = new DataManager(this, "playerStats.yml");

        this.getCommand("arena").setExecutor(new ArenaCommand());
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand());
        this.getServer().getPluginManager().registerEvents(new SpawnListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnJoinLeaveListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnBlockBreakListener(), this);
        this.getServer().getPluginManager().registerEvents(new FoodLevelListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerItemDropPickupListener(), this);
        this.getServer().getPluginManager().registerEvents(new ScoreboardListener(), this);

        ClearEntitiesProcedure.clearAllEntities();
    }

    public static Main getPlugin() {
        return plugin;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
