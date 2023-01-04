package com.github.victortedesco.dpi.config;

import com.github.victortedesco.dpi.DynamicPlayerInfo;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public abstract class ConfigurationHandler {

    public final void createFile(String file) {
        if (!new File(DynamicPlayerInfo.getInstance().getDataFolder(), file + ".yml").exists()) {
            DynamicPlayerInfo.getInstance().saveResource(file + ".yml", false);
        }
    }

    public final FileConfiguration getFileConfiguration(String file) {
        final File newFile = new File(DynamicPlayerInfo.getInstance().getDataFolder() + File.separator + file + ".yml");
        return YamlConfiguration.loadConfiguration(newFile);
    }

    public abstract void loadFields();
}