package site.meowcat.KCUtils.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import site.meowcat.KCUtils.WarningManager;

public class WarnClearCommand implements CommandExecutor {
    private final WarningManager warningManager;
    public WarnClearCommand(WarningManager warningManager) {
        this.warningManager = warningManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NonNull @NotNull String[] args) {
        if (!sender.isOp() || !sender.hasPermission("kcutils.warnclear")) {
            sender.sendMessage("§c You must be a server operator to use this command!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§cUsage: /warnclear <player>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            sender.sendMessage("§cPlayer not found!");
            return true;
        }

        warningManager.clearWarnings(target.getUniqueId());

        sender.sendMessage("§a Cleared all warnings for §f" + target.getName());
        return true;
    }
}
