package mctourney.plugins.shared.commands.cmds;

import mctourney.plugins.shared.User;
import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandBase {
    @Override
    public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
        if(args.length < 2) {
            sender.sendMessage(Chat.invalid());
            sender.sendMessage(ChatColor.GRAY + "/" + label + " <playerName> <kickReason>");
            return;
        }
        if(Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage(Chat.playerNotFound(args[0]));
            return;
        }
        String kickMessage = "";
        for(int i = 1; i < args.length; i++) {
            kickMessage = kickMessage + args[i] + "" + ".";
        }
        Player target = Bukkit.getPlayer(args[0]);
        User t = new User(target.getUniqueId());
        if(t.getRank() == Rank.Admin) {
            sender.sendMessage(ChatColor.RED + "You are not permitted to kick this player.");
            return;
        }
        Chat.shoutCenter(ChatColor.GREEN + target.getName() + " has been kicked by " + sender.getName());
        target.kickPlayer(ChatColor.YELLOW + "You have been kicked by " + sender.getName() + " for " + kickMessage.replace(".", " "));
        return;
    }

    @Override
    public String name() {
        return "kick";
    }

    @Override
    public String description() {
        return "Boot a player off";
    }

    @Override
    public String permission() {
        return Rank.jrmodPerm;
    }
}
