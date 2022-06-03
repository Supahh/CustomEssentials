package mctourney.plugins.shared.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerUtils {
    public static List<UUID> debug = new ArrayList<>();
    public static List<UUID> god = new ArrayList<>();


    public static boolean inDebug(UUID u) {
        return debug.contains(u);
    }

    public static void addDebug(Player player) {
        UUID u = player.getUniqueId();
        if(inDebug(u)) {
            player.sendMessage(ChatColor.YELLOW + "{SHARED} " + ChatColor.GREEN + "You are no longer in debuggging.");
            debug.remove(u);
            return;
        }
        debug.add(u);
        player.sendMessage(ChatColor.YELLOW + "{SHARED} " + ChatColor.GREEN + "You are now in debug mode.");
    }


    /**
     * In god mode
     * @param u
     * @return
     */
    public static boolean inGodMode(UUID u) {
        return god.contains(u);
    }

    /**
     * Add too god mode
     * @param player
     */
    public static void addGod(Player player) {
        UUID u = player.getUniqueId();
        if(inGodMode(u)) {
            player.sendMessage(ChatColor.RED+"You are no longer in god mode.");
            god.remove(u);
            return;
        }
        player.sendMessage(ChatColor.GREEN +"You are now in god mode.");
        god.add(u);
        return;
    }

    public static void createDonationBar(Player players, String s, int i) {
        BossBar bar;
    }



    public String offlineUUID(OfflinePlayer player) {
        return player.getUniqueId().toString();
    }

    public static String getPing(Player p) {
        try {
            String bukkitversion = Bukkit.getServer().getClass().getPackage().getName().substring(23);
            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + bukkitversion + ".entity.CraftPlayer");
            Object handle = craftPlayer.getMethod("getHandle").invoke(p);
            return ((Integer) handle.getClass().getDeclaredField("ping").get(handle)).intValue() + "";
        } catch (Exception e) { return "-1"; }
    }

    public static Block getFeetBlock(Player p, double minusY) {
        return new Location(p.getWorld(),p.getLocation().getX() - 0, p.getLocation().getY() - minusY,p.getLocation().getZ() - 0, p.getLocation().getPitch(), p.getLocation().getYaw()).getBlock();
    }
}
