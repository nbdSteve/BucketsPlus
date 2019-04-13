package dev.neur.bp.listener;

import dev.neur.bp.event.GenBucketUseEvent;
import dev.neur.bp.generators.VerticalGeneration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GenBucketUsed implements Listener {

    @EventHandler
    public void genBucketUsed(GenBucketUseEvent event) {
        if (event.getGenerationType().equalsIgnoreCase("vertical")) {
            new VerticalGeneration(event);
        }
        if (event.getGenerationType().equalsIgnoreCase("horizontal")) {

        }
        if (event.getGenerationType().equalsIgnoreCase("gravity")) {

        }
    }
}
