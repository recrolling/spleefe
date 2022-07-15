package ru.exordia.spleef.game.events

import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import ru.exordia.spleef.game.Game
import ru.exordia.spleef.game.commands.CreateArena

class onBlockBreakEvent : Listener {
    @EventHandler
    fun onBreakBlock(event: BlockBreakEvent) {
        val player: Player = event.getPlayer()
        val pos1: Location = CreateArena.pos1
        val pos2: Location = CreateArena.pos2
        val x1: Double = pos1.getX()
        val x2: Double = pos2.getX()
        val z1: Double = pos1.getZ()
        val z2: Double = pos2.getZ()
        val xp: Double = player.getLocation().getX()
        val zp: Double = player.getLocation().getZ()
        if ((z1 <= zp && z2 >= zp && x2 <= xp && x1 >= xp || z1 >= zp && z2 <= zp && x2 <= xp && x1 >= xp || z1 >= zp && z2 <= zp && x2 >= xp && x1 <= xp || z1 <= zp && z2 >= zp && x2 >= xp && x1) <= xp) {
            if (!Game.INSTANCE.isGameStarting()) {
                event.setCancelled(true)
            } else event.setDropItems(false)
        }
    }
}