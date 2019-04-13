package dev.neur.bp.generators;

import dev.neur.bp.BucketsPlus;
import dev.neur.bp.event.GenBucketUseEvent;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class VerticalGeneration {

    ArrayList<String> blocksToReplace;

    public VerticalGeneration(GenBucketUseEvent useEvent) {
        int blockY = useEvent.getBlockPlacedOn().getY();
        int blockX = useEvent.getBlockPlacedOn().getX();
        int blockZ = useEvent.getBlockPlacedOn().getZ();
        blocksToReplace = new ArrayList<>();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (blockY > 200) {
                    int startingPoint = blockY;
                    while (startingPoint >= 256 - useEvent.getNumberOfBlocksToGenerate()) {
                        blocksToReplace.add(blockX + ":" + startingPoint + ":" + blockZ);
                        startingPoint--;
                    }
                } else {
                    int startingPoint = blockY + 1;
                    while (startingPoint <= 256 - blockY) {
                        blocksToReplace.add(blockX + ":" + startingPoint + ":" + blockZ);
                        startingPoint++;
                    }
                }

                new BukkitRunnable() {
                    int index = 0;

                    @Override
                    public void run() {
                        if (index < blocksToReplace.size()) {
                            String[] coords = blocksToReplace.get(index).split(":");
                            useEvent.getBucketOwner().getWorld().getBlockAt(Integer.parseInt(coords[0]),
                                    Integer.parseInt(coords[1]), Integer.parseInt(coords[2])).setType(Material.COBBLESTONE);
                            index++;
                        } else {
                            this.cancel();
                        }
                    }
                }.runTaskTimer(BucketsPlus.getPlugin(BucketsPlus.class), 0L, 1L);
            }
        }.runTaskAsynchronously(BucketsPlus.getPlugin(BucketsPlus.class));
    }
}