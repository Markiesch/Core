package nl.markschuurmans.core.chat.processors;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import nl.markschuurmans.core.chat.ChatTagProcessor;
import org.bukkit.entity.Player;

public class HexTagProcessor implements ChatTagProcessor {
    @Override
    public void process(String args, String content, TextComponent root) {
        TextComponent comp = new TextComponent(content);

//        if (args == null || !args.matches("#[0-9a-fA-F]{6}")) {
//            player.sendMessage("§cInvalid hex color code: " + args);
//            root.addExtra(comp);
//            return;
//        }

        comp.setColor(ChatColor.of(args));
        root.addExtra(comp);
    }
}
