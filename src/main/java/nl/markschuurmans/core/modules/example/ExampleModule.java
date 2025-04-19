package nl.markschuurmans.core.modules.example;

import nl.markschuurmans.core.Core;
import nl.markschuurmans.core.modules.example.commands.ExampleCommand;
import nl.markschuurmans.core.commands.framework.CommandManager;
import nl.markschuurmans.core.modules.example.event.ChatEvent;

public class ExampleModule {
    public ExampleModule(Core core) {
        CommandManager commandManager = core.getCommandManager();
        commandManager.register(new ExampleCommand());

        core.getServer().getPluginManager().registerEvents(new ChatEvent(), core);
    }
}
