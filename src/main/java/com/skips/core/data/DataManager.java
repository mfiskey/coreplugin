package com.skips.core.data;

import com.skips.core.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DataManager {
    private Main plugin;
    private FileConfiguration fileConfiguration = null;
    private File file = null;

    public DataManager(Main plugin, String fileName) {
        this.plugin = plugin;
        saveDefaultConfig(fileName);
    }

    public void reloadConfig(String fileName) {
        if (this.file == null) this.file = new File(this.plugin.getDataFolder(), fileName);
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
        InputStream defaultStream = this.plugin.getResource(fileName);
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.fileConfiguration.setDefaults(defaultConfig);

        }
    }

    public FileConfiguration getConfig(String fileName) {
        if (this.fileConfiguration == null) {
            reloadConfig(fileName);
        }
        return this.fileConfiguration;
    }

    public void saveConfig(String fileName) {
        if (this.fileConfiguration == null || this.file == null) {
            return;
        }
        try {
            this.getConfig(fileName).save(this.file);
        } catch (IOException exception) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save config file to " + this.file, exception);
        }
    }

    public void saveDefaultConfig(String fileName) {
        if (this.file == null) {
            this.file = new File(this.plugin.getDataFolder(), fileName);
        }
        if (!this.file.exists()) {
            this.plugin.saveResource(fileName, false);
        }
    }

}
