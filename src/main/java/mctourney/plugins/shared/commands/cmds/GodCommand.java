package mctourney.plugins.shared.commands.cmds;

import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.PlayerUtils;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandBase {
    @Override
    public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
        Player player = (Player)sender;
        if(args.length == 0) {
            PlayerUtils.addGod(player);
            return;
        }
        if(Bukkit.getPlayer(args[0]) == null) {
            player.sendMessage(Chat.playerNotFound(args[0]));
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        PlayerUtils.addGod(target);
    }

    @Override
    public String name() {
        return "god";
    }

    @Override
    public String description() {
        return "Toggle invincibility status";
    }

    @Override
    public String permission() {
        return Rank.srmodPerm;
    }
}
