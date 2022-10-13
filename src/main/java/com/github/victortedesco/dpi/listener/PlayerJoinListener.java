package com.github.victortedesco.dpi.listener;

import com.github.victortedesco.dpi.utils.ActionBarUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ActionBarUtils.createRunnable(player);
    }
}
