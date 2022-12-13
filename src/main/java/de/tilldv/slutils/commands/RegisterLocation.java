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
        if (!(args.length > 0)) return false;

        switch (args[0]) {
            case "never" -> DataStorage.registerLocation = DataTypes.ExecuteLocation.NEVER;
            case "join" -> DataStorage.registerLocation = DataTypes.ExecuteLocation.JOIN;
            case "login" -> DataStorage.registerLocation = DataTypes.ExecuteLocation.LOGIN;
            default -> sender.sendMessage("Invalid argument.");
        }

        Main.getInstance().getConfig().set("registerlocation", DataStorage.registerLocation.toString());
        Main.getInstance().saveConfig();
        return true;
    }
}
