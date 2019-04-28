package dev.neur.bp.listener;

import dev.neur.bp.BucketsPlus;
import dev.neur.bp.event.GenBucketGenerationEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

public class BlockGenerationByGenBucket implements Listener {

    @EventHandler
    public void blockGeneration(GenBucketGenerationEvent event) {
        final BukkitTask generationTaskID = Bukkit.getScheduler().runTask(BucketsPlus.getPlugin(BucketsPlus.class), () -> {
            event.getBlockToChange().setType(event.getBlockTypeToGenerate());
        });
    }
}
