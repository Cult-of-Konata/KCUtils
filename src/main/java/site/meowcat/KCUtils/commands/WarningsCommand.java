package site.meowcat.KCUtils.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import site.meowcat.KCUtils.WarningManager;

public class WarningsCommand implements CommandExecutor {

    private final WarningManager warningManager;

    public WarningsCommand(WarningManager warningManager) {
        this.warningManager = warningManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NonNull @NotNull String[] args) {
        Player target;
        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage("§c Console must specify a target.");
                return true;
            }
            target = player;
            } else {
                if (!sender.isOp()) {
                    sender.sendMessage("§c Only operators can view warnings of other players.");
                    return true;
                }
                target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage("§cNo targets matched selector.");
                }
            }
        int count = warningManager.getWarnings(target.getUniqueId());
        sender.sendMessage("§e" + target.getName() + " has §f" + count + "§e warnings.");
        return true;
        }
    }

