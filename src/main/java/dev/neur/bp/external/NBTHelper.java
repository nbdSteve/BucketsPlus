package dev.neur.bp.external;

import dev.neur.bp.external.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public class NBTHelper {

    public static ItemStack addUniqueBucketData(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("bucket-plus", true);
        nbtItem.setString("generation-type", "vertical");
        nbtItem.setInteger("number-of-blocks-to-generate", 60);
        nbtItem.setLong("delay-between-block-generation", 5L);
        return nbtItem.getItem();
    }
}