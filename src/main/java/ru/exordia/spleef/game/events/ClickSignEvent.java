package ru.exordia.spleef.game.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import ru.exordia.spleef.game.Game;

public class ClickSignEvent implements Listener {

    @EventHandler public void onClickSign(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clicked = event.getClickedBlock();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (clicked.getType() == Material.OAK_WALL_SIGN || clicked.getType() == Material.OAK_SIGN) {
                Sign sign = (Sign) clicked.getState();
                if (sign.getLine(0).equals("Войти")) {
                    player.performCommand("spleef join");
                    event.setCancelled(true);
                }
            }
        }
    }
}