package site.meowcat.KCUtils.checker;

import org.apache.commons.lang3.concurrent.BackgroundInitializer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker {
    private final JavaPlugin plugin;

    public UpdateChecker(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void check() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                String current = plugin.getPluginMeta().getVersion();

                URL url = new URL(
                        "https://hangar.papermc.io/api/v1/projects/Cult-of-Konata/KCUtils/versions"
                );

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(url.openStream())
                );

                String reponse = reader.readLine();

                if (!reponse.contains(current)) {
                    plugin.getLogger().info("A new version of KCUtils is available: " + reponse);
                    plugin.getLogger().info("Download it at: https://hangar.papermc.io/Cult-of-Konata/KCUtils");
                }
            } catch (Exception e) {
                plugin.getLogger().info("Failed to check for updates.");
            }
        });
    }
}
