package nl.markschuurmans.core.chat;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatFormatter {
    private static final Pattern TAG_PATTERN = Pattern.compile("<(\\w+)(:[^>]+)?>((?:(?!</\\1>).)*)</\\1>", Pattern.DOTALL);

    public static BaseComponent[] format(Player player, String input) {
        return format(input, player);
    }

    public static BaseComponent[] format(String input) {
        return format(input, null);
    }

    private static BaseComponent[] format(String input, @Nullable Player player) {
        TextComponent root = new TextComponent();
        int lastIndex = 0;
        Matcher matcher = TAG_PATTERN.matcher(input);

        while (matcher.find()) {
            if (matcher.start() > lastIndex) {
                root.addExtra(new TextComponent(input.substring(lastIndex, matcher.start())));
            }

            String tag = matcher.group(1).toLowerCase();
            String args = matcher.group(2) != null ? matcher.group(2).substring(1) : null;
            String content = matcher.group(3);
            lastIndex = matcher.end();

            try {
                ChatTagType tagType = ChatTagType.valueOf(tag.toUpperCase());
                if (player != null && !tagType.canUseTag(player)) {
                    root.addExtra(new TextComponent(matcher.group(0)));
                    continue;
                }

                tagType.createProcessor().ifPresent(processor -> processor.process(args, content, root));
            } catch (IllegalArgumentException e) {
                root.addExtra(new TextComponent(matcher.group(0)));
            }
        }

        if (lastIndex < input.length()) {
            root.addExtra(new TextComponent(input.substring(lastIndex)));
        }

        return new BaseComponent[]{ root };
    }
}
