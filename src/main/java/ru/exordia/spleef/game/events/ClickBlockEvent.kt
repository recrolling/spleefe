package ru.exordia.spleef.game.events

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import ru.exordia.spleef.game.commands.CreateArena
import ru.exordia.spleef.game.commands.Item

class ClickBlockEvent : Listener {
    private var count = 0
    private val item: Item = Item()
    @EventHandler
    fun onClickBlock(event: PlayerInteractEvent) {
        val player: Player = event.getPlayer()
        if (event.getAction() === Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().equals(item.createItem())) {
                count++
                if (count == 2) {
                    CreateArena.pos1 = event.getClickedBlock().getLocation()
                    player.sendMessage("§fУстановлена §bпервая §fпозиция")
                } else if (count == 4) {
                    CreateArena.pos2 = event.getClickedBlock().getLocation()
                    player.sendMessage("§fУстановлена §bвторая §fпозиция")
                }
            }
        }
        if (count == 5) {
            resetPositions()
            count = 1
        }
    }

    private fun resetPositions() {
        CreateArena.pos1 = null
        CreateArena.pos2 = null
    }
}