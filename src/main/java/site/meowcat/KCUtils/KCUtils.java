package site.meowcat.KCUtils;

import net.kyori.adventure.text.Component;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import site.meowcat.KCUtils.commands.AboutCommand;
import site.meowcat.KCUtils.commands.BanIpCommand;
import site.meowcat.KCUtils.commands.WarnCommand;
import site.meowcat.KCUtils.commands.WarningsCommand;


public final class KCUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        WarningManager warningManager = new WarningManager(this);
        Metrics metrics = new Metrics(this, 30511);
        this.getCommand("about").setExecutor(new AboutCommand(this));
        this.getCommand("ban-ip").setExecutor(new BanIpCommand(this));
        this.getCommand("warn").setExecutor(new WarnCommand(warningManager));
        this.getCommand("warnings").setExecutor(new WarningsCommand(warningManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
