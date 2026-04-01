package site.meowcat.KCUtils.checker;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker {
    private final JavaPlugin plugin;
    private boolean updateAvailable = false;
    private String latestVersion;

    public UpdateChecker(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void check() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                String currentVersion = plugin.getPluginMeta().getVersion();

                URL url = new URL(
                        "https://hangar.papermc.io/api/v1/projects/Cult-of-Konata/KCUtils/versions"
                );

                InputStreamReader reader = new InputStreamReader(url.openStream());
                JsonArray versions = new JsonParser().parse(reader).getAsJsonArray();
                JsonObject newest = versions.get(0).getAsJsonObject();
                latestVersion = newest.get("version").getAsString();

                if (!currentVersion.equalsIgnoreCase(latestVersion)) {
                    updateAvailable = true;
                    plugin.getLogger().info("A new update is available! Current version: " + currentVersion + ", Latest version: " + latestVersion);
                    plugin.getLogger().info("https://hangar.papermc.io/Cult-of-Konata/KCUtils" + latestVersion);
                } else {
                    plugin.getLogger().info("You are running the latest version of KCUtils.");
                }
            } catch (Exception e) {
               plugin.getLogger().info("An error occurred while checking for updates.");
            }
        });
    }

    public boolean isUpdateAvailable() {
        return updateAvailable;
    }
    public String getLatestVersion() {
        return latestVersion;
    }
}