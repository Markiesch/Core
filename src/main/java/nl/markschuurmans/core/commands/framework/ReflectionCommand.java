package nl.markschuurmans.core.commands.framework;

import nl.markschuurmans.core.commands.annotations.CommandInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ReflectionCommand extends Command {

    private final BaseCommand executor;

    public ReflectionCommand(CommandInfo meta, BaseCommand executor) {
        super(meta.name(), "", "", Arrays.asList(meta.aliases()));
        this.executor = executor;
        this.setPermission(meta.permission().isEmpty() ? null : meta.permission());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        return executor.onCommand(sender, this, commandLabel, args);
    }

    @NotNull
    @Override
    public java.util.List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        if (executor instanceof TabCompleter completer) {
            List<String> result = completer.onTabComplete(sender, this, alias, args);
            if (result != null) {
                return result;
            }
        }
        return super.tabComplete(sender, alias, args);
    }
}