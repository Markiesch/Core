package nl.markschuurmans.core.modules.example.menus;

import nl.markschuurmans.core.menu.Menu;
import nl.markschuurmans.core.menu.MenuItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExampleMenu {
    public static void open(Player player) {
        Menu menu = new Menu("§aExample Menu", 27);

        ItemStack confirmItem = new ItemStack(Material.LIME_WOOL);
        ItemStack cancelItem = new ItemStack(Material.RED_WOOL);

        menu.setItem(11, new MenuItem(confirmItem, (e, click) -> {
            e.getPlayer().sendMessage("§aConfirmed with: " + click.name());
        }));

        menu.setItem(15, new MenuItem(cancelItem, (e, click) -> {
            e.getPlayer().sendMessage("§cCancelled!");
            e.getPlayer().closeInventory();
        }));

        menu.open(player);
    }
}