package com.github.victortedesco.dpi;

import com.github.victortedesco.dpi.commands.DpiCommand;
import com.github.victortedesco.dpi.config.Configuration;
import com.github.victortedesco.dpi.listener.PlayerJoinListener;
import com.github.victortedesco.dpi.utils.ActionBarManager;
import com.github.victortedesco.dpi.utils.Version;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicPlayerInfo extends JavaPlugin {

    private final static Configuration CONFIGURATION = new Configuration();
    private final static ActionBarManager ACTION_BAR_MANAGER = new ActionBarManager();

    public static DynamicPlayerInfo getInstance() {
        return getPlugin(DynamicPlayerInfo.class);
    }

    public static Configuration getConfiguration() {
        return CONFIGURATION;
    }

    public static ActionBarManager getActionBarManager() {
        return ACTION_BAR_MANAGER;
    }

    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage("[DynamicPlayerInfo] " + ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public void onEnable() {
        getConfiguration().loadFields();
        if (Version.getServerVersion() == Version.UNKNOWN) {
            for (String incompatible : getConfiguration().getIncompatible()) sendConsoleMessage(incompatible);
            Bukkit.getScheduler().runTaskLater(this, () -> getPluginLoader().disablePlugin(this), 1L);
            return;
        }
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getCommand("dpi").setExecutor(new DpiCommand());
        getCommand("dpi").setTabCompleter(new DpiCommand());
        getActionBarManager().restartTasks();
        Metrics metrics = new Metrics(this, 16591);
        for (String enabled : getConfiguration().getEnabled()) sendConsoleMessage(enabled);
        sendConsoleMessage("&fMinecraft " + Version.getMinecraftVersion());
    }

    @Override
    public void onDisable() {
        for (String disabled : getConfiguration().getDisabled()) sendConsoleMessage(disabled);
    }
}
