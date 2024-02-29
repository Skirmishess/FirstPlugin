package net.skirmishes.firstplugin.listeners;

import net.skirmishes.firstplugin.managers.GUICreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener
{
    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        GUICreator.giveJoinItems(player);
    }
}
