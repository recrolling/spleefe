package ru.exordia.spleef.game.commands;

import org.bukkit.entity.Player;
import ru.exordia.spleef.game.Game;

public class ResetArena implements Commands {

    @Override public void onCommands(Player player, String[] args) {
        if (player.isOp() || player.hasPermission("spleef.admin")) {
            Game.INSTANCE.fillFloor();
        } else
            player.sendMessage("§f[§bSpleef§f] §7Недостатачно прав.");
    }
}