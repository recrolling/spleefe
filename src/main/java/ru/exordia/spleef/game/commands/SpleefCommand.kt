package ru.exordia.spleef.game.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.HashMap
import java.util.Map

class SpleefCommand : CommandExecutor {
    private val commands: Map<String?, Commands?> = HashMap()
    fun addCommands() {
        commands.put("item", Item())
        commands.put("createarena", CreateArena())
        commands.put("join", Join())
        commands.put("leave", Leave())
        commands.put("setwin", SetWin())
        commands.put("setlose", SetLose())
        commands.put("setspawn", SetSpawn())
        commands.put("resetarena", ResetArena())
    }

    @Override
    fun onCommand(sender: CommandSender, command: Command?, label: String?, args: Array<String?>): Boolean {
        val player: Player = sender as Player
        addCommands()
        if (args.size < 1) {
            player.sendMessage(helpMessage())
        } else {
            val cmd = args[0]
            if (commands[cmd] != null) {
                commands[cmd].onCommands(player, args)
            }
        }
        return true
    }

    fun helpMessage(): String {
        val result = StringBuilder()
        result.append("§7=========================================").append("\n")
        result.append("§f/spleef leave - §cВыйти с игры").append("\n")
        result.append("§f/spleef join - §cПрисоеденится к игре").append("\n")
        result.append("§f/spleef item - §cЭлемент для создания арены§7§l§o*").append("\n")
        result.append("§f/spleef resetarena - §cЗаполнить арену§7§l§o*").append("\n")
        result.append("§7=========================================")
        return result.toString()
    }
}