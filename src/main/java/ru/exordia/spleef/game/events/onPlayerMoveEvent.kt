package ru.exordia.spleef.game.events

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import ru.exordia.spleef.game.Game

class onPlayerMoveEvent : Listener {
    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        val player: Player = event.getPlayer()
        if (Game.INSTANCE.isGameStarting()) {
            Game.INSTANCE.checkPlayerDeath(player)
            Game.INSTANCE.checkPlayerWin(player)
        }
    }
}