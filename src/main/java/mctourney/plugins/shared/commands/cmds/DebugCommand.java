package mctourney.plugins.shared.commands.cmds;

import mctourney.plugins.shared.commands.CommandBase;
import mctourney.plugins.shared.utils.PlayerUtils;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandBase {
    @Override
    public void onCommand(CommandSender sender, String cmd, String label, String[] args) {
        Player player = (Player)sender;
        PlayerUtils.addDebug(player);
    }

    @Override
    public String name() {
        return "debug";
    }

    @Override
    public String description() {
        return "View plugin outputs";
    }

    @Override
    public String permission() {
        return Rank.jrmodPerm;
    }
}
