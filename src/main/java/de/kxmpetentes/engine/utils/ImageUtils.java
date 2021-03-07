package de.kxmpetentes.engine.utils;

import java.awt.*;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.03.2021 um 00:37
 */

public class ImageUtils {

    private ImageUtils() {
    }

    public static void addCenterText(String text, Font font, int x, int y, Graphics g, Color color) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, Math.max(0, x - ((int) g.getFontMetrics().getStringBounds(text, g).getWidth() / 2)), y);
    }

    public static void addRightText(String text, Font font, int x, int y, Graphics g, Color color) {
        g.setFont(font);
        g.setColor(color);
        int realX = Math.max(0, x - ((int) g.getFontMetrics().getStringBounds(text, g).getWidth()));
        g.drawString(text, realX, y);
    }

    public static void addShadow(String text, Font font, int x, int y, Graphics g, Color color) {
        addText(text, font, x + 1, y + 1, g, color);
        addText(text, font, x + 1, y - 1, g, color);
        addText(text, font, x - 1, y + 1, g, color);
        addText(text, font, x - 1, y - 1, g, color);
    }

    public static void addCenterShadow(String text, Font font, int x, int y, Graphics g, Color color) {
        addCenterText(text, font, x + 1, y + 1, g, color);
        addCenterText(text, font, x + 1, y - 1, g, color);
        addCenterText(text, font, x - 1, y + 1, g, color);
        addCenterText(text, font, x - 1, y - 1, g, color);
    }

    public static void addText(String text, Font font, int x, int y, Graphics g, Color color) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }

    /**
     * @param percentage 0.0 - 1.0
     * @return color from green to red
     */
    public static Color getThreatLevel(double percentage) {
        return Color.getHSBColor((float) (1f - percentage) * .35f, 1, 1);
    }
}