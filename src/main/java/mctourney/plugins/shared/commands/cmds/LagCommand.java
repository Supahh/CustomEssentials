package mctourney.plugins.shared.commands.cmds;

import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.event.Lag;
import mctourney.plugins.shared.utils.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class LagCommand implements CommandBase {
    @Override
    public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
        double tps = Chat.round(Lag.getTPS(), 2);
        double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
        if(tps == 0.0) {
            sender.sendMessage(Chat.error("Please try again in a couple of seconds"));
            return;
        }
        if(tps >= 20.0) {
            tps = 20.0;
            lag = 0;
        }
        sender.sendMessage(ChatColor.YELLOW + "Server is at " + ChatColor.GREEN + tps + ChatColor.YELLOW + " tps.");
        sender.sendMessage(ChatColor.YELLOW + "Lag is approximately " + ChatColor.GREEN + lag + ChatColor.YELLOW + "%");
    }



    @Override
    public String name() {
        return "lag";
    }


    @Override
    public String description() {
        return "Check the server's tps ";
    }

    @Override
    public String permission() {
        return "m.default";
    }


}
