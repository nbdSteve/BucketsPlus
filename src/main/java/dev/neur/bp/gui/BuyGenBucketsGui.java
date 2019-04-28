package dev.neur.bp.gui;

import dev.neur.bp.BucketsPlus;
import dev.neur.bp.method.FormatColor;
import dev.neur.bp.method.itemCreation.CraftItem;

public class BuyGenBucketsGui extends AbstractGui {

    /**
     * Constructor to create the Gui, add all items with their respective listeners
     */
    public BuyGenBucketsGui() {
        super(BucketsPlus.files.get("config").getInt("buckets-gui.size"),
                FormatColor.applyCodes(BucketsPlus.files.get("config").getString("buckets-gui.name")));
        //Add all of the items to the Gui from the configuration
        for (int i = 0; i <= 54; i++) {
            final int configId = i;
            setItemInSlot(BucketsPlus.files.get("config").getInt("buckets-gui." + configId + ".slot"),
                    new CraftItem(BucketsPlus.files.get("config").getString("buckets-gui." + configId + ".material"),
                            BucketsPlus.files.get("config").getString("buckets-gui." + configId + ".name"),
                            BucketsPlus.files.get("config").getStringList("buckets-gui." + configId + ".lore"),
                            BucketsPlus.files.get("config").getStringList("buckets-gui." + configId + ".enchantments"),
                            "null", 0, null).getItem(), player -> {
                        //Add respective listeners to each item
                        try {
                            if (BucketsPlus.files.get("config").getBoolean("buckets-gui." + configId + ".open-vertical")) {

                            }
                            if (BucketsPlus.files.get("config").getBoolean("buckets-gui." + configId + ".open-horizontal")) {

                            }
                            if (BucketsPlus.files.get("config").getBoolean("buckets-gui." + configId + ".exit-gui")) {
                                player.closeInventory();
                            }
                        } catch (NullPointerException bucketNotFound) {
                            player.closeInventory();
                        }
                    });
        }
    }
}
