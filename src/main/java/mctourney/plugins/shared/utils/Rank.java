package mctourney.plugins.shared.utils;

import org.bukkit.ChatColor;

public enum Rank{
    Member(ChatColor.YELLOW), Elite(ChatColor.GREEN), Premium(ChatColor.AQUA), Champion(ChatColor.GOLD), jMod(ChatColor.LIGHT_PURPLE), sMod(ChatColor.DARK_PURPLE), Admin(ChatColor.RED);
    private ChatColor color;
    Rank(ChatColor color) {
        this.color=color;
    }
    public ChatColor getColor() {
        return color;
    }


    public static String jrmodPerm = "m.jrmod";
    public static String srmodPerm = "m.srmod";
    public static String adminPerm = "m.admin";


}