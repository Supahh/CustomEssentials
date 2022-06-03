package mctourney.plugins.shared.commands.cmds;

import java.util.HashMap;

import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandBase {
	public static final HashMap<String, String> map = new HashMap<String, String>();


	@Override
	public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
	    if(args.length < 2){
	          sender.sendMessage(Chat.invalid());
	          sender.sendMessage(ChatColor.GRAY + "/" + label + " <playerName> <message>");
	          return;
	    }
	    if(Bukkit.getPlayer(args[0]) == null){
	          sender.sendMessage(Chat.playerNotFound(args[0]));
	          return;
	    }
	    String message = "";
	    for(int i = 1; i < args.length; i++) {
            message = message + args[i] + " ";
        }
	    String otherUser = ChatColor.stripColor(Bukkit.getPlayer(args[0]).getDisplayName());
	    String thisUser = ChatColor.stripColor(sender.getName());
	    Player other = Bukkit.getPlayer(sender.getName());
	    Bukkit.getPlayer(args[0]).sendMessage(ChatColor.GRAY + "(From " + other.getDisplayName() + ChatColor.GRAY + ") "+ message);
	    sender.sendMessage(ChatColor.GRAY + "(To " + Bukkit.getPlayer(args[0]).getDisplayName() + ChatColor.GRAY + ") " + message);
	    map.put(thisUser, otherUser);
	    map.put(otherUser, thisUser);
	    return;    
	}

	@Override
	public String name() {
		return "message";
	}

	@Override
	public String description() {
		return "Message other players";
	}

	@Override
	public String permission() {
		return "m.default";
	}

}
