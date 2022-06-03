package mctourney.plugins.shared.commands.cmds;

import mctourney.plugins.shared.User;
import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.security.InvalidParameterException;

public class ModCommand implements CommandBase {
    @Override
    public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
        if(args.length < 2) {
            sender.sendMessage(Chat.invalid());
            sender.sendMessage(ChatColor.GRAY + "/mod <" + Rank.jMod.toString() + "/" + Rank.sMod.toString() + "> <playerName>");
            return;
        }
        String rank = args[0];
        String playerName = args[1];
        Player player = Bukkit.getPlayer(playerName);
        User user = new User(player.getUniqueId());
        try {
            user.setRank(Rank.valueOf(rank));
        }catch(Exception io) {
            sender.sendMessage(Chat.error("Please try again!"));
            return;
        }
        sender.sendMessage(ChatColor.GRAY + "Player has been ranked '" + user.getRank() + "'!");
        player.sendMessage(ChatColor.GREEN + "You have been promoted to " + user.getRank());
    }

    @Override
    public String name() {
        return "mod";
    }


    @Override
    public String description() {
        return " Mod a player!";
    }

    @Override
    public String permission() {
        return Rank.adminPerm;
    }
}
