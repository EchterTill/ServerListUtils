package de.tilldv.slutils.listeners;

import de.tilldv.slutils.data.DataStorage;
import de.tilldv.slutils.data.DataTypes;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

public class LoginListener implements @NotNull Listener {
    @EventHandler
    public void onServerListPing(PlayerLoginEvent event) {
        Bukkit.getLogger().info("Spieler eingeloggt");
        String address = event.getAddress().toString().substring(1).split(":")[0];
        String name = DataStorage.IPMap.get(address);


        switch (DataStorage.loginAccess) {
            case OPEN -> {
                event.allow();
            }
            case KNOWN, BLACKLIST -> {
                if (name != null) {
                    event.allow();
                } else event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Server is restricted to known players.");
            }
            case WHITELIST -> {
                if (Bukkit.getOfflinePlayer(name).isWhitelisted()) {
                } else event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "Server is restricted to whitelisted players players.");
            }
            case CLOSED -> {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "This server is closed.");
            }
        }

        if (DataStorage.registerLocation == DataTypes.ExecuteLocation.LOGIN) {
            DataStorage.IPMap.put(address, event.getPlayer().getName());
        }




    }
}
