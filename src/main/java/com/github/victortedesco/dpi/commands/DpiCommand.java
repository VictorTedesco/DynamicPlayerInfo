package com.github.victortedesco.dpi.commands;

import com.github.victortedesco.dpi.DynamicPlayerInfo;
import com.github.victortedesco.dpi.config.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DpiCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        boolean performed = false;
        Configuration config = DynamicPlayerInfo.getConfiguration();

        if (sender.hasPermission(config.getAdminPermission())) {
            if (args.length != 0 && args[0].equalsIgnoreCase("reload")) {
                DynamicPlayerInfo.getConfiguration().loadFields();
                DynamicPlayerInfo.getActionBarManager().restartTasks();
                sender.sendMessage(config.getReloaded());
                performed = true;
            }
        } else {
            sender.sendMessage(config.getWithoutPermission());
            performed = true;
        }
        if (!performed) {
            for (String help : config.getHelp()) sender.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        final String[] COMPLETIONS = {"reload"};

        if (sender.hasPermission(DynamicPlayerInfo.getConfiguration().getAdminPermission())) {
            if (args.length == 1) {
                final List<String> completions = new ArrayList<>();

                StringUtil.copyPartialMatches(args[0], Arrays.asList(COMPLETIONS), completions);
                Collections.sort(completions);
                return completions;
            }
        }
        return Collections.emptyList();
    }
}
