package com.github.victortedesco.dpi.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Configuration extends ConfigurationHandler {

    private String info;
    private double updateTime;
    private double lineOfSight;
    private String adminPermission;
    private List<String> incompatible;
    private List<String> enabled;
    private List<String> disabled;
    private List<String> help;
    private String reloaded;
    private String withoutPermission;

    @Override
    public void loadFields() {
        super.createFile("config");
        FileConfiguration config = super.getFileConfiguration("config");

        // Configuration
        info = config.getString("info", "config.yml error");
        updateTime = config.getDouble("update-time", 0.25);
        lineOfSight = config.getDouble("line-of-sight", 10);
        adminPermission = config.getString("plugin.admin-permission", "dpi.admin");

        // Messages
        incompatible = config.getStringList("messages.incompatible");
        enabled = config.getStringList("messages.enabled");
        disabled = config.getStringList("messages.disabled");
        help = config.getStringList("messages.help");
        reloaded = ChatColor.translateAlternateColorCodes('&', config.getString("messages.reloaded", "&aPlugin successfully reloaded"));
        withoutPermission = ChatColor.translateAlternateColorCodes('&', config.getString("messages.without-permission", "&cYou don´t have permission to use this command."));
    }

    public String getInfo() {
        return info;
    }

    public double getUpdateTime() {
        return updateTime;
    }

    public double getLineOfSight() {
        return lineOfSight;
    }

    public String getAdminPermission() {
        return adminPermission;
    }

    public List<String> getIncompatible() {
        return incompatible;
    }

    public List<String> getEnabled() {
        return enabled;
    }

    public List<String> getDisabled() {
        return disabled;
    }

    public List<String> getHelp() {
        return help;
    }

    public String getReloaded() {
        return reloaded;
    }

    public String getWithoutPermission() {
        return withoutPermission;
    }
}
