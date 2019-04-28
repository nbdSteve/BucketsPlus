package dev.neur.bp.method.itemCreation;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles creating an ItemStack from a list of parameters
 */
public class CraftItem {
    //Store the item stack being created
    private ItemStack item;
    //Store the item meta
    private ItemMeta itemMeta;
    //Store the item lore
    private List<String> itemLore;

    /**
     * Constructor that will create a new ItemStack based off of the parameters
     *
     * @param material          String, the item material
     * @param name              String, the items display name
     * @param lore              List<String>, list of strings to add as the items lore
     * @param enchantments      List<String>, list of enchantments to add to the item
     * @param typeOfTool        String, the type of tool being created
     * @param idFromConfig      Integer, the raw tool ID from the configuration files
     * @param player            Player, the player to give the new item to - can be null
     */
    public CraftItem(String material, String name, List<String> lore, List<String> enchantments, String typeOfBucket, int idFromConfig, Player player) {
        item = createItem(material);
        itemMeta = item.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        }
        addLore(lore);
        itemMeta.setLore(itemLore);
        addEnchantments(enchantments);
        item.setItemMeta(itemMeta);
        if (player != null) {
            player.getInventory().addItem(NBTCreator.addToolData(item, typeOfBucket, idFromConfig));
        }
    }

    /**
     * Creates a new item of the given material
     *
     * @param material String, material name and damage value
     * @return ItemStack
     */
    private ItemStack createItem(String material) {
        String[] materialParts = material.split(":");
        return new ItemStack(Material.valueOf(materialParts[0].toUpperCase()), 1, Byte.parseByte(materialParts[1]));
    }

    /**
     * Adds the given lore to an item
     *
     * @param loreToAdd         List<String>, the lines of lore to add
     */
    private void addLore(List<String> loreToAdd) {
        itemLore = new ArrayList<>();
        if (loreToAdd != null) {
            for (String lineOfLore : loreToAdd) {
                itemLore.add(ChatColor.translateAlternateColorCodes('&', lineOfLore));
            }
        }
    }

    /**
     * Add a list of enchantments to an item
     *
     * @param enchantmentsToAdd List<String>, list of enchantments to add
     */
    private void addEnchantments(List<String> enchantmentsToAdd) {
        if (enchantmentsToAdd != null) {
            for (String enchantment : enchantmentsToAdd) {
                String[] enchantmentParts = enchantment.split(":");
                itemMeta.addEnchant(Enchantment.getByName(enchantmentParts[0].toUpperCase()),
                        Integer.parseInt(enchantmentParts[1]), true);
                if (enchantmentParts[0].equalsIgnoreCase("lure")) {
                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
            }
        }
    }

    /**
     * Getter for the item
     *
     * @return ItemStack
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * Getter for the items meta data
     *
     * @return ItemMeta
     */
    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    /**
     * Getter for the items lore
     *
     * @return List<String>
     */
    public List<String> getItemLore() {
        return itemLore;
    }
}
