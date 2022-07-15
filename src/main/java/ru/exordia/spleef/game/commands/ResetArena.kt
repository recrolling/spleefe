package ru.exordia.spleef.game.commands

import org.bukkit.entity.Player
import ru.exordia.spleef.game.Game

class ResetArena : Commands {
    @Override
    override fun onCommands(player: Player, args: Array<String?>?) {
        if (player.isOp() || player.hasPermission("spleef.admin")) {
            Game.INSTANCE.fillFloor()
        } else player.sendMessage("§f[§bSpleef§f] §7Недостатачно прав.")
    }
}