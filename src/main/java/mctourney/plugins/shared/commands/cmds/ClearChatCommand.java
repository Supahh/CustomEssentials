package mctourney.plugins.shared.commands.cmds;

import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ClearChatCommand implements CommandBase {

	@Override
	public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
			for(int i = 0 ; i < 20 ; i++) {
				Bukkit.broadcastMessage("");
			}
			Chat.shoutCenter(ChatColor.GREEN + "Chat has been cleared by " + sender.getName());
		}



	@Override
	public String name() {
		return "clearchat";
	}


	@Override
	public String description() {
		return "Clears the chat";
	}

	@Override
	public String permission() {
		return Rank.jrmodPerm;
	}

}
