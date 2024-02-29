package net.skirmishes.firstplugin.managers;

import net.skirmishes.firstplugin.Core;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class JoinItems
{
    public static void giveJoinItems(Player player)
    {
        for (String configID : Core.plugin.itemJoinFile.getConfigurationSection("Items").getKeys(false))
        {
            if (configID.equalsIgnoreCase("BORDER")) continue;
            ItemBuilder itemBuilder = new ItemBuilder(Core.plugin.getMaterialByName(Core.plugin.itemJoinFile.getString("Items."+configID+".Material")), 1,
                    Core.plugin.itemJoinFile.getInt("Items."+configID+".Damage"));
            itemBuilder.setName(Core.plugin.itemJoinFile.getString("Items."+configID+".Display"));
            itemBuilder.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
            itemBuilder.addLore(Core.plugin.itemJoinFile.getStringList("Items."+configID+".Lore"));
            if (configID.equals("1")) itemBuilder.setNBTBoolean("isServerSelector", true);
            if (configID.equals("2")) itemBuilder.setNBTBoolean("isCosmtics", true);

            player.getInventory().setItem(Core.plugin.itemJoinFile.getInt("Items."+configID+".Slot"), itemBuilder.toNBTItemStack());
        }
    }
}
