package nl.markschuurmans.core.menu;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public class MenuItem {
    private final ItemStack item;
    private final BiConsumer<MenuClickEvent, ClickType> action;

    public MenuItem(ItemStack item, BiConsumer<MenuClickEvent, ClickType> action) {
        this.item = item;
        this.action = action;
    }

    public ItemStack getItem() {
        return item;
    }

    public void handleClick(MenuClickEvent event, ClickType clickType) {
        if (action == null) return;
        action.accept(event, clickType);
    }
}