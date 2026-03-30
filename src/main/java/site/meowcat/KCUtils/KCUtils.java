package site.meowcat.KCUtils;

import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;
import site.meowcat.KCUtils.commands.AboutCommand;

public final class KCUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("about").setExecutor(new AboutCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
