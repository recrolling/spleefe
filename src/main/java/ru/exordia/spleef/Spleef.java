package ru.exordia.spleef;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.exordia.spleef.game.Game;
import ru.exordia.spleef.game.commands.SpleefCommand;
import ru.exordia.spleef.game.events.*;

public final class Spleef extends JavaPlugin {

    @Override public void onEnable() {
        Game.INSTANCE.spleef = this;
        registerEvents();
        getCommand("spleef").setExecutor(new SpleefCommand());

    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new ClickBlockEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onBlockBreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ClickSignEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamageEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerMoveEvent(), this);

    }

    @Override public void onDisable() { }
}
