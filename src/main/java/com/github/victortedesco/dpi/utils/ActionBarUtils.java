package com.github.victortedesco.dpi.utils;

import com.cryptomorin.xseries.messages.ActionBar;
import com.github.victortedesco.dpi.DynamicPlayerInfo;
import com.github.victortedesco.dpi.config.Config;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ActionBarUtils {

    static <T extends Player> void sendMessage(Player player, Iterable<T> players) {
        if (player == null) {
            return;
        }
        T target = null;
        for (T newTarget : players) {
            Vector vector = newTarget.getLocation().toVector().subtract(player.getLocation().toVector());
            if (player.getLocation().getDirection().normalize().crossProduct(vector).lengthSquared() < 1.0 && vector.normalize().dot(player.getLocation().getDirection().normalize()) >= 0.0) {
                if (target == null || target.getLocation().distanceSquared(player.getLocation()) > newTarget.getLocation().distanceSquared(player.getLocation()))
                    target = newTarget;
                if (player.getLocation().distance(target.getLocation()) > Config.LINE_OF_SIGHT || !player.canSee(target) || target.hasPotionEffect(PotionEffectType.INVISIBILITY) || target.getGameMode() == GameMode.SPECTATOR)
                    continue;
                ActionBar.sendActionBar(player, PlaceholderAPI.setPlaceholders(target, Config.INFO));
            }
        }
    }

    public static void createRunnable(Player player) {
        double time = Config.UPDATE_TIME;
        if (time < 0) time = 0.1;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) cancel();
                sendMessage(player, player.getWorld().getPlayers());
            }
        }.runTaskTimerAsynchronously(DynamicPlayerInfo.getInstance(), 1L, (long) (20L * time));
    }

    public static void restartTasks() {
        Bukkit.getScheduler().cancelTasks(DynamicPlayerInfo.getInstance());
        for (Player player : Bukkit.getOnlinePlayers()) {
            ActionBarUtils.createRunnable(player);
        }
    }
}
