package mctourney.plugins.shared.commands.cmds;

import mctourney.plugins.shared.User;
import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand implements CommandBase {
    @Override
    public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
        String c = ChatColor.WHITE + ", ";
        Chat.sendCenter((Player)sender, ChatColor.YELLOW + "Online" + ChatColor.GRAY  +"(" + ChatColor.AQUA + Bukkit.getOnlinePlayers().size() + ChatColor.GRAY + "/" + ChatColor.DARK_AQUA + Bukkit.getMaxPlayers() + ChatColor.GRAY + ")");
        Chat.sendCenter((Player)sender, ChatColor.WHITE + "(" + ChatColor.GRAY + "MEMBER" + c + ChatColor.GREEN + "ELITE" + c + ChatColor.AQUA + "PREMIUM" + c + ChatColor.GOLD + "CHAMPION" + c + ChatColor.LIGHT_PURPLE + "MOD"  + c + ChatColor.DARK_PURPLE + "SMOD" + c + ChatColor.RED + "ADMIN" + ChatColor.WHITE +	")");
        String message = null;
        for (Player other : Bukkit.getOnlinePlayers()) {
            User player = new User(other.getUniqueId());
            if (other == null || !other.isOnline()){
                continue;
            }
            ChatColor name = player.getRank().getColor();
            message = name + other.getName() + ChatColor.WHITE + ", ";
            message = message.substring(0, message.length() - 2);
        }
        sender.sendMessage(ChatColor.WHITE + "[" + message  + ChatColor.WHITE + "]");
    }

    @Override
    public String name() {
        return "list";
    }


    @Override
    public String description() {
        return "A list of online players";
    }

    @Override
    public String permission() {
        return "m.default";
    }
}
