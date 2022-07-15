package ru.exordia.spleef.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import ru.exordia.spleef.Spleef;
import ru.exordia.spleef.game.commands.CreateArena;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static Game INSTANCE = new Game();

    public static final int MAX_PLAYERS = 2;

    private int schedulerId;
    private int counter;
    private boolean gameStarting;
    public Spleef spleef;
    public Location loseLocation;
    public Location winLocation;

    public List<Location> spawnLocations = new ArrayList<>();
    public List<Player> joinPlayer = new ArrayList<>();

    public void checkPlayerDeath(Player player) {
        if (player.getLocation().getY() < CreateArena.pos1.getY() - 2) {
            joinPlayer.remove(player);
            player.teleport(loseLocation);
        }
    }

    public void checkPlayerWin(Player player) {
        if (joinPlayer.size() == 1) {
            Player winPlayer = joinPlayer.get(0);
            winPlayer.teleport(winLocation);

            Bukkit.getServer().broadcastMessage("§f[§bSpleef§f] Игрок §6" + winPlayer.getName() + " §fпобедил!");

            endGame();
        }
    }

    public void endGame() {
        gameStarting = false;
        joinPlayer.clear();
        fillFloor();

    }

    public void fillFloor() {

        Location loc1 =  CreateArena.pos1;
        Location loc2 =  CreateArena.pos2;

        List<Block> blocks = new ArrayList<>();

        int x1 = loc1.getBlockX();
        int y1 = loc1.getBlockY();
        int z1 = loc1.getBlockZ();
        int x2 = loc2.getBlockX();
        int y2 = loc2.getBlockY();
        int z2 = loc2.getBlockZ();

        int xMin, yMin, zMin;
        int xMax, yMax, zMax;
        int x, y, z;

        if (x1 > x2) {
            xMin = x2;
            xMax = x1;
        } else {
            xMin = x1;
            xMax = x2;
        }

        if (y1 > y2) {
            yMin = y2;
            yMax = y1;
        } else {
            yMin = y1;
            yMax = y2;
        }

        if (z1 > z2) {
            zMin = z2;
            zMax = z1;
        } else {
            zMin = z1;
            zMax = z2;
        }

        for (x = xMin; x <= xMax; x++) {
            for (y = yMin; y <= yMax; y++) {
                for (z = zMin; z <= zMax; z++) {
                    Block b = new Location(loc1.getWorld(), x, y, z).getBlock();
                    blocks.add(b);
                }
            }
        }

        for (Block block : blocks) {
            block.setType(Material.SNOW_BLOCK);
        }
    }


    public void startGame() {
        gameStarting = true;

        schedulerId = Bukkit.getScheduler().scheduleSyncRepeatingTask(spleef, () -> {
            counter++;
            for (Player player : joinPlayer) {
                player.sendTitle(" ", "§a" + counter, 10, 10, 10);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 5);
                if (counter == 4) {
                    player.sendTitle("§eSTART", "", 10, 10, 10);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100, 5);
                    Bukkit.getScheduler().cancelTask(schedulerId);
                    counter = 0;
                }
            }
        }, 3, 20);
    }

    public boolean isGameStarting() {
        return gameStarting;
    }
}