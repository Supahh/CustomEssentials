package mctourney.plugins.shared.commands.cmds;

import java.util.UUID;

import mctourney.plugins.shared.User;
import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionCommand implements CommandBase {
	
	

	@Override
	public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
		if(args.length < 2) {
			sender.sendMessage(Chat.invalid());
			sender.sendMessage(ChatColor.WHITE + "/perm <group> <playerName> <days>");
			return;
		}
		String rank = args[0];
		String player = args[1];
		int days = Integer.valueOf(args[2]);
		if(Bukkit.getPlayer(player) != null) {
			Player p = Bukkit.getPlayer(player);
			User user = new User(p.getUniqueId());
			user.addDonation(Rank.valueOf(rank), p.getName(), days);
			return;
		}else {
			OfflinePlayer offline = Bukkit.getOfflinePlayer(player);
			if(!offline.hasPlayedBefore()) {
				sender.sendMessage("never joined.");
				return;
			}
			sender.sendMessage(ChatColor.RED + "That player is not online!");
			User user = new User(offline.getUniqueId());
			user.addDonation(Rank.valueOf(rank), offline.getName(), days);
			}
	}
	
	
	
	

	@Override
	public String name() {
		return "perm";
	}

	@Override
	public String description() {
		return "Permissions handler";
	}

	@Override
	public String permission() {
		return "m.admin";
	}

}
