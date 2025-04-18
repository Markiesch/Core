package nl.markschuurmans.core.commands.framework;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandContext {
    private final CommandSender sender;
    private final String[] args;

    public CommandContext(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
    }

    public CommandSender getSender() {
        return sender;
    }

    public Player getPlayer() {
        return sender instanceof Player ? (Player) sender : null;
    }

    public String[] getArgs() {
        return args;
    }

    public String getArg(int index) {
        return index < args.length ? args[index] : "";
    }
}