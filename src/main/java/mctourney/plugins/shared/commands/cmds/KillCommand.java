package mctourney.plugins.shared.commands.cmds;

import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand implements CommandBase {
    @Override
    public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
        if(args.length == 0) {
            if(sender instanceof Player) {
                ((Player) sender).setHealth(0.0);
                sender.sendMessage(ChatColor.GREEN + "You have been killed.");
            }
            return;
        }
        Player t = Bukkit.getPlayer(args[0]);
        if(t == null) {
            sender.sendMessage(Chat.playerNotFound(args[0]));
            return;
        }
        t.setHealth(0.0);
        sender.sendMessage(ChatColor.RED + "You killed " + t.getDisplayName() + "!");
    }

    @Override
    public String name() {
        return "kill";
    }

    @Override
    public String description() {
        return "Murder someone";
    }

    @Override
    public String permission() {
        return Rank.adminPerm;
    }
}
