package de.tilldv.slutils.data;

import de.tilldv.slutils.Main;
import org.bukkit.Bukkit;

public class DataManager {
    public static void initData() {
        DataStorage.registerLocation = DataTypes.ExecuteLocation.LOGIN;
        DataStorage.pingAccess = DataTypes.AccessMode.KNOWN;
        DataStorage.loginAccess = DataTypes.AccessMode.KNOWN;

    }

    public static void generateConfig(String confirmation) {
        if (!confirmation.equalsIgnoreCase("confirm")) return;
        initData();
        Main.getInstance().getConfig().set("registerlocation", DataTypes.ExecuteLocation.LOGIN.toString());
        Main.getInstance().getConfig().set("pingaccess", DataTypes.AccessMode.KNOWN.toString());
        Main.getInstance().getConfig().set("loginaccess", DataTypes.AccessMode.KNOWN.toString());
        Main.getInstance().saveConfig();


    }

    public static void loadConfig() {
        Bukkit.getLogger().info("Config wird geladen...");

        try {
            Main.getInstance().reloadConfig();
            DataStorage.registerLocation = DataTypes.ExecuteLocation.valueOf(Main.getInstance().getConfig().getString("registerlocation"));
            DataStorage.pingAccess = DataTypes.AccessMode.valueOf(Main.getInstance().getConfig().getString("pingaccess"));
            DataStorage.loginAccess = DataTypes.AccessMode.valueOf(Main.getInstance().getConfig().getString("loginaccess"));
            Bukkit.getLogger().info("Config geladen.");

        } catch (Exception e) {
            Bukkit.getLogger().warning("Ein Fehler ist aufgetreten");
            throw e;
        }
    }
}
