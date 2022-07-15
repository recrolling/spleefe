package ru.exordia.spleef.game.commands

import org.bukkit.entity.Player
import ru.exordia.spleef.game.Game

class Leave : Commands {
    @Override
    override fun onCommands(player: Player, args: Array<String?>?) {
        val size: Int = Game.INSTANCE.joinPlayer.size()
        val maxPlayers: Int = Game.MAX_PLAYERS
        if (Game.INSTANCE.joinPlayer.contains(player)) {
            Game.INSTANCE.joinPlayer.remove(player)
            player.sendMessage("§f[§bSpleef§f] Вы вышли с игры")
            for (players in Game.INSTANCE.joinPlayer) {
                players.sendMessage("§f[§bSpleef§f] Игрок §c" + player.getName() + "§f вышел с игры [" + Game.INSTANCE.joinPlayer.size() + "/" + maxPlayers + "]")
            }
        } else {
            player.sendMessage("§f[§bSpleef§f] Вы не находитесь в игре")
        }
    }
}