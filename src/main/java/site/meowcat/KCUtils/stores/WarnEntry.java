package site.meowcat.KCUtils.stores;

public class WarnEntry {
    private final String reason;
    private final String moderator;
    private final long timestamp;

    public WarnEntry(String reason, String moderator, long timestamp) {
        this.reason = reason;
        this.moderator = moderator;
        this.timestamp = timestamp;
    }

    public String getReason() {
        return reason;
    }
    public String getModerator() {
        return moderator;
    }
    public long getTimestamp() {
        return timestamp;
    }
}
