package de.tilldv.slutils.commands;

import de.tilldv.slutils.Main;
import de.tilldv.slutils.data.DataStorage;
import de.tilldv.slutils.data.DataTypes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginAccess implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(args.length > 0)) {
            sender.sendMessage("Invalid Argument.");
            return true;
        }

        switch (args[0]) {
            case "open" -> DataStorage.loginAccess = DataTypes.AccessMode.OPEN;
            case "known" -> DataStorage.loginAccess = DataTypes.AccessMode.KNOWN;
            case "whitelist" -> DataStorage.loginAccess = DataTypes.AccessMode.WHITELIST;
            case "closed" -> DataStorage.loginAccess = DataTypes.AccessMode.CLOSED;
            case "get" -> sender.sendMessage("The login access is set to " + DataStorage.loginAccess.toString() + ".");
            default -> sender.sendMessage("Invalid argument.");
        }

        if (!args[0].equalsIgnoreCase("get")) sender.sendMessage("Der Zugriff zum Ping wurde auf " + args[0] + " gesetzt.");
        Main.getInstance().getConfig().set("loginaccess", DataStorage.loginAccess.toString());
        Main.getInstance().saveConfig();


        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return new ArrayList<>(Arrays.asList("open", "known", "whitelist", "closed", "get"));
    }

}
