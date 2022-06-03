package mctourney.plugins.shared.listeners;

import com.destroystokyo.paper.event.player.PlayerHandshakeEvent;
import mctourney.plugins.shared.Shared;
import mctourney.plugins.shared.User;
import mctourney.plugins.shared.event.TickEvent;
import mctourney.plugins.shared.game.Settings;
import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.PlayerUtils;
import mctourney.plugins.shared.utils.inventory.GUI;
import oracle.jrockit.jfr.StringConstantPool;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerListener implements Listener {

    @EventHandler
    public void onTalk(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setFormat(User.rank.get(player.getUniqueId()).getColor() + "" + ChatColor.BOLD +User.rank.get(player.getUniqueId()).toString().toUpperCase() + ChatColor.RESET + " " + ChatColor.ITALIC + player.getDisplayName() + ChatColor.RESET + ChatColor.GRAY + " > " + ChatColor.RESET + event.getMessage()) ;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)  {
        Player player = event.getPlayer();
        User user = new User(player.getUniqueId());
        user.initialize();
        event.setJoinMessage("");
        String playerName = ChatColor.GREEN + "" + ChatColor.BOLD + "" + player.getName();
        if(!player.hasPlayedBefore()) {
            Chat.shoutCenter(playerName + "" + ChatColor.GREEN + ""  + " has joined for the first time.");
            return;
        }
        Chat.shoutCenter(playerName + "" + ChatColor.GREEN + ""  + " has joined the server");
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = new User(player.getUniqueId());
        user.remove();
        event.setQuitMessage("");
        String playerName = ChatColor.GREEN + "" + ChatColor.BOLD + "" + player.getName();
        Chat.shoutCenter(playerName + "" + ChatColor.GREEN + " has left the server");

    }

    @EventHandler
    public void godMode(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            if(PlayerUtils.god.size() < 0) {
                return;
            }
            Player player = (Player)event.getEntity();
            if(PlayerUtils.inGodMode(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        Settings.getBannedCommands().stream().forEach(banned ->  {
            if(message.equalsIgnoreCase(banned)) {
                Chat.sendCenter(player,Chat.error("This command has been disabled!"));
                event.setCancelled(true);
            }
        });
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getInventory().getHolder() instanceof GUI) {
            event.setCancelled(true);
            GUI gui = (GUI) event.getInventory().getHolder();
            gui.onClick((Player)event.getWhoClicked(), event.getRawSlot(), event.getCurrentItem());
        }
    }



}
