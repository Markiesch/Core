package nl.markschuurmans.core.chat;

import net.md_5.bungee.api.chat.TextComponent;

public interface ChatTagProcessor {
    void process(String args, String content, TextComponent root);
}
