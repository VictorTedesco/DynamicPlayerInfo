package com.github.victortedesco.dpi.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {

    public static String INFO;
    public static double UPDATE_TIME;
    public static double LINE_OF_SIGHT;
    public static String ADMIN_PERMISSION;
    public static List<String> INCOMPATIBLE;
    public static List<String> ENABLED;
    public static List<String> DISABLED;
    public static List<String> HELP;
    public static String RELOADED;
    public static String WITHOUT_PERMISSION;


    @SuppressWarnings("ConstantConditions")
    public static void loadConfigs() {
        FileConfiguration config = ConfigHandler.getConfig("config");

        // Configuration
        INFO = ChatColor.translateAlternateColorCodes('&', config.getString("info"));
        UPDATE_TIME = config.getDouble("update-time");
        LINE_OF_SIGHT = config.getDouble("line-of-sight");
        ADMIN_PERMISSION = config.getString("plugin.admin-permission");

        // Messages
        INCOMPATIBLE = config.getStringList("messages.incompatible");
        ENABLED = config.getStringList("messages.enabled");
        DISABLED = config.getStringList("messages.disabled");
        HELP = config.getStringList("messages.help");
        RELOADED = ChatColor.translateAlternateColorCodes('&', config.getString("messages.reloaded"));
        WITHOUT_PERMISSION = ChatColor.translateAlternateColorCodes('&', config.getString("messages.without-permission"));
    }
}
