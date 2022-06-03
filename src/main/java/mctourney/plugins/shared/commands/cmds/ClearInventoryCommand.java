package mctourney.plugins.shared.commands.cmds;

import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventoryCommand implements CommandBase {

	@Override
	public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
		if(args.length == 0) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				p.getInventory().clear();
				p.getInventory().setBoots(null);
				p.getInventory().setHelmet(null);
				p.getInventory().setChestplate(null);
				p.getInventory().setLeggings(null);
				p.sendMessage(ChatColor.GRAY + "Inventory cleared");
				return;
			}

		}
		if(Bukkit.getPlayer(args[0]) == null){
			sender.sendMessage(Chat.playerNotFound(args[0]));
			return;
		}
		Player t = Bukkit.getPlayer(args[0]);
		t.getInventory().clear();
		t.getInventory().setBoots(null);
		t.getInventory().setHelmet(null);
		t.getInventory().setChestplate(null);
		t.getInventory().setLeggings(null);
		sender.sendMessage(ChatColor.GRAY +  t.getName() + " inventory has been cleared.");
		return;
	}

	@Override
	public String name() {
		return "ci";
	}


	@Override
	public String description() {
		return "Empty out your inventory";
	}

	@Override
	public String permission() {
		return Rank.adminPerm;
	}


}
