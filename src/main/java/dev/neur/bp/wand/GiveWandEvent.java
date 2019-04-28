package dev.neur.bp.wand;

import dev.neur.bp.external.NBTHelper;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class GiveWandEvent implements Listener {

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().startsWith("--")) {
            
        }
        if (event.getMessage().equalsIgnoreCase("--gwand")) {
            event.setCancelled(true);
            ItemStack item = new ItemStack(Material.getMaterial("GOLD_HOE"));
            item = NBTHelper.addWandData(item);
            event.getPlayer().getInventory().addItem(item);
        }
    }
}
