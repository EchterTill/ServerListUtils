package de.tilldv.slutils;

import de.tilldv.slutils.commands.LoginAccess;
import de.tilldv.slutils.commands.OverrideConfig;
import de.tilldv.slutils.commands.PingAccess;
import de.tilldv.slutils.commands.RegisterLocation;
import de.tilldv.slutils.data.DataManager;
import de.tilldv.slutils.listeners.JoinListener;
import de.tilldv.slutils.listeners.LoginListener;
import de.tilldv.slutils.listeners.PingListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main INSTANCE;
    public Main() {
        INSTANCE = this;
    }
    @Override
    public void onEnable() {
        DataManager.loadConfig();

        getServer().getPluginManager().registerEvents(new PingListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new LoginListener(), this);

        this.getCommand("registerlocation").setExecutor(new RegisterLocation());
        this.getCommand("pingaccess").setExecutor(new PingAccess());
        this.getCommand("loginaccess").setExecutor(new LoginAccess());
        this.getCommand("overrideconfig").setExecutor(new OverrideConfig());

    }

    public static Main getInstance() {
        return INSTANCE;
    }





}
