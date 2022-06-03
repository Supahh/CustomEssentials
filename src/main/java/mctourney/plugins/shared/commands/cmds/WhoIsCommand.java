package mctourney.plugins.shared.commands.cmds;


import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class WhoIsCommand implements CommandBase {

	@Override
	public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
		
		if(args.length == 0) {
			sender.sendMessage(Chat.invalid());
			sender.sendMessage(ChatColor.GRAY +"/whois <playerName>");
			return;
		}
		Player t;
		if(Bukkit.getPlayer(args[0]) == null) {

		}
	}

	@Override
	public String name() {
		return "whois";
	}

	@Override
	public String description() {
		return "Retrieve a player's info";
	}

	@Override
	public String permission() {
		return Rank.adminPerm;
	}

}
