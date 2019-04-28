package dev.neur.bp.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPlaceEvent;

public class GenBucketGenerationEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Player bucketOwner;
    private Block blockToChange;
    private Material blockTypeToGenerate;
    private boolean cancel;

    public GenBucketGenerationEvent(Player bucketOwner, Block blockToChange, Material blockTypeToGenerate) {
        this.bucketOwner = bucketOwner;
        this.blockToChange = blockToChange;
        this.blockTypeToGenerate = blockTypeToGenerate;
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

    public Block getBlockToChange() {
        return blockToChange;
    }

    public Material getBlockTypeToGenerate() {
        return blockTypeToGenerate;
    }
}
