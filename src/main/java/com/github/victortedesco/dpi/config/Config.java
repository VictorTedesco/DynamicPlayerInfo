package com.github.victortedesco.dpi.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {

    public static List<String> INCOMPATIBLE;
    public static List<String> ENABLED;
    public static List<String> DISABLED;
    public static List<String> HELP;
    public static String INFO;
    public static String WITHOUT_PERMISSION;
    public static String RELOADED;
    public static String ADMIN_PERMISSION;

    @SuppressWarnings("ConstantConditions")
    public static void loadConfigs() {
        FileConfiguration config = ConfigHandler.getConfig("config");

        INFO = ChatColor.translateAlternateColorCodes('&', config.getString("info"));
        ADMIN_PERMISSION = ChatColor.translateAlternateColorCodes('&', config.getString("plugin.admin-permission"));

        INCOMPATIBLE = config.getStringList("messages.incompatible");
        ENABLED = config.getStringList("messages.enabled");
        DISABLED = config.getStringList("messages.disabled");
        HELP = config.getStringList("messages.help");
        RELOADED = ChatColor.translateAlternateColorCodes('&', config.getString("messages.reloaded"));
        WITHOUT_PERMISSION = ChatColor.translateAlternateColorCodes('&', config.getString("messages.without-permission"));
    }
}
