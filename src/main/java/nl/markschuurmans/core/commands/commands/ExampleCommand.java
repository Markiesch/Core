package nl.markschuurmans.core.commands.commands;

import nl.markschuurmans.core.commands.annotations.*;
import nl.markschuurmans.core.commands.enums.CommandSenderType;
import nl.markschuurmans.core.commands.framework.*;
import org.bukkit.Bukkit;

@CommandInfo(name = "example", aliases = {"ex"}, permission = "core.example", allowedSender = CommandSenderType.PLAYER_ONLY)
public class ExampleCommand extends BaseCommand {

    @SubCommand(name = "hello")
    public void hello(CommandContext ctx) {
        ctx.sender().sendMessage("§aHello there!");
    }

    @SubCommand(name = "broadcast", permission = "core.broadcast")
    public void broadcast(CommandContext ctx) {
        String message = String.join(" ", ctx.args());
        Bukkit.broadcastMessage("§6[Broadcast] §f" + message);
    }
}