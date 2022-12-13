package de.tilldv.slutils.commands;

import de.tilldv.slutils.data.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class OverrideConfig implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) return false;
        DataManager.generateConfig("confirm");

        return true;
    }
}
