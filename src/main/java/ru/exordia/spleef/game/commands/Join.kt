package ru.exordia.spleef.game.commands

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import ru.exordia.spleef.game.Game
import java.util.List
import java.util.Random

class Join : Commands {
    private val random: Random = Random()

    @Override
    override fun onCommands(player: Player, args: Array<String?>?) {
        val maxPlayers: Int = Game.MAX_PLAYERS
        val locs: List<Location> = Game.INSTANCE.spawnLocations
        if (Game.INSTANCE.joinPlayer.size() !== maxPlayers) {
            if (!Game.INSTANCE.joinPlayer.contains(player)) {
                giveShovel(player)
                Game.INSTANCE.joinPlayer.add(player)
                for (players in Game.INSTANCE.joinPlayer) {
                    players.sendMessage("§f[§bSpleef§f] Игрок §9" + player.getName() + " §fприсоеденился к игре [" + Game.INSTANCE.joinPlayer.size() + "/" + maxPlayers + "]")
                }
                player.teleport(locs[random.nextInt(locs.size())])
                if (Game.INSTANCE.joinPlayer.size() === Game.MAX_PLAYERS) {
                    Game.INSTANCE.startGame()
                }
            } else {
                player.sendMessage("§f[§bSpleef§f] Вы уже в игре!")
            }
        } else {
            player.sendMessage("§f[§bSpleef§f] Игра уже началась")
        }
    }

    private fun giveShovel(player: Player) {
        val item = ItemStack(Material.DIAMOND_SHOVEL)
        item.addEnchantment(Enchantment.DIG_SPEED, 5)
        player.getInventory().setItem(0, item)
    }
}