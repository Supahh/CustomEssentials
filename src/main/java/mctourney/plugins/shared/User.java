package mctourney.plugins.shared;

import mctourney.plugins.shared.game.Settings;
import mctourney.plugins.shared.utils.Chat;
import mctourney.plugins.shared.utils.PlayerUtils;
import mctourney.plugins.shared.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class User {
    public static HashMap<UUID, Rank> rank = new HashMap<>();
    public static HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
    public static FileConfiguration group = YamlConfiguration.loadConfiguration(new File(Shared.getInstance().getDataFolder() + "/" + "group.yml"));
    private UUID u;
    private Player player;
    File playerFile;
    FileConfiguration playerData;

    public User(UUID u) {
        this.u = u;
        this.player = Bukkit.getPlayer(u);
        this.playerFile = new File("plugins/mctourney.com/users/" + u + ".yml");
        if (!playerFile.exists()) {
            firstSetup();
        }
        this.playerData = YamlConfiguration.loadConfiguration(playerFile);
    }

    /**
     * Inject perms, setup everything.
     */
    public void initialize() {
        setName(player.getName());
        rank.put(u, getRank());
        setLastAddress(player.getAddress().getAddress().toString());
        player.setPlayerListName(rank.get(u).getColor() + "" + ChatColor.BOLD + rank.get(u).toString().toUpperCase() + ChatColor.RESET + " " + ChatColor.ITALIC + player.getName());
        PermissionAttachment attachment = player.addAttachment(Shared.getInstance());
        perms.put(u, attachment);
        injectPermission(rank.get(u));
        player.setPlayerListHeaderFooter("✫ " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "  --------------------------  " + ChatColor.RESET + " ✫\n   " + ChatColor.GOLD + "" + ChatColor.BOLD + "McTourney\n    " + ChatColor.YELLOW + "" + ChatColor.ITALIC + "store.mctourney.com\n " + ChatColor.AQUA + "" + ChatColor.ITALIC + "twitter.com/mc_tourney\n   " + ChatColor.GREEN + "" + ChatColor.BOLD + "" + Settings.getServerType() + "\n" + ChatColor.RESET + "✫ " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "  --------------------------  " + ChatColor.RESET + " ✫", "");
        player.sendMessage(ChatColor.GRAY + "Welcome to" + ChatColor.RED + " " + ChatColor.BOLD + Settings.getServerType() + ChatColor.RESET + "" + ChatColor.GRAY + "!");
        player.sendMessage(ChatColor.GRAY + "Visit " + ChatColor.RED + "mctourney.com" + ChatColor.GRAY+ " for news and information.");
        player.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "NO CHEATING! FLYING/XRAY/ETC = BAN! YOU HAVE BEEN WARNED!");
        checkDonation();
        player.sendMessage(ChatColor.GREEN + "Everything loaded successfully!");
    }


    /**
     * Permissions
     * @param rank for their permissions.
     */
    public void injectPermission(Rank rank) {
        List<String> permissions = new ArrayList<>();
        playerData.getStringList("perms").stream().forEach(p -> {
            permissions.add(p);
        }); //SOLO PERMS!
        switch(rank) {
            case Member:
               group.getStringList("Member").stream().forEach(p -> {
                    permissions.add(p);
                });
                break;
            case Elite:
                group.getStringList("Elite").stream().forEach(p -> {
                    permissions.add(p);
                });
                break;
            case Premium:
                group.getStringList("Premium").stream().forEach(p -> {
                    permissions.add(p);
                });
                break;
            case Champion:
                group.getStringList("Champion").stream().forEach(p -> {
                    permissions.add(p);
                });
                break;
            case jMod:
                group.getStringList("jMod").stream().forEach(p -> {
                    permissions.add(p);
                });
                break;
            case sMod:
                group.getStringList("sMod").stream().forEach(p -> {
                    permissions.add(p);
                });
                break;
            case Admin:
                group.getStringList("Admin").stream().forEach(p -> {
                    permissions.add(p);
                });
                break;
        }
       permissions.stream().forEach(add->{
           perms.get(u).setPermission(add, true);
       });
    }


    /**
     * End the user and clear up memory
     */
    public void remove() {
        player.removeAttachment(perms.get(u));
        rank.remove(u);
    }

    /**
     * Date
     * @return
     */
    public String getJoinDate() {
        return playerData.getString("join-date");
    }

    /**
     * Set ip Address
     * @param address
     */

    public void setLastAddress(String address) {
        if(!getLastAddress().equals(address)) {
            playerData.set("last-ip", address);
            save();
        }
    }

    /**
     * Get last ip address
     * @return
     */
    public String getLastAddress() {
        return playerData.getString("last-ip");
    }


    /**
     * Name management
     * @param playerName
     */
    public void setName(String playerName) {
        if(!getName().equals(playerName)) {
            playerData.set("name", playerName);
            save();
        }
    }

    /**
     * Get the username
     * @return
     */
    public String getName() {
        return playerData.getString("name");
    }

    /**
     * Set the rank of a user
     * @param rank
     */
    public void setRank(Rank rank) {
        playerData.set("rank", rank.toString());
        save();
    }

    /**
     * Get the rank of a user
     * @return
     */
    public Rank getRank() {
        return Rank.valueOf(playerData.getString("rank"));
    }

    /**
     * Creates first file from template.
     * @throws IOException
     */
    public void firstSetup() {
        try {
            File create = new File("plugins/mctourney.com/users/" + u + ".yml");
            File copy = new File("plugins/Shared/" + "temp.yml");
            create.createNewFile();
            Files.copy(copy.toPath(), create.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileConfiguration config = YamlConfiguration.loadConfiguration(create);
            config.set("name", player.getName());
            config.set("last-ip", player.getAddress().toString());
            config.set("rank", config.getString("rank"));
            config.set("join-date", Chat.simpleDateFormat(new Date()).toString());
            config.set("perms", config.getStringList("perms"));
            config.save(create);
        }catch(Exception io) {
            Bukkit.getPluginManager().disablePlugin(Shared.getInstance());
        }
    }


    /**
     * All donation info.
     * @return
     */
    public void addDonation(Rank rank, String name, int days) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        if(playerData != null) {
            setRank(rank);
            c.add(Calendar.DATE, days);
            setDonationDate(dateFormat.format(c.getTime()));
            Player target = Bukkit.getPlayer(name);
            Bukkit.getOnlinePlayers().stream().forEach(players -> {
                PlayerUtils.createDonationBar(players, ChatColor.YELLOW + "" + ChatColor.BOLD + name + ChatColor.WHITE + " is the MVP of the server for the time being.", 180);
            });
            Chat.shoutCenter(ChatColor.WHITE + "" + ChatColor.BOLD + name + ChatColor.YELLOW + "" + ChatColor.BOLD + " HAS DONATED AND RECIEVED "  + ChatColor.GOLD + "" + ChatColor.BOLD + rank.toString().toUpperCase());
            if(target == null) {
                return;
            }
            Chat.cleanChat(3);
            target.sendMessage(ChatColor.RED + "Please relogin to recieve your membership perks. If there is any issues please contact a staff member.");
        }
    }


    public String getDonationDate() {
        return playerData.getString("donation-end");
    }

    public void checkDonation() {
        if (hasDonated()) {
            Calendar c = Calendar.getInstance();
            Date today = c.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            try {
                Date donationDate = formatter.parse(getDonationDate());
                if (today.after(donationDate)) {
                    player.sendMessage(ChatColor.RED + "Your " + getRank() + " subscription has ended.");
                    setRank(Rank.Member);
                    setDonationDate(null);
                    return;
                }
                player.sendMessage(ChatColor.WHITE + "*" + getRank() + " subscription ends on: " + getDonationDate());
            }catch(Exception io) {

            }
        }
    }

    public void setDonationDate(String format) {
        playerData.createSection("donation-end");
        playerData.set("donation-end", format);
        save();
    }

    public boolean hasDonated() {
        if(playerData.getString("donation-end") == null) return false;
        return true;
    }


    /**
     * Save any file changes!
     */
    public void save() {
        try {
            playerData.save(playerFile);
        }catch(Exception io) {
            player.sendMessage(ChatColor.RED + "An error has occurred on User.java!");
        }
    }
}
