package dev.neur.bp.listener;

import dev.neur.bp.BucketsPlus;
import dev.neur.bp.event.GenBucketUseEvent;
import dev.neur.bp.generators.VerticalGeneration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GenBucketUsed implements Listener {

    @EventHandler
    public void genBucketUsed(GenBucketUseEvent event) {
        BucketsPlus.LOGGER.severe("Current placed: " + BucketsPlus.files.get("data").getInt("bucket-data.number-placed"));
        int currentPlaced = BucketsPlus.files.get("data").getInt("bucket-data.number-placed");
        BucketsPlus.files.get("data").set("bucket-data.number-placed", currentPlaced + 1);
        BucketsPlus.files.save("data");
        BucketsPlus.LOGGER.severe("New placed: " + BucketsPlus.files.get("data").getInt("bucket-data.number-placed"));
        if (event.getGenerationType().equalsIgnoreCase("vertical")) {
            new VerticalGeneration(event);
        }
        if (event.getGenerationType().equalsIgnoreCase("horizontal")) {

        }
        if (event.getGenerationType().equalsIgnoreCase("gravity")) {

        }
    }
}
