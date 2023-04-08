package fur.kiyoshi.provino.utils;

import org.bukkit.ChatColor;

public class Format {

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
