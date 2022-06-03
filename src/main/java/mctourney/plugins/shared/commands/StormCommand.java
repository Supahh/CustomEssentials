package mctourney.plugins.shared.commands;

import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class StormCommand implements CommandBase {
    @Override
    public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Chat.invalid());
            sender.sendMessage(ChatColor.GRAY + "/" + label + " <on/off>");
            return;
        }
        if(args[0].equalsIgnoreCase("on")) {
            Bukkit.getWorlds().get(0).setStorm(true);
            Bukkit.getWorlds().get(0).setThundering(true);
            sender.sendMessage(Chat.success("Weather has been turned " + args[0]));
            return;
        }
        if(args[0].equalsIgnoreCase("off")) {
            Bukkit.getWorlds().get(0).setStorm(false);
            Bukkit.getWorlds().get(0).setThundering(false);
            sender.sendMessage(Chat.success("Weather has been turned " + args[0]));
            return;
        }
    }

    @Override
    public String name() {
        return "storm";
    }

    @Override
    public String description() {
        return "Toggle weather";
    }

    @Override
    public String permission() {
        return Rank.jrmodPerm;
    }
}
