package ru.exordia.spleef.game.commands

import org.bukkit.entity.Player
import ru.exordia.spleef.game.Game

class SetWin : Commands {
    @Override
    override fun onCommands(player: Player, args: Array<String?>?) {
        if (player.isOp() || player.hasPermission("spleef.admin")) {
            Game.INSTANCE.winLocation = player.getLocation()
            player.sendMessage("§fУстановлен спавн для победителя")
        } else player.sendMessage("§f[§bSpleef§f] §7Недостатачно прав.")
    }
}