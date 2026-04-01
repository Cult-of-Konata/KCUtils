package site.meowcat.KCUtils;

import net.kyori.adventure.text.Component;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.plugin.java.JavaPlugin;
import site.meowcat.KCUtils.commands.*;


public final class KCUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Note that all commands that need WarningManager have "warningManager" as a parameter.
        WarningManager warningManager = new WarningManager(this);
        createCharts();
        this.getCommand("about").setExecutor(new AboutCommand(this));
        this.getCommand("ban-ip").setExecutor(new BanIpCommand(this));
        this.getCommand("warn").setExecutor(new WarnCommand(warningManager));
        this.getCommand("warnings").setExecutor(new WarningsCommand(warningManager));
        this.getCommand("warnclear").setExecutor(new WarnClearCommand(warningManager));
    }

    public void createCharts() {
        Metrics metrics = new Metrics(this, 30511);
        metrics.addCustomChart(new SimplePie("used_language", () -> {
            return getConfig().getString("language", "English");
        }));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
