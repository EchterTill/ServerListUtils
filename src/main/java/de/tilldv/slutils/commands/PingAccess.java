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

public class PingAccess implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(args.length > 0)) {
            sender.sendMessage("Invalid Argument.");
            return true;
        }


        switch (args[0]) {
            case "open" -> DataStorage.pingAccess = DataTypes.AccessMode.OPEN;
            case "known" -> DataStorage.pingAccess = DataTypes.AccessMode.KNOWN;
            case "blacklist" -> DataStorage.pingAccess = DataTypes.AccessMode.BLACKLIST;
            case "whitelist"-> DataStorage.pingAccess = DataTypes.AccessMode.WHITELIST;
            case "closed" -> DataStorage.pingAccess = DataTypes.AccessMode.CLOSED;
            case "get" -> sender.sendMessage("Der Modus ist momentan " + DataStorage.pingAccess.toString());
            default -> sender.sendMessage("Ungültiges Argument.");
        }

        Main.getInstance().getConfig().set("pingaccess", DataStorage.pingAccess.toString());
        Main.getInstance().saveConfig();
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return new ArrayList<>(Arrays.asList("open", "known", "blacklist", "whitelist", "closed", "get"));
    }
}
