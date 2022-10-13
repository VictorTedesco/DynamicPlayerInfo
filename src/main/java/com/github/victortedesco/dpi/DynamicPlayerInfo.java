package com.github.victortedesco.dpi;

import com.github.victortedesco.dpi.commands.DpiCommand;
import com.github.victortedesco.dpi.config.Config;
import com.github.victortedesco.dpi.config.ConfigHandler;
import com.github.victortedesco.dpi.listener.PlayerJoinListener;
import com.github.victortedesco.dpi.utils.ActionBarUtils;
import com.github.victortedesco.dpi.utils.Version;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicPlayerInfo extends JavaPlugin {

    public static DynamicPlayerInfo getInstance() {
        return getPlugin(DynamicPlayerInfo.class);
    }

    public static Version getVersion() {
        return Version.getServerVersion();
    }

    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage("[DynamicPlayerInfo] " + ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void createAndLoadConfigs() {
        ConfigHandler.createConfig("config");
        Config.loadConfigs();
    }

    @Override
    public void onEnable() {
        createAndLoadConfigs();
        if (getVersion() == Version.UNKNOWN) {
            for (String incompatible : Config.INCOMPATIBLE) {
                sendConsoleMessage(incompatible.replace("%server_version%", "(" + Version.getMinecraftVersion() + ")"));
            }
            Bukkit.getScheduler().runTaskLater(this, () -> getPluginLoader().disablePlugin(this), 1L);
            return;
        }
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getCommand("dpi").setExecutor(new DpiCommand());
        getCommand("dpi").setTabCompleter(new DpiCommand());
        ActionBarUtils.restartTasks();
        Metrics metrics = new Metrics(this, 16591);
        for (String enabled : Config.ENABLED) {
            sendConsoleMessage(enabled.replace("%plugin_version%", "(v" + getDescription().getVersion() + ")"));
        }
        sendConsoleMessage("&fMinecraft " + Version.getMinecraftVersion());
    }

    @Override
    public void onDisable() {
        for (String disabled : Config.DISABLED) {
            sendConsoleMessage(disabled.replace("%plugin_version%", "(v" + getDescription().getVersion() + ")"));
        }
    }
}
