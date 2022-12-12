package de.tilldv.slutils.commands;

import de.tilldv.slutils.Main;
import de.tilldv.slutils.data.DataStorage;
import de.tilldv.slutils.data.DataTypes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class RegisterLocation implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("never")) {
                DataStorage.registerLocation = DataTypes.ExecuteLocation.NEVER;
            } else if (args[0].equalsIgnoreCase("join")) {
                DataStorage.registerLocation = DataTypes.ExecuteLocation.JOIN;
            } else if (args[0].equalsIgnoreCase("login")) {
                DataStorage.registerLocation = DataTypes.ExecuteLocation.LOGIN;
            } else {
                return true;
            }
            sender.sendMessage("Der Status wurde auf " + args[0] + " gesetzt.");
            Main.getInstance().getConfig().set("registerlocation", DataStorage.registerLocation.toString());
            Main.getInstance().saveConfig();


        } else sender.sendMessage("Ungültiges Argument.");




        return true;
    }
}
