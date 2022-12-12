package de.tilldv.slutils.commands;

import de.tilldv.slutils.Main;
import de.tilldv.slutils.data.DataStorage;
import de.tilldv.slutils.data.DataTypes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PingAccess implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("open")) {
                DataStorage.pingAccess = DataTypes.AccessMode.OPEN;
            } else if (args[0].equalsIgnoreCase("known")) {
                DataStorage.pingAccess = DataTypes.AccessMode.KNOWN;
            } else if (args[0].equalsIgnoreCase("blacklist")) {
                DataStorage.pingAccess = DataTypes.AccessMode.BLACKLIST;
            } else if (args[0].equalsIgnoreCase("whitelist")) {
                DataStorage.pingAccess = DataTypes.AccessMode.WHITELIST;
            } else if (args[0].equalsIgnoreCase("closed")) {
                DataStorage.pingAccess = DataTypes.AccessMode.CLOSED;
            } else if (args[0].equalsIgnoreCase("get")) {
                sender.sendMessage("Der Modus ist momentan " + DataStorage.pingAccess.toString());

            } else {
                sender.sendMessage("Ungültiges Argument.");
                return true;
            }

            if (!args[0].equalsIgnoreCase("get")) sender.sendMessage("Der Zugriff zum Ping wurde auf " + args[0] + " gesetzt.");
            Main.getInstance().getConfig().set("pingaccess", DataStorage.pingAccess.toString());
            Main.getInstance().saveConfig();


        } else sender.sendMessage("Invalid Argument.");



        return true;
    }
}
