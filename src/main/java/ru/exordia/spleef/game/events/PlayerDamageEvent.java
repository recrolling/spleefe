package ru.exordia.spleef.game.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import ru.exordia.spleef.game.Game;

public class PlayerDamageEvent implements Listener {

    @EventHandler public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (Game.INSTANCE.joinPlayer.contains(player))
                event.setCancelled(true);
        }
    }
}
