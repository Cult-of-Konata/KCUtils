package site.meowcat.KCUtils;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import site.meowcat.KCUtils.stores.WarnEntry;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WarningManager {
    private final HashMap<UUID, List<WarnEntry>> warnings = new HashMap<>();
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

        for (String key : config.getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            List<Map<?, ?>> list = config.getMapList(key);
            List<WarnEntry> entries = new ArrayList<>();

            for (Map<?, ?> map : list) {
                String reason = (String) map.get("reason");
                String mod = (String) map.get("moderator");
                long time = (long) map.get("timestamp");
                entries.add(new WarnEntry(reason, mod, time));
            }
            warnings.put(uuid, entries);
        }
    }

    public void save() {
        config.set("", null); // nah its fine dw about it

        for (UUID uuid : warnings.keySet()) {
            List<Map<String, Object>> list = new ArrayList<>();

            for (WarnEntry entry : warnings.get(uuid)) {
                Map<String, Object> map = new HashMap<>();
                map.put("reason", entry.getReason());
                map.put("moderator", entry.getModerator());
                map.put("timestamp", entry.getTimestamp());
                list.add(map);
            }
            config.set(uuid.toString(), list);
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWarnings(UUID uuid) {
        return warnings.getOrDefault(uuid, new ArrayList<>()).size();
    }

    public void addWarning(UUID uuid, String reason, String moderator) {
        warnings.computeIfAbsent(uuid, k -> new ArrayList<>()).add(new WarnEntry(reason, moderator, System.currentTimeMillis()));
    }

    public void clearWarnings(UUID uuid) {
        warnings.remove(uuid);
        save();
    }

    public List<WarnEntry> getHistory(UUID uuid) {
        return warnings.getOrDefault(uuid, new ArrayList<>());
    }
}
