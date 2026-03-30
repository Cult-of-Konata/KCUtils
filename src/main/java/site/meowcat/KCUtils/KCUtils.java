package site.meowcat.KCUtils;

import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;
import site.meowcat.KCUtils.commands.AboutCommand;
import site.meowcat.KCUtils.commands.BanIpCommand;

public final class KCUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("about").setExecutor(new AboutCommand(this));
        this.getCommand("ban-ip").setExecutor(new BanIpCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
