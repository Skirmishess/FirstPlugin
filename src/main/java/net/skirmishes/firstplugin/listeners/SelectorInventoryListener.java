package net.skirmishes.firstplugin.listeners;

import de.tr7zw.nbtapi.NBTItem;
import net.skirmishes.firstplugin.Core;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SelectorInventoryListener implements Listener
{
    @EventHandler
    public void onInventoryEvent(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();

        if (!event.getView().getTitle().equals(Core.cc(Core.plugin.selectorFile.getString("GUI.Title")))) return;

        event.setCancelled(true);
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        NBTItem nbtItem = new NBTItem(event.getCurrentItem());

        if (nbtItem.getBoolean("isSkyblock"))
        {
            player.sendMessage(Core.cc("&bYou are moving to skyblock"));
            return;
        }
    }
}
