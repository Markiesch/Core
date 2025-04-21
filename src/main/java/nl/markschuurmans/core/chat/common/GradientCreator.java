package nl.markschuurmans.core.chat.common;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GradientCreator {

    public static List<ChatColor> createGradient(String startHex, String endHex, int steps) {
        Color start = Color.decode(startHex);
        Color end = Color.decode(endHex);
        List<ChatColor> gradient = new ArrayList<>();

        for (int i = 0; i < steps; i++) {
            float ratio = i / (float) (steps - 1);
            int red = (int) (start.getRed() + (end.getRed() - start.getRed()) * ratio);
            int green = (int) (start.getGreen() + (end.getGreen() - start.getGreen()) * ratio);
            int blue = (int) (start.getBlue() + (end.getBlue() - start.getBlue()) * ratio);

            gradient.add(ChatColor.of(new Color(red, green, blue)));
        }

        return gradient;
    }
}