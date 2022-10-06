package com.github.victortedesco.dpi.commands;

import com.github.victortedesco.dpi.DynamicPlayerInfo;
import com.github.victortedesco.dpi.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        boolean performed = false;

        if (args.length != 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission(Config.ADMIN_PERMISSION)) {
                DynamicPlayerInfo.createAndLoadConfigs();
                sender.sendMessage(Config.RELOADED);
            } else {
                sender.sendMessage(Config.WITHOUT_PERMISSION);
            }
            performed = true;
        }
        if (!performed) {
            for (String help : Config.HELP) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
            }
        }
        return true;
    }
}
