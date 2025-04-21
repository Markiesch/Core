package nl.markschuurmans.core.chat.processors;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import nl.markschuurmans.core.chat.ChatTagProcessor;
import nl.markschuurmans.core.chat.common.GradientCreator;

import java.util.List;

public class GradientTagProcessor implements ChatTagProcessor {
    @Override
    public void process(String args, String content, TextComponent root) {
        String[] colors = args.split(":");
//        if (colors.length != 2) {
//            player.sendMessage("§cInvalid gradient format. Use: <gradient:#start:#end>");
//            root.addExtra(new TextComponent(content));
//            return;
//        }

        List<ChatColor> gradient = GradientCreator.createGradient(colors[0], colors[1], content.length());
        for (int i = 0; i < content.length(); i++) {
            TextComponent c = new TextComponent(String.valueOf(content.charAt(i)));
            c.setColor(gradient.get(i));
            root.addExtra(c);
        }
    }
}
