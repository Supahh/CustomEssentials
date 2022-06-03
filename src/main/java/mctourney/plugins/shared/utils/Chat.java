package mctourney.plugins.shared.utils;

import mctourney.plugins.shared.Shared;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat {

    /**
     * Round numbers
     * @param value
     * @param places
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Send debug message
     * @param debugMessage
     */
    public static void sendDebug(String debugMessage) {
        PlayerUtils.debug.stream().forEach(debuggers -> {
            Player checked = Bukkit.getPlayer(debuggers);
            checked.sendMessage(ChatColor.YELLOW + "[SHARED] " + ChatColor.WHITE + debugMessage);
        });
    }


    public static String error(String message) {
        return ChatColor.RED + "" + ChatColor.BOLD + "ERROR!" + ChatColor.RESET + " " + ChatColor.RED + message;
    }


    public static String success(String message) {
        return ChatColor.GREEN + "" + ChatColor.BOLD + "SUCCESS!" + ChatColor.RESET + " " + ChatColor.GREEN + message;
    }

    public static String denyPermission() {
        String help = "\"/help\"";
        String noPerm = "Unknown command. Type " + help + " for help. ";
        return noPerm;
    }

    /**
     * Cleans the chat.
     * @param i
     */
    public static void cleanChat(int i) {
        for(int k=0; k < i ; k++) {
            Bukkit.broadcastMessage("");
        }
    }

    /**
     * Sends a centered message to the whole server
     * @param message
     */
    public static void shoutCenter(String message) {
        Align.send(message);
    }

    /**
     * Sends a message through an action bar
     * @param player
     * @param message
     * @param duration
     */
    public static void actionBar(final Player player, final String message, int duration) {
        player.sendActionBar(message);
        if (duration >= 0) {
            (new BukkitRunnable() {
                public void run() {
                    player.sendActionBar("");
                }
            }).runTaskLater(Shared.getInstance(), (duration + 1));
        }

        while (duration > 40) {
            duration -= 40;
            (new BukkitRunnable() {
                public void run() {
                    player.sendActionBar(message);
                }
            }).runTaskLater(Shared.getInstance(), duration);
        }
    }


    /**
     * Center to only the player.
     * @param player
     * @param message
     */
    public static void sendCenter(Player player, String message) {
        Align.send(player, message);
    }

    /**
     * Convert time from army to standard.
     * @param armyTime
     * @return
     * @throws ParseException
     */
    public static String convertTime(String armyTime) throws ParseException {
        String input = armyTime;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateFormat outputformat = new SimpleDateFormat("hh:mm:ss aa");
        Date date = null;
        String output = null;
        date= df.parse(input);
        output = outputformat.format(date);
        return output;
    }

    /**
     * Simple
     * @param d
     * @return
     */
    public static String simpleDateFormat(Date d) {
       DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
       return format.format(d);
    }

    public static String invalid() {
        return ChatColor.RED + "Invalid Usage!";
    }

    public static String playerNotFound(String player) {
        return ChatColor.RED + "Player '" + player + "' could not be found.";
    }
}
