package mctourney.plugins.shared.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class InventoryUtils {

    public static ItemStack create(Material m, String name) {
        ItemStack item = new ItemStack(m);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(name);
        item.setItemMeta(itemmeta);
        return item;
    }

    public static ItemStack create(Material m, String name, List<String> lore) {
        ItemStack item = new ItemStack(m);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(name);
        itemmeta.setLore(lore);
        item.setItemMeta(itemmeta);
        return item;
    }

}