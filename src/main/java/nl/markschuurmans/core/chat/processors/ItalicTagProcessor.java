package nl.markschuurmans.core.chat.processors;

import net.md_5.bungee.api.chat.TextComponent;
import nl.markschuurmans.core.chat.ChatTagProcessor;
import org.bukkit.entity.Player;

public class ItalicTagProcessor implements ChatTagProcessor {
    @Override
    public void process(String args, String content, TextComponent root) {
        TextComponent comp = new TextComponent(content);
        comp.setItalic(true);
        root.addExtra(comp);
    }
}
