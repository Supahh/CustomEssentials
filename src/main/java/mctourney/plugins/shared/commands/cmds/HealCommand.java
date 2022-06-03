package mctourney.plugins.shared.commands.cmds;

import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandBase {

	@Override
	public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
		if(args.length == 0) {
			if(sender instanceof Player) {
			Player p = (Player)sender;
			sender.sendMessage(ChatColor.GREEN + "You have been healed.");
			heal(p);
			return;
			}
		}
		if(Bukkit.getPlayer(args[0]) == null) {
			sender.sendMessage(Chat.playerNotFound(args[0]));
			return;
		}
		Player t = Bukkit.getPlayer(args[0]);
		heal(t);
		t.sendMessage(ChatColor.GREEN + "You have been healed.");
		sender.sendMessage(ChatColor.RED + "You have healed '" + t.getDisplayName() + "'");
	}
	
	
	
	public void heal(Player player) {
		player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
		player.setFoodLevel(20);
		player.setFireTicks(0);
	}

	@Override
	public String name() {
		return "heal";
	}

	@Override
	public String description() {
		return "Feeds and heals player";
	}

	@Override
	public String permission() {
		return Rank.srmodPerm;
	}

}
