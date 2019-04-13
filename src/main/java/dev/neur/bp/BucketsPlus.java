package dev.neur.bp;

import dev.neur.bp.cmd.BpCmd;
import dev.neur.bp.event.callcustomevents.PlayerInteractListener;
import dev.neur.bp.file.LoadFiles;
import dev.neur.bp.listener.GenBucketUsed;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.logging.Logger;

public final class BucketsPlus extends JavaPlugin {
    //Store the plugin files
    private static LoadFiles files;
    //Create a logger for the plugin
    public static Logger LOGGER = Logger.getLogger(BucketsPlus.class.getName());
    //Store the servers economy
//    public static Economy economy;
    //If the plugin should log debug timing messages
    public static boolean debugMode;
    //Static way to format price placeholders
    public static DecimalFormat numberFormat = new DecimalFormat("#,###");

    @Override
    public void onEnable() {
        getCommand("bp").setExecutor(new BpCmd());
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new GenBucketUsed(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
