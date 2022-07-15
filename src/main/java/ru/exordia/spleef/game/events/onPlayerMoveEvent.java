package ru.exordia.spleef.game.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ru.exordia.spleef.game.Game;

public class onPlayerMoveEvent implements Listener {

    @EventHandler public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (Game.INSTANCE.isGameStarting()) {
            Game.INSTANCE.checkPlayerDeath(player);
            Game.INSTANCE.checkPlayerWin(player);
        }
    }
}