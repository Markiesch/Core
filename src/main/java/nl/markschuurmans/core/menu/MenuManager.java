package nl.markschuurmans.core.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuManager implements Listener {
    public static void init(org.bukkit.plugin.Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new MenuManager(), plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (!(event.getInventory().getHolder() instanceof Menu menu)) return;

        event.setCancelled(true);
        menu.handleClick(player, event);
    }
}