package ru.exordia.spleef.game

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.block.Block
import org.bukkit.entity.Player
import ru.exordia.spleef.Spleef
import ru.exordia.spleef.game.commands.CreateArena
import java.util.ArrayList
import java.util.List

class Game {
    private var schedulerId = 0
    private var counter = 0
    var isGameStarting = false
        private set
    var spleef: Spleef? = null
    var loseLocation: Location? = null
    var winLocation: Location? = null
    var spawnLocations: List<Location> = ArrayList()
    var joinPlayer: List<Player> = ArrayList()
    fun checkPlayerDeath(player: Player) {
        if (player.getLocation().getY() < CreateArena.pos1.getY() - 2) {
            joinPlayer.remove(player)
            player.teleport(loseLocation)
        }
    }

    fun checkPlayerWin(player: Player?) {
        if (joinPlayer.size() === 1) {
            val winPlayer: Player = joinPlayer[0]
            winPlayer.teleport(winLocation)
            Bukkit.getServer().broadcastMessage("§f[§bSpleef§f] Игрок §6" + winPlayer.getName() + " §fпобедил!")
            endGame()
        }
    }

    fun endGame() {
        isGameStarting = false
        joinPlayer.clear()
        fillFloor()
    }

    fun fillFloor() {
        val loc1: Location = CreateArena.pos1
        val loc2: Location = CreateArena.pos2
        val blocks: List<Block> = ArrayList()
        val x1: Int = loc1.getBlockX()
        val y1: Int = loc1.getBlockY()
        val z1: Int = loc1.getBlockZ()
        val x2: Int = loc2.getBlockX()
        val y2: Int = loc2.getBlockY()
        val z2: Int = loc2.getBlockZ()
        val xMin: Int
        val yMin: Int
        val zMin: Int
        val xMax: Int
        val yMax: Int
        val zMax: Int
        var x: Int
        var y: Int
        var z: Int
        if (x1 > x2) {
            xMin = x2
            xMax = x1
        } else {
            xMin = x1
            xMax = x2
        }
        if (y1 > y2) {
            yMin = y2
            yMax = y1
        } else {
            yMin = y1
            yMax = y2
        }
        if (z1 > z2) {
            zMin = z2
            zMax = z1
        } else {
            zMin = z1
            zMax = z2
        }
        x = xMin
        while (x <= xMax) {
            y = yMin
            while (y <= yMax) {
                z = zMin
                while (z <= zMax) {
                    val b: Block = Location(loc1.getWorld(), x, y, z).getBlock()
                    blocks.add(b)
                    z++
                }
                y++
            }
            x++
        }
        for (block in blocks) {
            block.setType(Material.SNOW_BLOCK)
        }
    }

    fun startGame() {
        isGameStarting = true
        schedulerId = Bukkit.getScheduler().scheduleSyncRepeatingTask(spleef, {
            counter++
            for (player in joinPlayer) {
                player.sendTitle(" ", "§a$counter", 10, 10, 10)
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 5)
                if (counter == 4) {
                    player.sendTitle("§eSTART", "", 10, 10, 10)
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100, 5)
                    Bukkit.getScheduler().cancelTask(schedulerId)
                    counter = 0
                }
            }
        }, 3, 20)
    }

    companion object {
        var INSTANCE = Game()
        const val MAX_PLAYERS = 2
    }
}