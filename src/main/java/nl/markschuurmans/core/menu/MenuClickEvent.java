package nl.markschuurmans.core.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuClickEvent {
    private final Player player;
    private final InventoryClickEvent event;

    public MenuClickEvent(Player player, InventoryClickEvent event) {
        this.player = player;
        this.event = event;
    }

    public Player getPlayer() {
        return player;
    }

    public InventoryClickEvent getRawEvent() {
        return event;
    }

    public void cancel() {
        event.setCancelled(true);
    }
}