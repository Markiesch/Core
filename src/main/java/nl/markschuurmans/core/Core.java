package nl.markschuurmans.core;

import nl.markschuurmans.core.commands.framework.CommandManager;
import nl.markschuurmans.core.menu.MenuManager;
import nl.markschuurmans.core.modules.example.ExampleModule;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        MenuManager.init(this);
        commandManager = new CommandManager(this);
        new ExampleModule(this);

        getLogger().info("Core plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Core plugin disabled!");
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
