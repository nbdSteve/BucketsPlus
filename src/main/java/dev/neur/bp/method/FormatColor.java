package dev.neur.bp.method;

import org.bukkit.ChatColor;

public class FormatColor {

    public static String applyCodes(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
