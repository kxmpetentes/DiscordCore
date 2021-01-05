package de.kxmpetentes.engine.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 22:16
 */

public class ColorModel {

    private final Color color;
    private final ArrayList<Color> colorList = new ArrayList<>();

    /**
     * @param colors Enter the colors from which a random one should be chosen
     */
    public ColorModel(Color... colors) {

        for (Color c : colors) {
            this.colorList.add(c);
            this.colorList.add(c.brighter());
            this.colorList.add(c.darker());
        }

        int randomInt = ThreadLocalRandom.current().nextInt(colorList.size());
        this.color = this.colorList.get(randomInt);
    }

    /**
     * @return a random color of the colors specified in the constructor
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return Gives you the list of colors to choose
     */
    public ArrayList<Color> getColors() {
        return colorList;
    }

}
