package site.meowcat.KCUtils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import site.meowcat.KCUtils.checker.UpdateChecker;

public class UpdateCommand implements CommandExecutor {
    private final UpdateChecker checker;

    public UpdateCommand(UpdateChecker checker) {
        this.checker = checker;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NonNull @NotNull String[] args) {
        if (!sender.isOp() || !sender.hasPermission("kcutils.update")) {
            sender.sendMessage("§7 You must be a server operator to use this command!");
        }
        checker.check();
        return true;

    }
}