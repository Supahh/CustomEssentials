package mctourney.plugins.shared;

import mctourney.plugins.shared.commands.CommandFactory;
import mctourney.plugins.shared.event.Lag;
import mctourney.plugins.shared.event.TickEvent;
import mctourney.plugins.shared.game.Settings;
import mctourney.plugins.shared.listeners.PlayerListener;
import mctourney.plugins.shared.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Collections;
import java.util.logging.Logger;

public class Shared extends JavaPlugin {
    private static Shared plugin;

    public void onEnable() {
        long start = System.currentTimeMillis();
        plugin = this;
        PluginManager pluginManager = Bukkit.getPluginManager();
        saveDefaultConfig();
        reloadConfig();
        pluginManager.registerEvents(new PlayerListener() ,this);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 20L, 1L);
        getServer().getScheduler().runTaskTimer(this, () -> pluginManager.callEvent(new TickEvent()), 0, 1);
        Settings.loadBroadcast();
        CommandFactory.loadCommands();;
        plugin.getDescription().getCommands().keySet().stream().forEach(c->{
            getCommand(c).setExecutor(CommandFactory.getInstance());
        });
        File mainDirectory = new File("plugins/mctourney.com");
        File playerDirectory = new File("plugins/mctourney.com/users");
        File otherDirectory = new File("plugins/mctourney.com/data");
        if(!mainDirectory.exists()) {
            mainDirectory.mkdirs();
            playerDirectory.mkdirs();
            otherDirectory.mkdirs();
        }
        Bukkit.getOnlinePlayers().stream().forEach(p ->{
            Player player = p;
            User user = new User(player.getUniqueId());
            user.initialize();
        });
        long took = System.currentTimeMillis() - start;
        getLogger().warning(plugin.getName() + " enabled in " + took + "ms! Server type ");
    }



    public Logger getLogger() {
        return Bukkit.getLogger();
    }

    public static Shared getInstance() {
        return plugin;
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }
}
