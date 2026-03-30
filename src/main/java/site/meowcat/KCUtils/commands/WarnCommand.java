package site.meowcat.KCUtils.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.HashMap;
import java.util.UUID;

public class WarnCommand implements CommandExecutor {
    private final HashMap<UUID, Integer> warnings = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp() || !sender.hasPermission("KCUtils.warn")) {
            sender.sendMessage("§c Only operators can use this command.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("§cUsage: /warn <player> <reason>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage("§cNo targets matched selector!");
        }

        String reason = args.length > 1
                ? String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length))
                : "No reason provided.";
        int count = warnings.getOrDefault(target.getUniqueId(), 0) + 1;
        warnings.put(target.getUniqueId(), count);
        sender.sendMessage("§e Warned target: §f" + target.getName() + "§e(" + count + " warnings)"); // some nice string work here
        target.sendMessage("§c You have been warned for: " + reason);
        return true;
    }
}
