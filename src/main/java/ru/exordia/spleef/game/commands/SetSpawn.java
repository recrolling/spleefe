package ru.exordia.spleef.game.commands;

import org.bukkit.entity.Player;
import ru.exordia.spleef.game.Game;

public class SetSpawn implements Commands {

    @Override public void onCommands(Player player, String[] args) {
        if (player.isOp() || player.hasPermission("spleef.admin")) {
            player.sendMessage("Точка спавна установлена");
            Game.INSTANCE.spawnLocations.add(player.getLocation());
        } else
            player.sendMessage("§f[§bSpleef§f] §7Недостатачно прав.");
    }
}
