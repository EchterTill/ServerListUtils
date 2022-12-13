package de.tilldv.slutils.listeners;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import de.tilldv.slutils.data.DataStorage;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PingListener implements Listener {
    @EventHandler
    public void onServerListPing(PaperServerListPingEvent event) {
        String address = event.getAddress().toString().substring(1);
        Bukkit.getLogger().info(address);
        String name = null;
        try {
           name = DataStorage.IPMap.get(address);

        } catch (Exception e){
            Bukkit.getLogger().warning("Fehler in der IPMap");
        }


        switch (DataStorage.pingAccess) {
            case OPEN -> {
                if (name != null) motd(event, name);
            }
            case KNOWN -> {
                if (name == null) {
                    motd(event, name);
                } else event.setCancelled(true);
            }
            case BLACKLIST -> {
                if (name != null && !Bukkit.getOfflinePlayer(name).isBanned()) {
                    motd(event, name);
                } else event.setCancelled(true);
            }
            case WHITELIST -> {
                if (name != null && Bukkit.getOfflinePlayer(name).isWhitelisted()) {
                    motd(event, name);
                } else event.setCancelled(true);
            }
            case CLOSED -> event.setCancelled(true);
        }
    }
    private void motd(PaperServerListPingEvent event, String name) {

        String[] line1 = {
                "Hallo " + name + ".",
                "Guten Tag Herr " + name + "."
        };

        String[] line2 = {
                "Du bist schon " + Bukkit.getOfflinePlayer(name).getStatistic(Statistic.DEATHS) + " mal gestorben",
                "Du hast schon " + Bukkit.getOfflinePlayer(name).getStatistic(Statistic.PLAYER_KILLS) + " Spieler umgebracht."
        };

        event.motd(Component.text(line1[(int) (Math.random() * line1.length)] + "\n" + line2[(int) (Math.random() * line2.length)]));

    }



}
