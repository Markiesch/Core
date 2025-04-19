package nl.markschuurmans.core.modules.example.event;

import net.md_5.bungee.api.chat.BaseComponent;
import nl.markschuurmans.core.chat.ChatFormatter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        BaseComponent[] components = ChatFormatter.format(event.getPlayer(), event.getMessage());
        Bukkit.getConsoleSender().spigot().sendMessage(components);
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.spigot().sendMessage(components);
        }
    }
}
