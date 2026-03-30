package site.meowcat.KCUtils.commands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.BanList;
import org.bukkit.BanEntry;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.Date;

public class BanIpCommand implements CommandExecutor {
    private final JavaPlugin plugin;

    public BanIpCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NonNull @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("§c You must be a server operator to use this command!");
        }
        if (args.length == 0) {
            sender.sendMessage("§cUsage: /banip <IP or player> <reason>");
            return false;
        }
        String target = args[0];
        String reason = args.length > 1 ? String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length)) : "No reason provided.";
        String ipToBan;

        // check if the target is an online player
        Player player = Bukkit.getPlayerExact(target);
        if (player != null) {
            ipToBan = player.getAddress().getAddress().getHostAddress();
        } else {
            // otherwise just assume the argument is an IP address
            ipToBan = target;
        }

        // ban the IP
        BanList banList = Bukkit.getBanList(BanList.Type.IP);
        banList.addBan(ipToBan, reason, (Date) null, sender.getName());
        Bukkit.getOnlinePlayers().stream()
                .filter(p -> p.getAddress().getAddress().getHostAddress().equals(ipToBan))
                .forEach(p -> p.kickPlayer("§cYou have been banned: " + reason));
        sender.sendMessage("§aBanned IP §f" + ipToBan + "§afor reason: §f" + reason);
        return true;
    }
}
