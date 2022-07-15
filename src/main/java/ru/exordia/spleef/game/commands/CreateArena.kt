package ru.exordia.spleef.game.commands

import org.bukkit.Location
import org.bukkit.entity.Player

class CreateArena : Commands {
    @Override
    override fun onCommands(player: Player, args: Array<String?>?) {
        if (player.isOp() || player.hasPermission("spleef.admin")) {
            if (pos1 != null && pos2 != null) {
                //TODO write coords in config...
                player.sendMessage("§f[§bSpleef§f] Арена §aуспешно §fсоздана!")
            } else {
                if (pos1 == null) player.sendMessage("§f[§bSpleef§f] Ошибка! вы не установили позиции") else {
                    player.sendMessage("§f[§bSpleef§f] Ошибка! не установлена §cвторая §fпозиция")
                }
            }
        } else player.sendMessage("§f[§bSpleef§f] §7Недостатачно прав.")
    }

    companion object {
        var pos1: Location? = null
        var pos2: Location? = null
    }
}