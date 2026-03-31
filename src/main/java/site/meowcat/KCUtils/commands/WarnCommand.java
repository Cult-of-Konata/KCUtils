package site.meowcat.KCUtils.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import site.meowcat.KCUtils.WarningManager;

public class WarnCommand implements CommandExecutor {

    private final WarningManager warningManager;

    public WarnCommand(WarningManager warningManager) {
        this.warningManager = warningManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.isOp()) {
            sender.sendMessage("§cOnly operators can use this command.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("§cUsage: /warn <player> [reason]");
            return true;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

        if (target == null) {
            sender.sendMessage("§cPlayer not found.");
            return true;
        }

        warningManager.addWarning(target.getUniqueId());

        int count = warningManager.getWarnings(target.getUniqueId());

        sender.sendMessage("§eWarned §f" + target.getName() + " §e(" + count + " warnings)");
        if (target.isOnline()) {
            target.getPlayer().sendMessage("§cYou have been warned!");
            target.getPlayer().sendMessage("§7Total warnings: §f" + count);
        }

        return true;
    }
}