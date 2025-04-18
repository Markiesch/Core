package nl.markschuurmans.core;

import nl.markschuurmans.core.commands.commands.ExampleCommand;
import nl.markschuurmans.core.commands.framework.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        commandManager = new CommandManager(this);

        commandManager.register(new ExampleCommand());
        getLogger().info("Core plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Core plugin disabled!");
    }
}
