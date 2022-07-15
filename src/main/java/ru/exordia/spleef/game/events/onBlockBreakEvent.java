package ru.exordia.spleef.game.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import ru.exordia.spleef.game.Game;
import ru.exordia.spleef.game.commands.CreateArena;

public class onBlockBreakEvent implements Listener {

    @EventHandler public void onBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();

        Location pos1 = CreateArena.pos1;
        Location pos2 = CreateArena.pos2;


        double x1 = pos1.getX();
        double x2 = pos2.getX();

        double z1 = pos1.getZ();
        double z2 = pos2.getZ();

        double xp = player.getLocation().getX();
        double zp = player.getLocation().getZ();

        if (z1 <= zp && z2 >= zp && x2 <= xp && x1 >= xp
                || z1 >= zp && z2 <= zp && x2 <= xp && x1 >= xp
                || z1 >= zp && z2 <= zp && x2 >= xp && x1 <= xp
                || z1 <= zp && z2 >= zp && x2 >= xp && x1 <= xp) {
            if (!Game.INSTANCE.isGameStarting()) {
                event.setCancelled(true);
            } else
                event.setDropItems(false);
        }
    }
}