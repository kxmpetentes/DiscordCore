package de.kxmpetentes.engine.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 22:26
 */

public class PictureModel {

    private final int widht, height;
    private final String pictureName;
    private final BufferedImage bufferedImage;
    private final Graphics2D graphics2D;
    private Font font = Font.getFont(Font.MONOSPACED);
    private File file;

    public PictureModel(int width, int height, String pictureName, Color backgroundColor) {
        this.widht = width;
        this.height = height;
        this.pictureName = pictureName;
        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.graphics2D = bufferedImage.createGraphics();

        graphics2D.setColor(backgroundColor);
    }

    public PictureModel addText(Color textColor, String message, int x, int y) {
        graphics2D.setFont(this.font);
        graphics2D.setColor(textColor);
        graphics2D.drawString(message, x, y);
        return this;
    }

    public PictureModel drawRect(Color color) {
        graphics2D.setColor(color);
        graphics2D.drawRect(0, 0, widht, height);

        return this;
    }

    public PictureModel draw3DRect(Color color, boolean raised) {
        graphics2D.setColor(color);
        graphics2D.draw3DRect(0, 0, widht, height, raised);

        return this;
    }

    public PictureModel drawCircle(Color color, int x, int y) {
        graphics2D.setColor(color);
        graphics2D.fillOval(x, y, widht, height);

        return this;
    }

    public PictureModel fillRect(Color color) {
        graphics2D.setColor(color);
        graphics2D.fillRect(0, 0, widht, height);

        return this;
    }

    public PictureModel setFont(Font font) {
        this.font = font;
        this.graphics2D.setFont(font);

        return this;
    }

    public PictureModel setFile(File file) {
        this.file = file;

        return this;
    }

    public PictureModel saveToPngFile() throws IOException {
        graphics2D.dispose();

        file = new File(pictureName);
        ImageIO.write(bufferedImage, "png", file);
        return this;
    }

    public PictureModel saveToJpgFile() throws IOException {
        graphics2D.dispose();

        file = new File(pictureName);
        ImageIO.write(bufferedImage, "jpg", file);
        return this;
    }


    public int getHeight() {
        return height;
    }

    public int getWidht() {
        return widht;
    }

    public File getFile() {
        return file;
    }
}
