package ru.exordia.spleef.game.commands;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.exordia.spleef.game.Game;

import java.util.Arrays;

public class Item implements Commands {

    @Override public void onCommands(Player player, String[] args) {
        player.sendMessage(Arrays.toString(Game.INSTANCE.joinPlayer.toArray()));
        if (player.isOp() || player.hasPermission("spleef.admin")) {
            giveItem(player);
        } else
            player.sendMessage("§f[§bSpleef§f] §7Недостатачно прав.");
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.STONE_HOE, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§6Создание арены");
            meta.addEnchant(Enchantment.PROTECTION_FIRE, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        item.setItemMeta(meta);

        return item;
    }

    public void giveItem(Player player) {
        player.getInventory().setItem(0, createItem());
        player.sendMessage("§e/spleef createarena для создания арены");

    }
}