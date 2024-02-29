package net.skirmishes.firstplugin.listeners;

import net.skirmishes.firstplugin.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (!event.getMessage().toLowerCase().contains("fuck")) return;

        player.sendMessage(Core.cc(Core.plugin.langFile.getString("Prefix")+" &cNo swearing please!"));
        event.setCancelled(true);
    }

}
