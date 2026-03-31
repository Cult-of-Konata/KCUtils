package site.meowcat.KCUtils;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class WarningManager {
    private final HashMap<UUID, Integer> warnings = new HashMap<>();
    private final JavaPlugin plugin;
    private File file;
    private FileConfiguration config;

    public WarningManager(JavaPlugin plugin) {
        this.plugin = plugin;
        load();
    }

    private void load() {
        file = new File(plugin.getDataFolder(), "warnings.yml");
        if (!file.exists()) {
            plugin.getDataFolder().mkdirs();
            plugin.saveResource("warnings.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);

        for (String key: config.getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            warnings.put(uuid, config.getInt(key));
        }
    }

    public void save() {
        for (UUID uuid: warnings.keySet()) {
            config.set(uuid.toString(), warnings.get(uuid));
        }
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getWarnings(UUID uuid) {
        return warnings.getOrDefault(uuid, 0);
    }
    public void addWarning(UUID uuid) {
        warnings.put(uuid, getWarnings(uuid) + 1);
    }
    public void clearWarnings(UUID uuid) {
        warnings.remove(uuid);
        save();
    }
}
