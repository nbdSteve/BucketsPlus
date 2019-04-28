package dev.neur.bp.method.itemCreation;

import dev.neur.bp.BucketsPlus;
import dev.neur.bp.external.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

/**
 * Class that handles adding custom nbt data to items when the are created
 */
public class NBTCreator {

    /**
     * Adds the flags required for the tool to be registered by the plugin
     *
     * @param item         ItemStack, the item to tag
     * @param typeOfTool   String, the type of tool being created
     * @param idFromConfig Integer, the raw ID from the configuration files
     * @return
     */
    public static ItemStack addToolData(ItemStack item, String typeOfTool, int idFromConfig) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("ntool." + typeOfTool, true);
        nbtItem.setInteger("ntool.raw.id", idFromConfig);
        nbtItem.setInteger("ntool.uses", BucketsPlus.files.get(typeOfTool).getInt(typeOfTool + "-wands." + idFromConfig + ".uses.starting"));
        return nbtItem.getItem();
    }

    /**
     * Adds the flags required for the tool to be registered by the plugin
     *
     * @param item         ItemStack, the item to tag
     * @param typeOfTool   String, the type of tool being created
     * @param idFromConfig Integer, the raw ID from the configuration files
     * @param omniTool     boolean, if the tool should have omni functionality
     * @return
     */
    public static ItemStack addToolData(ItemStack item, String typeOfTool, int idFromConfig, boolean omniTool) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("ntool." + typeOfTool, true);
        nbtItem.setInteger("ntool.raw.id", idFromConfig);
        if (omniTool) {
            nbtItem.setBoolean("ntool.omni", true);
        }
        return nbtItem.getItem();
    }
}