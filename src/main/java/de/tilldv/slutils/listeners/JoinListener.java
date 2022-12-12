package de.tilldv.slutils.listeners;

import de.tilldv.slutils.data.DataStorage;
import de.tilldv.slutils.data.DataTypes;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinListener  implements Listener {
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        String address = event.getPlayer().getAddress().toString().substring(1).split(":")[0];
        Bukkit.getLogger().info(address);
        event.joinMessage(Component.text(ChatColor.AQUA + event.getPlayer().getName() + ChatColor.GREEN + " hat den Server betreten."));

        if (DataStorage.registerLocation == DataTypes.ExecuteLocation.JOIN) {
            DataStorage.IPMap.put(address, event.getPlayer().getName());
        }


    }
}
