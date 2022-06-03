package mctourney.plugins.shared.commands;

import mctourney.plugins.shared.commands.cmds.*;
import mctourney.plugins.shared.utils.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CommandFactory implements CommandExecutor {

    private CommandFactory() { }

    private static CommandFactory instance = new CommandFactory();

    public static CommandFactory getInstance() {
        return instance;
    }

    public static TreeMap<String, CommandBase> commands = new TreeMap<String, CommandBase>();

    public static void loadCommands() {
        commands.put("kill", new KillCommand());
        commands.put("kick", new KickCommand());
        commands.put("god", new GodCommand());
        commands.put("debug", new DebugCommand());
        commands.put("mod", new ModCommand());
        commands.put("perm", new PermissionCommand());
        commands.put("ci", new ClearInventoryCommand());
        commands.put("heal", new HealCommand());
        commands.put("message", new MessageCommand());
        commands.put("reply", new ReplyCommand());
        commands.put("clearchat", new ClearChatCommand());
        commands.put("lag", new LagCommand());
        commands.put("whois", new WhoIsCommand());
        commands.put("list", new ListCommand());
        commands.put("top", new TopCommand());
        commands.put("storm", new StormCommand());
        commands.put("rules", new RuleCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("help")) {
            sender.sendMessage("");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.GRAY + "*********" + ChatColor.RED + "Player Commands" + ChatColor.GRAY + "*********");
            for (CommandBase allCommands : commands.values()) {
                List<String> desc = new ArrayList<>();
                desc.add(allCommands.description());
                if (sender.hasPermission(allCommands.permission())) {
                    for (String d : desc) {
                        sender.sendMessage(ChatColor.GRAY + "/" + ChatColor.RED + allCommands.name() + ChatColor.WHITE + " - " + ChatColor.GRAY + d);
                    }
                }
            }
        }
        for (CommandBase allCommands : commands.values()) {
            if (cmd.getName().equalsIgnoreCase(allCommands.name())) {
                CommandBase command = getSent(cmd.getName());
                if(command != null) {
                    if (!sender.hasPermission(command.permission())) {
                        sender.sendMessage(Chat.denyPermission());
                        return true;
                    }
                    command.onCommand(sender, command.name(), label, args);
                }
            }
        }
        return false;

    }

    private CommandBase getSent(String name) {
        for (CommandBase cmd : commands.values()) {
            if (cmd.name().equalsIgnoreCase(name)) {
                return cmd;
            }
        }
        return null;
    }


}

