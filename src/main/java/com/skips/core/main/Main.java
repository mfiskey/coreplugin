package com.skips.core.main;
import com.skips.core.commands.SetSpawnCommand;
import com.skips.core.commands.SpawnCommand;
import com.skips.core.commands.StatsResetCommand;
import com.skips.core.data.DataManager;
import com.skips.core.listeners.*;
import com.skips.core.procedures.ArrowRechargeProcedure;
import com.skips.core.procedures.ClearEntitiesProcedure;
import com.skips.core.procedures.SetArenaSpawnProcedure;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    public static final ConsoleCommandSender ccs = Bukkit.getConsoleSender();
    private static Main plugin;

    public DataManager config;
    public static DataManager spawnData;
    public static DataManager playerStatsData;
    public static DataManager arenaSpawnData;

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
        this.arenaSpawnData = new DataManager(this, "arenaSpawn.yml");

        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand());
        this.getCommand("stats").setExecutor(new StatsResetCommand());

        this.getServer().getPluginManager().registerEvents(new SpawnListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnJoinLeaveListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnBlockBreakListener(), this);
        this.getServer().getPluginManager().registerEvents(new FoodLevelListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerItemDropPickupListener(), this);
        this.getServer().getPluginManager().registerEvents(new SideBarListener(), this);
        this.getServer().getPluginManager().registerEvents(new ArenaSpawnListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnChatListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnDeathListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnShootListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnDamageListener(), this);

        SetArenaSpawnProcedure.setArenaSpawn();
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
