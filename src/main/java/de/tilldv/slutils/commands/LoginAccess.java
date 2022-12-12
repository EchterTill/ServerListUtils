package de.tilldv.slutils.commands;

import de.tilldv.slutils.Main;
import de.tilldv.slutils.data.DataStorage;
import de.tilldv.slutils.data.DataTypes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class LoginAccess implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("open")) {
                DataStorage.loginAccess = DataTypes.AccessMode.OPEN;
            } else if (args[0].equalsIgnoreCase("known")) {
                DataStorage.loginAccess = DataTypes.AccessMode.KNOWN;
            } else if (args[0].equalsIgnoreCase("whitelist")) {
                DataStorage.loginAccess = DataTypes.AccessMode.WHITELIST;
            } else if (args[0].equalsIgnoreCase("closed")) {
                DataStorage.loginAccess = DataTypes.AccessMode.CLOSED;
            } else if (args[0].equalsIgnoreCase("get")) {
                sender.sendMessage("Der Status ist momentan " + DataStorage.loginAccess.toString());
            } else {
                sender.sendMessage("Ungültiges Argument.");
                return true;
            }

            if (!args[0].equalsIgnoreCase("get")) sender.sendMessage("Der Zugriff zum Ping wurde auf " + args[0] + " gesetzt.");
            Main.getInstance().getConfig().set("loginaccess", DataStorage.loginAccess.toString());
            Main.getInstance().saveConfig();
        } else sender.sendMessage("Invalid Argument.");



        return true;
    }
}
