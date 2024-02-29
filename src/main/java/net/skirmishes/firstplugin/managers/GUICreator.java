package net.skirmishes.firstplugin.managers;

import net.skirmishes.firstplugin.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.List;

public class GUICreator
{
    public static void createPublicGUI(Player player)
    {
        Inventory inventoryGUI = Bukkit.getServer().createInventory(null, Core.plugin.configFile.getInt("GUI.Rows")*9,
                Core.cc(Core.plugin.configFile.getString("GUI.Title")));

        if (Core.plugin.inventory == null) Core.plugin.inventory = inventoryGUI;
        player.openInventory(Core.plugin.inventory);
    }
    public static void createPrivateGUI(Player player)
    {
        Inventory inventoryGUI = Bukkit.getServer().createInventory(player, Core.plugin.configFile.getInt("GUI.Rows")*9,
                Core.cc(Core.plugin.configFile.getString("GUI.Title")));

        ItemBuilder itemBuilderBorder = new ItemBuilder(Core.plugin.getMaterialByName(Core.plugin.configFile.getString("GUI.Items.BORDER.Material")), 1, Core.plugin.configFile.getInt("GUI.Items.BORDER.Damage"));
        itemBuilderBorder.setName(Core.plugin.configFile.getString("GUI.Items.BORDER.Display"));
        itemBuilderBorder.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
        itemBuilderBorder.addLore(Core.plugin.configFile.getStringList("GUI.Items.BORDER.Lore"));
        itemBuilderBorder.setNBTBoolean("isBorderItem", true);

        if (Core.plugin.configFile.getBoolean("GUI.Items.BORDER.Fill"))
        {
            for (int i = 0; i < Core.plugin.configFile.getInt("GUI.Rows")*9; i++) {
                if (player.getInventory().getItem(i) != null) continue;

                inventoryGUI.setItem(i, itemBuilderBorder.toNBTItemStack());
            }
        }
        else {
            for (int slotID : Core.plugin.configFile.getIntegerList("GUI.Items.BORDER.Slots")) {
                if (player.getInventory().getItem(slotID) != null) continue;

                inventoryGUI.setItem(slotID, itemBuilderBorder.toNBTItemStack());
            }
        }

        for (String configID : Core.plugin.configFile.getConfigurationSection("GUI.Items").getKeys(false))
        {
            if (configID.equalsIgnoreCase("BORDER")) continue;
            ItemBuilder itemBuilder = new ItemBuilder(Core.plugin.getMaterialByName(Core.plugin.configFile.getString("GUI.Items."+configID+".Material")), 1,
                    Core.plugin.configFile.getInt("GUI.Items."+configID+".Damage"));
            itemBuilder.setName(Core.plugin.configFile.getString("GUI.Items."+configID+".Display"));
            itemBuilder.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
            itemBuilder.addLore(Core.plugin.configFile.getStringList("GUI.Items."+configID+".Lore"));
            if (configID.equals("1")) itemBuilder.setNBTBoolean("isTeleportItem", true);
            if (configID.equals("2")) itemBuilder.setNBTBoolean("isTextFile", true);
            if (configID.equals("3")) itemBuilder.setNBTBoolean("isGiveItem", true);
            if (configID.equals("4")) itemBuilder.setNBTBoolean("isSetGamemode", true);
            if (configID.equals("5")) itemBuilder.setNBTBoolean("isKickPlayer", true);

            inventoryGUI.setItem(Core.plugin.configFile.getInt("GUI.Items."+configID+".Slot"), itemBuilder.toNBTItemStack());
        }
        player.openInventory(inventoryGUI);
    }
}
