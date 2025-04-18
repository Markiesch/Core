package nl.markschuurmans.core.commands.framework;

import nl.markschuurmans.core.commands.annotations.CommandInfo;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class CommandManager {
    private final JavaPlugin plugin;
    private final CommandMap commandMap;

    public CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.commandMap = getCommandMap();
    }

    private CommandMap getCommandMap() {
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            return (CommandMap) field.get(Bukkit.getServer());
        } catch (Exception e) {
            throw new RuntimeException("Unable to get CommandMap", e);
        }
    }

    public void register(BaseCommand command) {
        Class<?> clazz = command.getClass();

        if (!clazz.isAnnotationPresent(CommandInfo.class)) {
            plugin.getLogger().warning("Attempted to register command without @CommandInfo: " + clazz.getName());
            return;
        }

        CommandInfo meta = clazz.getAnnotation(CommandInfo.class);
        ReflectionCommand cmd = new ReflectionCommand(meta, command);

        commandMap.register(plugin.getName(), cmd);
        plugin.getLogger().info("Registered command: /" + meta.name());
    }
}