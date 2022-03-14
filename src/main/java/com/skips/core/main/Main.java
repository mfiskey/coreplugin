package com.skips.core.main;
import com.skips.core.commands.ArenaCommand;
import com.skips.core.commands.SetSpawnCommand;
import com.skips.core.commands.SpawnCommand;
import com.skips.core.listeners.FoodLevelListener;
import com.skips.core.listeners.OnBlockBreakListener;
import com.skips.core.listeners.OnJoinLeaveListener;
import com.skips.core.listeners.SpawnListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    public static final ConsoleCommandSender ccs = Bukkit.getConsoleSender();
    private static Main plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.ccs.sendMessage(ChatColor.GOLD +"***********************************************");
        this.ccs.sendMessage(ChatColor.GOLD + "******** " + ChatColor.WHITE + "CORE plugin up and running!" + ChatColor.GOLD + " **********");
        this.ccs.sendMessage(ChatColor.GOLD + "***********************************************");
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();

        plugin = this;

        this.getCommand("arena").setExecutor(new ArenaCommand());
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand());
        this.getServer().getPluginManager().registerEvents(new SpawnListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnJoinLeaveListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnBlockBreakListener(), this);
        this.getServer().getPluginManager().registerEvents(new FoodLevelListener(), this);






    }

    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
