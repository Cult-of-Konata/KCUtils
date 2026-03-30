package site.meowcat.KCUtils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class AboutCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public AboutCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        var desc = plugin.getDescription();

        String name = desc.getName();
        String version = desc.getVersion();
        String authors = String.join(", ", desc.getAuthors());
        String website = desc.getWebsite() != null ? desc.getWebsite() : "N/A";

        sender.sendMessage("§6" + name + " §7v" + version);
        sender.sendMessage("§7Authors: §f" + authors);
        sender.sendMessage("§7Website: §f" + website);

        return true;
    }
}