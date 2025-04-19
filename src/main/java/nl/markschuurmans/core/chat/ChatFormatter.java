package nl.markschuurmans.core.chat;

import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatFormatter {

    private static final Pattern TAG_PATTERN = Pattern.compile("<(\\w+)(:[^>]+)?>((?:(?!<\\/\\1>).)*)<\\/\\1>", Pattern.DOTALL);

    public static BaseComponent[] format(Player player, String input) {
        TextComponent root = new TextComponent();

        int lastIndex = 0;
        Matcher matcher = TAG_PATTERN.matcher(input);

        while (matcher.find()) {
            // Append plain text before the tag
            if (matcher.start() > lastIndex) {
                root.addExtra(new TextComponent(input.substring(lastIndex, matcher.start())));
            }

            String tag = matcher.group(1).toLowerCase();
            String args = matcher.group(2) != null ? matcher.group(2).substring(1) : null;
            String content = matcher.group(3);
            lastIndex = matcher.end();

            if (!ChatPermission.canUseTag(player, tag)) {
                root.addExtra(new TextComponent(matcher.group(0)));
                continue;
            }

            switch (tag) {
                case "red" -> {
                    TextComponent comp = new TextComponent(content);
                    comp.setColor(ChatColor.RED);
                    root.addExtra(comp);
                }

                case "hex" -> {
                    TextComponent comp = new TextComponent(content);

                    if (args == null) {
                        root.addExtra(comp);
                        break;
                    }

                    if (!args.startsWith("#")) {
                        args = "#" + args;
                    }

                    if (!args.matches("#[0-9a-fA-F]{6}")) {
                        player.sendMessage("§cInvalid hex color code: " + args);
                        root.addExtra(comp);
                        break;
                    }

                    comp.setColor(ChatColor.of(args));
                    root.addExtra(comp);
                }

                case "gradient" -> {
                    String[] colors = args.split(":");
                    List<ChatColor> gradient = GradientUtil.createGradient(colors[0], colors[1], content.length());

                    for (int i = 0; i < content.length(); i++) {
                        TextComponent c = new TextComponent(String.valueOf(content.charAt(i)));
                        c.setColor(gradient.get(i));
                        root.addExtra(c);
                    }
                }

                case "bold" -> {
                    TextComponent comp = new TextComponent(content);
                    comp.setBold(true);
                    root.addExtra(comp);
                }

                case "italic" -> {
                    TextComponent comp = new TextComponent(content);
                    comp.setItalic(true);
                    root.addExtra(comp);
                }

                case "click" -> {
                    TextComponent comp = new TextComponent(content);
                    ClickEvent.Action action = args.startsWith("http") ? ClickEvent.Action.OPEN_URL : ClickEvent.Action.RUN_COMMAND;
                    comp.setClickEvent(new ClickEvent(action, args));
                    root.addExtra(comp);
                }

                default -> root.addExtra(new TextComponent(content)); // fallback
            }
        }

        // Append trailing text
        if (lastIndex < input.length()) {
            root.addExtra(new TextComponent(input.substring(lastIndex)));
        }

        return new BaseComponent[]{ root };
    }
}