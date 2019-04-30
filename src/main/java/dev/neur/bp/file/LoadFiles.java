package dev.neur.bp.file;

import dev.neur.bp.BucketsPlus;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class LoadFiles {
    //HashMap to store the files
    private HashMap<Files, CreateFile> files;

    /**
     * Enum to store each file, this is public so we can call methods on these
     */
    public enum Files {
        CONFIG, MESSAGES, VERTICAL_BUCKETS, HORIZONTAL_BUCKETS, DATA
    }

    /**
     * Generate all of the files in the enum
     */
    public LoadFiles() {
        files = new HashMap<>();
        //Load generic files
        files.put(Files.CONFIG, new CreateFile("buckets+.yml"));
        files.put(Files.MESSAGES, new CreateFile("messages.yml"));
        files.put(Files.DATA, new CreateFile("data.yml"));
        //Load vertical bucket files
        files.put(Files.VERTICAL_BUCKETS, new CreateFile("vertical" + File.separator + "vertical-buckets.yml"));
        //Load horizontal bucket files
        files.put(Files.HORIZONTAL_BUCKETS, new CreateFile("horizontal" + File.separator + "horizontal-buckets.yml"));
        //Log that files are loaded
        BucketsPlus.LOGGER.info("[Buckets+] Successfully loaded all configuration files...");
    }

    /**
     * Gets the specified YAML file
     *
     * @param fileName String, the name of the file from the Files enum
     * @return FileConfiguration
     */
    public FileConfiguration get(String fileName) {
        if (files.containsKey(Files.valueOf(fileName.toUpperCase()))) {
            return files.get(Files.valueOf(fileName.toUpperCase())).get();
        }
        return null;
    }

    public void save(String fileName) {
        if (files.containsKey(Files.valueOf(fileName.toUpperCase()))) {
            files.get(Files.valueOf(fileName.toUpperCase())).save();
        }
    }

    /**
     * Reloads all of the files in the Files enum
     */
    public void reload() {
        for (CreateFile file : files.values()) {
            file.reload();
        }
        BucketsPlus.LOGGER.info("[Buckets+] Reloading configuration files...");
    }
}
