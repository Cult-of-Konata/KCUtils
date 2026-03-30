package site.meowcat.KCUtils;

import java.util.HashMap;
import java.util.UUID;

public class WarningManager {
    private final HashMap<UUID, Integer> warnings = new HashMap<>();

    public int getWarnings(UUID uuid) {
        return warnings.getOrDefault(uuid, 0);
    }
    public void addWarning(UUID uuid) {
        warnings.put(uuid, getWarnings(uuid) + 1);
    }
}
