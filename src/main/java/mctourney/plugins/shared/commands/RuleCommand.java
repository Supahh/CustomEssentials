package mctourney.plugins.shared.commands;

import mctourney.plugins.shared.game.Settings;
import mctourney.plugins.shared.utils.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RuleCommand implements CommandBase {

    @Override
    public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Chat.sendCenter((Player) sender, "*   " + ChatColor.RED + "" + ChatColor.BOLD + "McTourney Rules" + ChatColor.RESET + "   *");
            Settings.getRules().stream().forEach(msg ->{
                Chat.sendCenter((Player)sender, msg);
            });
        }
    }

    @Override
    public String name() {
        return "rules";
    }

    @Override
    public String description() {
        return "Server rules";
    }

    @Override
    public String permission() {
        return "m.default";
    }
}
