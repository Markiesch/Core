package nl.markschuurmans.core.chat.processors;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import nl.markschuurmans.core.chat.ChatTagProcessor;
import org.bukkit.entity.Player;

public class ClickTagProcessor implements ChatTagProcessor {
    @Override
    public void process(String args, String content, TextComponent root) {
//        if (args == null || args.isEmpty()) {
//            player.sendMessage("§cInvalid click action. Provide a valid URL or command.");
//            root.addExtra(new TextComponent(content));
//            return;
//        }

        TextComponent comp = new TextComponent(content);
        comp.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, args));
        root.addExtra(comp);
    }
}
