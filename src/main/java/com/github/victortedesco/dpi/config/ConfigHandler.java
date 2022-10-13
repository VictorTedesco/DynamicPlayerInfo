package com.github.victortedesco.dpi.config;

import com.github.victortedesco.dpi.DynamicPlayerInfo;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigHandler {

    public static void createConfig(String file) {
        if (!new File(DynamicPlayerInfo.getInstance().getDataFolder(), file + ".yml").exists()) {
            DynamicPlayerInfo.getInstance().saveResource(file + ".yml", false);
        }
    }

    public static FileConfiguration getConfig(String file) {
        File new_file = new File(DynamicPlayerInfo.getInstance().getDataFolder() + File.separator + file + ".yml");
        return YamlConfiguration.loadConfiguration(new_file);
    }
}

