package nl.markschuurmans.core.chat;

import nl.markschuurmans.core.chat.processors.*;
import org.bukkit.entity.Player;

import java.util.Optional;

public enum ChatTagType {
    HEX("chat.format.hex", HexTagProcessor.class),
    GRADIENT("chat.format.gradient", GradientTagProcessor.class),
    BOLD("chat.format.bold", BoldTagProcessor.class),
    ITALIC("chat.format.italic", ItalicTagProcessor.class),
    CLICK("chat.format.click", ClickTagProcessor.class);

    private final String permission;
    private final Class<? extends ChatTagProcessor> processorClass;

    ChatTagType(String permission, Class<? extends ChatTagProcessor> processorClass) {
        this.permission = permission;
        this.processorClass = processorClass;
    }

    public boolean canUseTag(Player player) {
        return permission == null || player.hasPermission(permission);
    }

    public Optional<ChatTagProcessor> createProcessor() {
        try {
            return Optional.of(processorClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
