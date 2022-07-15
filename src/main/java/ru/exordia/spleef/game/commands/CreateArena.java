package ru.exordia.spleef.game.commands;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CreateArena implements Commands {

    public static Location pos1;
    public static Location pos2;

    @Override public void onCommands(Player player, String[] args) {
        if (player.isOp() || player.hasPermission("spleef.admin")) {
            if (pos1 != null && pos2 != null) {
                //TODO write coords in config...
                player.sendMessage("§f[§bSpleef§f] Арена §aуспешно §fсоздана!");
            } else {
                if (pos1 == null)
                    player.sendMessage("§f[§bSpleef§f] Ошибка! вы не установили позиции");
                else {
                    player.sendMessage("§f[§bSpleef§f] Ошибка! не установлена §cвторая §fпозиция");
                }
            }
        } else
            player.sendMessage("§f[§bSpleef§f] §7Недостатачно прав.");
    }
}