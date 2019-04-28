package dev.neur.bp.event.callcustomevents;

import dev.neur.bp.event.GenBucketUseEvent;
import dev.neur.bp.external.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void playerClickGround(PlayerInteractEvent event) {
        //Check that the event is not cancelled
        if (event.isCancelled()) return;
        //Store the player
        Player player = event.getPlayer();
        //Validate that the player is clicking correctly
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;
        //Validate that the item can be a gen bucket
        //Create a new nbt object for the players current item
        NBTItem nbtItem = new NBTItem(player.getItemInHand());
        //Verify that the player is placing a gen bucket
        if (!nbtItem.hasNBTData() || !nbtItem.getBoolean("bucket-plus")) return;
        //Store information about the bucket
        int numberOfBlocksToGenerate = nbtItem.getInteger("number-of-blocks-to-generate");
        long delayBetweenBlockGeneration = nbtItem.getLong("delay-between-block-generation");
        String generationType = nbtItem.getString("generation-type");
        //Call the gen bucket event
        Bukkit.getServer().getPluginManager().callEvent(new GenBucketUseEvent(player, event.getClickedBlock(),
                event.getBlockFace(), numberOfBlocksToGenerate, delayBetweenBlockGeneration, generationType));
    }
}