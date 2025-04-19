package nl.markschuurmans.core.chat;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ChatPermission {
    private static final Map<String, String> tagPermissionMap = new HashMap<>();

    static {
        tagPermissionMap.put("red", "chat.format.color");
        tagPermissionMap.put("hex", "chat.format.hex");
        tagPermissionMap.put("gradient", "chat.format.gradient");
        tagPermissionMap.put("bold", "chat.format.bold");
        tagPermissionMap.put("italic", "chat.format.italic");
        tagPermissionMap.put("click", "chat.format.click");
    }

    public static boolean canUseTag(Player player, String tag) {
        String perm = tagPermissionMap.get(tag.toLowerCase());
        return perm == null || player.hasPermission(perm);
    }
}
