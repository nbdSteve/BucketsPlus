package dev.neur.bp.event;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GenBucketUseEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Player bucketOwner;
    private Block blockPlacedOn;
    private BlockFace placedOnFace;
    private int numberOfBlocksToGenerate;
    private long delayBetweenBlockGeneration;
    private String generationType;
    private boolean cancel;

    public GenBucketUseEvent(Player bucketOwner, Block blockPlacedOn, BlockFace placedOnFace, int numberOfBlocksToGenerate, long delayBetweenBlockGeneration, String generationType) {
        this.bucketOwner = bucketOwner;
        this.blockPlacedOn = blockPlacedOn;
        this.placedOnFace = placedOnFace;
        this.numberOfBlocksToGenerate = numberOfBlocksToGenerate;
        this.delayBetweenBlockGeneration = delayBetweenBlockGeneration;
        this.generationType = generationType;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getBucketOwner() {
        return bucketOwner;
    }

    public Block getBlockPlacedOn() {
        return blockPlacedOn;
    }

    public BlockFace getPlacedOnFace() {
        return placedOnFace;
    }

    public int getNumberOfBlocksToGenerate() {
        return numberOfBlocksToGenerate;
    }

    public long getDelayBetweenBlockGeneration() {
        return delayBetweenBlockGeneration;
    }

    public String getGenerationType() {
        return generationType;
    }
}