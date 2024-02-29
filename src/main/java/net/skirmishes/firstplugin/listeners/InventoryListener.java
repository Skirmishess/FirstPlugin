package net.skirmishes.firstplugin.listeners;

import de.tr7zw.nbtapi.NBTItem;
import net.skirmishes.firstplugin.Core;
import net.skirmishes.firstplugin.managers.ServerSelectorCreator;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Weather;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener
{
    @EventHandler
    public void onInventoryEvent(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        //player.sendMessage(event.getSlot()+"");

        if (event.getClickedInventory().getType() == InventoryType.PLAYER)
        {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getItemMeta() == null) return;
            if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

            NBTItem nbtItem = new NBTItem(event.getCurrentItem());

            if (nbtItem.getBoolean("isServerSelector"))
            {
                ServerSelectorCreator.createGUI(player);
                return;
            }
            if (nbtItem.getBoolean("isCosmtics"))
            {
                return;
            }
        }
    }
}
