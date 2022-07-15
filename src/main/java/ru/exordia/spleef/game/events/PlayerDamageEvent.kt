package ru.exordia.spleef.game.events

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import ru.exordia.spleef.game.Game

class PlayerDamageEvent : Listener {
    @EventHandler
    fun onPlayerDamage(event: EntityDamageEvent) {
        if (event.getEntity() is Player) {
            val player: Player = event.getEntity() as Player
            if (Game.INSTANCE.joinPlayer.contains(player)) event.setCancelled(true)
        }
    }
}