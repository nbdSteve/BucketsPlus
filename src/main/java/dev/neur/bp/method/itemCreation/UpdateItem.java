package dev.neur.bp.method.itemCreation;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Class that handles updating an items lore and meta
 */
public class UpdateItem {

    /**
     * Void method to update the item with the new lore & meta
     *
     * @param itemLore List<String>, the lore that should be set
     * @param itemMeta ItemMeta, the item meta that should be set
     * @param item     ItemStack, the item being updated
     */
    public static void updateItem(List<String> itemLore, ItemMeta itemMeta, ItemStack item) {
        short durability = item.getDurability();
        item.setDurability(durability);
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);
    }
}