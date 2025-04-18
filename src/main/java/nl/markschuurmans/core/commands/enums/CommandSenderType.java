package nl.markschuurmans.core.commands.enums;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public enum CommandSenderType {
    ANY,
    CONSOLE_ONLY,
    PLAYER_ONLY;

   public boolean matchSender(CommandSender sender) {
        if (this == ANY) {
            return true;
        } else if (this == CONSOLE_ONLY) {
            return sender instanceof ConsoleCommandSender;
        } else if (this == PLAYER_ONLY) {
            return sender instanceof Player;
        }
        return false;
    }
}
