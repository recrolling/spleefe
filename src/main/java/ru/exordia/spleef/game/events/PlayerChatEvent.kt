package ru.exordia.spleef.game.events

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import ru.exordia.spleef.game.Game

class PlayerChatEvent : Listener {
    @EventHandler
    fun onChat(event: PlayerCommandPreprocessEvent) {
        val player: Player = event.getPlayer()
        val message: String = event.getMessage()
        if (message.startsWith("/spleef") || player.isOp() || player.hasPermission("spleef.admin")) return
        if (Game.INSTANCE.joinPlayer.contains(player)) event.setCancelled(true)
    }
}