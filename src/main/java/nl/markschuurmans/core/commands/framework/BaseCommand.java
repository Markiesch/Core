package nl.markschuurmans.core.commands.framework;

import nl.markschuurmans.core.commands.annotations.CommandInfo;
import nl.markschuurmans.core.commands.annotations.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.*;

public abstract class BaseCommand implements CommandExecutor, TabCompleter {
    private final Map<String, Method> subCommands = new HashMap<>();
    private final CommandInfo commandInfo;

    public BaseCommand() {
        this.commandInfo = this.getClass().getAnnotation(CommandInfo.class);

        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(SubCommand.class)) {
                SubCommand sub = method.getAnnotation(SubCommand.class);
                subCommands.put(sub.name().toLowerCase(), method);
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!commandInfo.allowedSender().matchSender(sender)) {
            if (sender instanceof Player) {
                sender.sendMessage("§cThis command can only be used by the console.");
            } else {
                sender.sendMessage("§cThis command can only be used by players.");
            }
            return true;
        }

        if (args.length > 0) {
            String sub = args[0].toLowerCase();
            Method method = subCommands.get(sub);
            if (method != null) {
                SubCommand subMeta = method.getAnnotation(SubCommand.class);
                if (!subMeta.permission().isEmpty() && !sender.hasPermission(subMeta.permission())) {
                    sender.sendMessage("§cNo permission.");
                    return true;
                }

                try {
                    method.invoke(this, new CommandContext(sender, Arrays.copyOfRange(args, 1, args.length)));
                } catch (Exception e) {
                    sender.sendMessage("§cError executing command.");
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            String current = args[0].toLowerCase();
            for (String sub : subCommands.keySet()) {
                if (sub.startsWith(current)) {
                    completions.add(sub);
                }
            }
        }

        // Optional: handle deeper tab completion in each subcommand
        return completions;
    }
}