package ru.exordia.spleef.game.commands

import org.bukkit.entity.Player

interface Commands {
    fun onCommands(player: Player?, args: Array<String?>?)
}