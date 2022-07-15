package ru.exordia.spleef.game.commands;

import org.bukkit.entity.Player;
import ru.exordia.spleef.game.Game;

public class Leave implements Commands {

    @Override public void onCommands(Player player, String[] args) {
       int size = Game.INSTANCE.joinPlayer.size();
        int maxPlayers = Game.MAX_PLAYERS;

        if (Game.INSTANCE.joinPlayer.contains(player)) {
            Game.INSTANCE.joinPlayer.remove(player);
            player.sendMessage("§f[§bSpleef§f] Вы вышли с игры");
            for (Player players : Game.INSTANCE.joinPlayer) {
                players.sendMessage("§f[§bSpleef§f] Игрок §c" + player.getName() + "§f вышел с игры [" + Game.INSTANCE.joinPlayer.size() + "/" + maxPlayers + "]");
            }
        } else {
            player.sendMessage("§f[§bSpleef§f] Вы не находитесь в игре");
        }
    }
}