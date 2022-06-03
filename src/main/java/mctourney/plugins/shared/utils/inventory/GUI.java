package mctourney.plugins.shared.utils.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public interface GUI extends InventoryHolder {

    void onClick(Player player, int slot, ItemStack clickedItem);

}
