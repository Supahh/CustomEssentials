package mctourney.plugins.shared.commands;

import mctourney.plugins.shared.utils.Rank;
import org.bukkit.command.CommandSender;

public interface CommandBase {

    public void onCommand(CommandSender sender, String cmd, String label, String[] args);

    public String name();

    public String description();

    public String permission();
}
