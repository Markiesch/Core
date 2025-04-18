package nl.markschuurmans.core.commands.framework;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public record CommandContext(CommandSender sender, String[] args) {

    public Player getPlayer() {
        return sender instanceof Player ? (Player) sender : null;
    }

    public String getArg(int index) {
        return index < args.length ? args[index] : "";
    }
}