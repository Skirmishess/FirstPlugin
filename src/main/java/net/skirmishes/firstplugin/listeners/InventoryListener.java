package net.skirmishes.firstplugin.listeners;

import de.tr7zw.nbtapi.NBTItem;
import net.skirmishes.firstplugin.Core;
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
                Location location = new Location(Bukkit.getWorld("Cannon"), -639, 3, -618);
                player.teleport(location);
            }
            if (nbtItem.getBoolean("isCosmtics"))
            {
                Location location = new Location(Bukkit.getWorld("Cannon"), -639, 3, -618);
                player.teleport(location);
            }
        }

        if (!event.getView().getTitle().equals(Core.cc(Core.plugin.configFile.getString("GUI.Title")))) return;

        event.setCancelled(true);
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        NBTItem nbtItem = new NBTItem(event.getCurrentItem());

        if (nbtItem.getBoolean("isTeleportItem"))
        {
            Location location = new Location(Bukkit.getWorld("Cannon"), -639, 3, -618);
            player.teleport(location);
        }
        if (nbtItem.getBoolean("isTextFile"))
        {
            player.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" &c&lYou wish you were sexy."));
        }
        if (nbtItem.getBoolean("isGiveItem"))
        {
            player.giveExpLevels(30);
        }
        if (nbtItem.getBoolean("isSetGamemode"))
        {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" &7Gamemode has been updated."));
        }
        if (nbtItem.getBoolean("isKickPlayer"))
        {
            player.kickPlayer(" &c&lGET FUCKED NERD. HAHA LEARN TO NOT GET KICKED FUCKER");
            player.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" &7Gamemode has been updated."));
        }
    }
}
