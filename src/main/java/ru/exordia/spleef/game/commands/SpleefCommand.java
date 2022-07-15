package ru.exordia.spleef.game.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SpleefCommand implements CommandExecutor {
    private final Map<String, Commands> commands = new HashMap<>();

    public void addCommands() {
        commands.put("item", new Item());
        commands.put("createarena", new CreateArena());
        commands.put("join", new Join());
        commands.put("leave", new Leave());
        commands.put("setwin", new SetWin());
        commands.put("setlose", new SetLose());
        commands.put("setspawn", new SetSpawn());
        commands.put("resetarena", new ResetArena());
    }

    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        addCommands();

        if (args.length < 1) {
            player.sendMessage(helpMessage());
        } else {
            String cmd = args[0];
            if (commands.get(cmd) != null) {
                commands.get(cmd).onCommands(player, args);
            }
        }

        return true;
    }

    public String helpMessage() {
        StringBuilder result = new StringBuilder();

        result.append("§7=========================================").append("\n");
        result.append("§f/spleef leave - §cВыйти с игры").append("\n");
        result.append("§f/spleef join - §cПрисоеденится к игре").append("\n");
        result.append("§f/spleef item - §cЭлемент для создания арены§7§l§o*").append("\n");
        result.append("§f/spleef resetarena - §cЗаполнить арену§7§l§o*").append("\n");
        result.append("§7=========================================");


        return result.toString();
    }
}