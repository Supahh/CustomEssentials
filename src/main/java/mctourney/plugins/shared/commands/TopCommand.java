package mctourney.plugins.shared.commands;

import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopCommand implements CommandBase {
    @Override
    public void onCommand(CommandSender sender, String cmd, String label, String[] args) {

        if(args.length == 0) {
            if(sender instanceof Player) {
                Player player = (Player)sender;
                top(player, player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockZ());
            }
            return;
        }
        if(Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage(Chat.playerNotFound(args[0]));
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        top(target.getPlayer(), target.getWorld(), target.getLocation().getBlockX(), target.getLocation().getBlockZ());
        sender.sendMessage(Chat.success("Sent player '" + target.getName() + " to top!"));
    }

    public void top(Player player, World world, int x, int z) {
        int y = 125;
        while (world.getBlockAt(x, y, z).getType() == Material.AIR && y > 1)
            y--;
        if (y == 1)
            y = 120;
        y += 2;
        player.teleport(new Location(world, x, y, z));
        Chat.sendCenter(player, ChatColor.RED + "Sending to top..");
    }

    @Override
    public String name() {
        return "top";
    }

    @Override
    public String description() {
        return "Top of your current location";
    }

    @Override
    public String permission() {
        return Rank.adminPerm;
    }
}
