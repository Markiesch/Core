package nl.markschuurmans.core.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Menu implements InventoryHolder {
    private final String title;
    private final int size;
    private final Map<Integer, MenuItem> items = new HashMap<>();
    private Inventory inventory;

    public Menu(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public void setItem(int slot, MenuItem item) {
        items.put(slot, item);
        if (inventory != null) {
            inventory.setItem(slot, item.getItem());
        }
    }

    public void open(Player player) {
        this.inventory = Bukkit.createInventory(this, size, title);

        for (Map.Entry<Integer, MenuItem> entry : items.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItem());
        }

        player.openInventory(inventory);
    }

    public void handleClick(Player player, InventoryClickEvent event) {
        int slot = event.getRawSlot();
        if (slot < 0 || slot >= size) return;

        MenuItem item = items.get(slot);
        if (item != null) {
            item.handleClick(new MenuClickEvent(player, event), event.getClick());
        }
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
