package ru.exordia.spleef.game.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.exordia.spleef.game.Game;

import java.util.List;
import java.util.Random;

public class Join implements Commands {
    private final Random random = new Random();

    @Override public void onCommands(Player player, String[] args) {
        int maxPlayers = Game.MAX_PLAYERS;
        List<Location> locs = Game.INSTANCE.spawnLocations;

        if (Game.INSTANCE.joinPlayer.size() != maxPlayers) {
            if (!Game.INSTANCE.joinPlayer.contains(player)) {
                giveShovel(player);
                Game.INSTANCE.joinPlayer.add(player);
                for (Player players : Game.INSTANCE.joinPlayer) {
                    players.sendMessage("§f[§bSpleef§f] Игрок §9" + player.getName() + " §fприсоеденился к игре [" + Game.INSTANCE.joinPlayer.size() + "/" + maxPlayers + "]");
                }
                player.teleport(locs.get(random.nextInt(locs.size())));
                if (Game.INSTANCE.joinPlayer.size() == Game.MAX_PLAYERS) {
                    Game.INSTANCE.startGame();
                }
            } else {
                player.sendMessage("§f[§bSpleef§f] Вы уже в игре!");
            }
        } else {
            player.sendMessage("§f[§bSpleef§f] Игра уже началась");
        }
    }

    private void giveShovel(Player player) {
        ItemStack item = new ItemStack(Material.DIAMOND_SHOVEL);
        item.addEnchantment(Enchantment.DIG_SPEED, 5);

        player.getInventory().setItem(0, item);
    }
}