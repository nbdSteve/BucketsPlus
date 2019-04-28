package dev.neur.bp.generators;

import dev.neur.bp.BucketsPlus;
import dev.neur.bp.event.GenBucketGenerationEvent;
import dev.neur.bp.event.GenBucketUseEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class BlockGenerationFromArray {

    private BukkitTask bucketGenerationTask;
    private BukkitTask blockRemovalTask;
    private int indexOfArray;

    public BlockGenerationFromArray(ArrayList<String> coordinatesForGeneration, GenBucketUseEvent bucketUseEvent) {
        indexOfArray = 0;
        this.bucketGenerationTask = Bukkit.getScheduler().runTaskTimerAsynchronously(BucketsPlus.getPlugin(BucketsPlus.class), () -> {
            if (indexOfArray < coordinatesForGeneration.size()) {
                //Get the coordinates from the array
                String[] blockCoordinates = coordinatesForGeneration.get(indexOfArray).split(":");
                //Remove them straight away
                indexOfArray++;
                Block currentBlock = Bukkit.getServer().getWorld(bucketUseEvent.getBucketOwner().getWorld().getName()).getBlockAt(
                        Integer.parseInt(blockCoordinates[0]), Integer.parseInt(blockCoordinates[1]), Integer.parseInt(blockCoordinates[2]));
                if (currentBlock.getType().equals(Material.AIR)) {
                    this.blockRemovalTask = Bukkit.getScheduler().runTask(BucketsPlus.getPlugin(BucketsPlus.class), () -> {
                        Block blockToChange = Bukkit.getServer().getWorld(bucketUseEvent.getBucketOwner().getWorld().getName())
                                .getBlockAt(currentBlock.getX(), currentBlock.getY(), currentBlock.getZ());
                        BlockPlaceEvent blockGenerationEvent =
                                new BlockPlaceEvent(blockToChange, blockToChange.getState(), blockToChange,
                                        new ItemStack(Material.BEDROCK), bucketUseEvent.getBucketOwner(), false);
                        Bukkit.getServer().getPluginManager().callEvent(blockGenerationEvent);
                        if (!blockGenerationEvent.isCancelled()) {
                            Bukkit.getServer().getPluginManager().callEvent(new GenBucketGenerationEvent(bucketUseEvent.getBucketOwner(), blockToChange, Material.COBBLESTONE));
                        } else {
                            blockRemovalTask.cancel();
                        }
                    });
                } else {
                    bucketGenerationTask.cancel();
                }
            }
        }, 0L, 2L);
    }

    public BukkitTask getBucketGenerationTask() {
        return bucketGenerationTask;
    }

    public BukkitTask getBlockRemovalTask() {
        return blockRemovalTask;
    }
}