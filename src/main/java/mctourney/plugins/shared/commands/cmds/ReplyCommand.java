package mctourney.plugins.shared.commands.cmds;

import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandBase {


	@Override
	public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
	    if(args.length < 1){
	          sender.sendMessage(Chat.invalid());
	          sender.sendMessage(ChatColor.GRAY + "/" + label + " <message>");
	          return;
	    }
	    if(!MessageCommand.map.containsKey(sender.getName())){
	          sender.sendMessage(ChatColor.RED + "No messages have been sent or received!");
	          return;
	    }
	    if(Bukkit.getPlayer(MessageCommand.map.get(sender.getName())) == null){
			sender.sendMessage(Chat.error("That player is offline!"));
	          return;
	    }
        String message = "";
        for (int i = 0; i < args.length; i++) {
            message = message + args[i] + " ";
        }
        Player thisPlayer = Bukkit.getPlayer(MessageCommand.map.get(sender.getName()));
        Player other = Bukkit.getPlayer(sender.getName());
	    thisPlayer.sendMessage(ChatColor.GRAY + "(From " + other.getDisplayName() + ChatColor.GRAY + ") "+ message);
	    sender.sendMessage(ChatColor.GRAY + "(To " +thisPlayer.getDisplayName() + ChatColor.GRAY + ") " + message);
	}

	@Override
	public String name() {
		return "reply";
	}

	@Override
	public String description() {
		return "Reply to recent recipients";
	}

	@Override
	public String permission() {
		return "m.default";
	}

}
