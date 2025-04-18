package nl.markschuurmans.core.commands.framework;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.CommandSender;
import java.lang.reflect.Method;
import java.util.*;

import nl.markschuurmans.core.commands.annotations.*;

public abstract class BaseCommand implements CommandExecutor, TabCompleter {
    private final Map<String, Method> subCommands = new HashMap<>();

    public BaseCommand() {
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(SubCommand.class)) {
                SubCommand sub = method.getAnnotation(SubCommand.class);
                subCommands.put(sub.name().toLowerCase(), method);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
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
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
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