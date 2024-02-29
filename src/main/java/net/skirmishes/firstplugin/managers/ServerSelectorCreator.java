package net.skirmishes.firstplugin.managers;

import net.skirmishes.firstplugin.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

public class ServerSelectorCreator
{
    public static void createGUI(Player player)
    {
        Inventory inventoryGUI = Bukkit.getServer().createInventory(player, Core.plugin.selectorFile.getInt("GUI.Rows")*9,
                Core.cc(Core.plugin.selectorFile.getString("GUI.Title")));

        ItemBuilder itemBuilderBorder = new ItemBuilder(Core.plugin.getMaterialByName(Core.plugin.selectorFile.getString("GUI.Items.BORDER.Material")), 1, Core.plugin.selectorFile.getInt("GUI.Items.BORDER.Damage"));
        itemBuilderBorder.setName(Core.plugin.selectorFile.getString("GUI.Items.BORDER.Display"));
        itemBuilderBorder.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
        itemBuilderBorder.addLore(Core.plugin.selectorFile.getStringList("GUI.Items.BORDER.Lore"));
        itemBuilderBorder.setNBTBoolean("isBorderItem", true);

        if (Core.plugin.selectorFile.getBoolean("GUI.Items.BORDER.Fill"))
        {
            for (int i = 0; i < Core.plugin.selectorFile.getInt("GUI.Rows")*9; i++) {
                if (player.getInventory().getItem(i) != null) continue;

                inventoryGUI.setItem(i, itemBuilderBorder.toNBTItemStack());
            }
        }
        else {
            for (int slotID : Core.plugin.selectorFile.getIntegerList("GUI.Items.BORDER.Slots")) {
                if (player.getInventory().getItem(slotID) != null) continue;

                inventoryGUI.setItem(slotID, itemBuilderBorder.toNBTItemStack());
            }
        }

        for (String configID : Core.plugin.selectorFile.getConfigurationSection("GUI.Items").getKeys(false))
        {
            if (configID.equalsIgnoreCase("BORDER")) continue;
            ItemBuilder itemBuilder = new ItemBuilder(Core.plugin.getMaterialByName(Core.plugin.selectorFile.getString("GUI.Items."+configID+".Material")), 1,
                    Core.plugin.selectorFile.getInt("GUI.Items."+configID+".Damage"));
            itemBuilder.setName(Core.plugin.selectorFile.getString("GUI.Items."+configID+".Display"));
            itemBuilder.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
            itemBuilder.addLore(Core.plugin.selectorFile.getStringList("GUI.Items."+configID+".Lore"));
            if (configID.equals("1")) itemBuilder.setNBTBoolean("isSkyblock", true);

            inventoryGUI.setItem(Core.plugin.selectorFile.getInt("GUI.Items."+configID+".Slot"), itemBuilder.toNBTItemStack());
        }
        player.openInventory(inventoryGUI);
    }
}
