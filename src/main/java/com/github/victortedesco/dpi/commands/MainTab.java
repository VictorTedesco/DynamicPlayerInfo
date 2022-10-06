package com.github.victortedesco.dpi.commands;

import com.github.victortedesco.dpi.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainTab implements TabCompleter {

    private final String[] COMPLETIONS = {"reload"};

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(Config.ADMIN_PERMISSION)) {
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
