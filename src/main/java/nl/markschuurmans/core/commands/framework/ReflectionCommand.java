package nl.markschuurmans.core.commands.framework;

import nl.markschuurmans.core.commands.annotations.CommandInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;

public class ReflectionCommand extends Command {

    private final BaseCommand executor;

    public ReflectionCommand(CommandInfo meta, BaseCommand executor) {
        super(meta.name(), "", "", Arrays.asList(meta.aliases()));
        this.executor = executor;
        this.setPermission(meta.permission().isEmpty() ? null : meta.permission());
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return executor.onCommand(sender, this, commandLabel, args);
    }

    @Override
    public java.util.List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        if (executor instanceof TabCompleter completer) {
            return completer.onTabComplete(sender, this, alias, args);
        }
        return super.tabComplete(sender, alias, args);
    }
}