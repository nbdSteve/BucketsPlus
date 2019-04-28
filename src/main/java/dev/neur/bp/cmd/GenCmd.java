package dev.neur.bp.cmd;

import dev.neur.bp.external.NBTHelper;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GenCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("gen")) {
                ItemStack item = new ItemStack(Material.DIAMOND);
                item = NBTHelper.addUniqueBucketData(item);
                ((Player) sender).getInventory().addItem(item);
            }
        }
        return true;
    }
}
