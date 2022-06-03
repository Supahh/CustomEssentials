package mctourney.plugins.shared.game;

import mctourney.plugins.shared.Shared;
import mctourney.plugins.shared.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Settings {
    private static String serverType;

    public static ArrayList<String> bannedCommands = new ArrayList<String>();

    public static void setServerType(String type) {
        serverType = type;
        Shared.getInstance().getLogger().warning("Server set to " + type);
    }

    public static String getServerType() {
        return serverType;
    }

    public static List<String> getBannedCommands() {
        return bannedCommands;
    }

    public static void addBannedCommands(String command) {
        bannedCommands.add("/" + command);
    }

    public static void loadBroadcast() {
        List<String> messages = new ArrayList<String>();
        Shared.getInstance().getConfig().getStringList("broadcast").stream().forEach(s ->{
            String color = ChatColor.translateAlternateColorCodes('&', s);
            messages.add(ChatColor.YELLOW + "✿ " + color);
        });
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Shared.getInstance(), new Runnable() {
            @Override
            public void run() {
                Collections.shuffle(messages);
                Chat.shoutCenter(messages.get(0));

            }
        }, 1, 20 * 60 * 10);
    }


    public static List<String> getRules() {
        return Shared.getInstance().getConfig().getStringList("server-rules");
    }
}
