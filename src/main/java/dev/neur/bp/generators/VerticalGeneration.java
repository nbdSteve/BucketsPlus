package dev.neur.bp.generators;

import dev.neur.bp.BucketsPlus;
import dev.neur.bp.event.GenBucketUseEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class VerticalGeneration {

    private ArrayList<String> coordinatesForGeneration;

    public VerticalGeneration(GenBucketUseEvent bucketUseEvent) {
        Block blockAbovePlaced = bucketUseEvent.getBlockPlacedOn().getRelative(0, 1, 0);
        Block blockBelowPlaced = bucketUseEvent.getBlockPlacedOn().getRelative(0, -1, 0);
        int blockY = bucketUseEvent.getBlockPlacedOn().getY();
        int blockX = bucketUseEvent.getBlockPlacedOn().getX();
        int blockZ = bucketUseEvent.getBlockPlacedOn().getZ();
        coordinatesForGeneration = new ArrayList<>();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (blockY > 200) {
                    if (!blockBelowPlaced.getType().equals(Material.AIR)) {
                        this.cancel();
                        return;
                    }
                    int startingPoint = blockY - 1;
                    while (startingPoint >= 1) {
                        coordinatesForGeneration.add(blockX + ":" + startingPoint + ":" + blockZ);
                        startingPoint--;
                    }
                } else {
                    if (!blockAbovePlaced.getType().equals(Material.AIR)) {
                        this.cancel();
                        return;
                    }
                    int startingPoint = blockY + 1;
                    while (startingPoint <= 254) {
                        coordinatesForGeneration.add(blockX + ":" + startingPoint + ":" + blockZ);
                        startingPoint++;
                    }
                }
            }
        }.runTaskAsynchronously(BucketsPlus.getPlugin(BucketsPlus.class));
        BlockGenerationFromArray verticalGeneration = new BlockGenerationFromArray(coordinatesForGeneration, bucketUseEvent);
    }
}