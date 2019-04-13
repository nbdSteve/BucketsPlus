package dev.neur.bp.file;

import dev.neur.bp.BucketsPlus;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class that handles creating a new file
 */
public class CreateFile {
    //Register main instance
    private Plugin plugin = BucketsPlus.getPlugin(BucketsPlus.class);
    //YAML configuration for the file
    private YamlConfiguration yamlFile;
    //Store the file name for later use
    private String fileName;
    //File to be created
    private File file;

    /**
     * Generates the provided yml file, the filename must be that of a file in the resources folder.
     *
     * @param fileName the name of the file being generated
     */
    public CreateFile(String fileName) {
        file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
            plugin.getLogger().info("The supplied file " + fileName + " was not found, creating it now.");
        }
        yamlFile = new YamlConfiguration();
        try {
            yamlFile.load(file);
        } catch (InvalidConfigurationException e) {
            plugin.getLogger().severe("The supplied file " + fileName +
                    " is not in the correct format, please contact the developer. Disabling the plugin");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        } catch (FileNotFoundException e) {
            plugin.getLogger().severe("The supplied file " + fileName +
                    " was not found, please contact the developer. Disabling the plugin.");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        } catch (IOException e) {
            plugin.getLogger().severe("The supplied file " + fileName +
                    " could not be loaded, please contact the developer. Disabling the plugin.");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
        //Instance variables
        this.fileName = fileName;
    }

    /**
     * reload the file, after the yml has been edited
     */
    public void reload() {
        try {
            yamlFile.load(file);
        } catch (InvalidConfigurationException e) {
            plugin.getLogger().severe("The supplied file " + fileName +
                    " is not in the correct format, plugin contact the developer. Disabling the plugin");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        } catch (FileNotFoundException e) {
            plugin.getLogger().severe("The supplied file " + fileName +
                    " was not found, plugin contact the developer. Disabling the plugin.");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        } catch (IOException e) {
            plugin.getLogger().severe("The supplied file " + fileName +
                    " could not be loaded, plugin contact the developer. Disabling the plugin.");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    /**
     * get the yaml configuration for the file
     *
     * @return yaml configuration
     */
    public FileConfiguration get() {
        return yamlFile;
    }
}
