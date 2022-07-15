package ru.exordia.spleef

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import ru.exordia.spleef.game.Game
import ru.exordia.spleef.game.commands.SpleefCommand
import ru.exordia.spleef.game.events.*

class Spleef : JavaPlugin() {
    @Override
    fun onEnable() {
        Game.INSTANCE.spleef = this
        registerEvents()
        getCommand("spleef").setExecutor(SpleefCommand())
    }

    fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(ClickBlockEvent(), this)
        Bukkit.getPluginManager().registerEvents(onBlockBreakEvent(), this)
        Bukkit.getPluginManager().registerEvents(ClickSignEvent(), this)
        Bukkit.getPluginManager().registerEvents(PlayerChatEvent(), this)
        Bukkit.getPluginManager().registerEvents(PlayerDamageEvent(), this)
        Bukkit.getPluginManager().registerEvents(onPlayerMoveEvent(), this)
    }

    @Override
    fun onDisable() {
    }
}