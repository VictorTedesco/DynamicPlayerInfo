package com.github.victortedesco.dpi.utils;

import com.cryptomorin.xseries.messages.ActionBar;
import com.github.victortedesco.dpi.DynamicPlayerInfo;
import com.github.victortedesco.dpi.config.Configuration;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public final class ActionBarManager {

    private void sendMessage(Player player) {
        if (player == null || !player.isOnline()) return;
        Player target = null;
        Configuration config = DynamicPlayerInfo.getConfiguration();

        for (Player newTarget : player.getWorld().getPlayers()) {
            Vector vector = newTarget.getLocation().toVector().subtract(player.getLocation().toVector());
            if (player.getLocation().getDirection().normalize().crossProduct(vector).lengthSquared() < 1.0 && vector.normalize().dot(player.getLocation().getDirection().normalize()) >= 0.0) {
                if (target == null || target.getLocation().distanceSquared(player.getLocation()) > newTarget.getLocation().distanceSquared(player.getLocation()))
                    target = newTarget;
                if (player.getLocation().distance(target.getLocation()) > config.getLineOfSight() || !player.canSee(target) || target.hasPotionEffect(PotionEffectType.INVISIBILITY) || target.getGameMode() == GameMode.SPECTATOR)
                    continue;
                ActionBar.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(target, config.getInfo())));
            }
        }
    }

    public void createTask(Player player) {
        double time = DynamicPlayerInfo.getConfiguration().getUpdateTime();
        if (time < 0) time = 0.05;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) cancel();
                sendMessage(player);
            }
        }.runTaskTimerAsynchronously(DynamicPlayerInfo.getInstance(), 1L, Math.round(20L * time));
    }

    public void restartTasks() {
        Bukkit.getScheduler().cancelTasks(DynamicPlayerInfo.getInstance());
        for (Player player : Bukkit.getOnlinePlayers()) createTask(player);
    }
}
