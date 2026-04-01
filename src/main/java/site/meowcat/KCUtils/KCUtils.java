package site.meowcat.KCUtils;

import java.util.logging.Logger;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import site.meowcat.KCUtils.checker.UpdateChecker;
import site.meowcat.KCUtils.commands.*;


public final class KCUtils extends JavaPlugin {
    private static final Logger LOGGER = Logger.getLogger("Minecraft");
    @Override
    public void onEnable() {
        getLogger().info("KCUtils is now enabled!");
        new UpdateChecker(this).check();
        // Note that all commands that need WarningManager have "warningManager" as a parameter.
        WarningManager warningManager = new WarningManager(this);
        createCharts();
        getLogger().info("Registering commands...");
        this.getCommand("about").setExecutor(new AboutCommand(this));
        this.getCommand("ban-ip").setExecutor(new BanIpCommand(this));
        this.getCommand("warn").setExecutor(new WarnCommand(warningManager));
        this.getCommand("warnings").setExecutor(new WarningsCommand(warningManager));
        this.getCommand("warnclear").setExecutor(new WarnClearCommand(warningManager));
        getLogger().info("Commands registered!");
    }

    public void createCharts() {
        Metrics metrics = new Metrics(this, 30511);
        metrics.addCustomChart(new SimplePie("used_language", () -> {
            return getConfig().getString("language", "English");
        }));
    }

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        UpdateChecker checker = new UpdateChecker(this);
        if (!event.getPlayer().isOp()) return;

        if (checker.isUpdateAvailable()) {
            event.getPlayer().sendMessage("§c KCUtils update available!");
            event.getPlayer().sendMessage("§7 Latest version: " + checker.getLatestVersion() + "");
            event.getPlayer().sendMessage("§7https://hangar.papermc.io/Cult-of-Konata/KCUtils");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("KCUtils is now shutting down...");
        getLogger().info("KCUtils has been disabled!");
    }
}
