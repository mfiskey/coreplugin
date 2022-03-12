package com.skips.core.main;

import com.skips.core.commands.Arena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    private final ConsoleCommandSender ccs = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.ccs.sendMessage(ChatColor.GOLD +"*********************************************");
        this.ccs.sendMessage(ChatColor.GOLD + "******* " + ChatColor.WHITE + "CORE plugin up and running!" + ChatColor.GOLD + " *********");
        this.ccs.sendMessage(ChatColor.GOLD + "*********************************************");
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("arena").setExecutor(new Arena());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
