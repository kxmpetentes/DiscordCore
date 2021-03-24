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

    /**
     * Create a picture
     *
     * @param width           width of the picture
     * @param height          height of the picture
     * @param pictureName     Picture name to save
     * @param backgroundColor picture background color
     */
    public PictureModel(int width, int height, String pictureName, Color backgroundColor) {
        this.widht = width;
        this.height = height;
        this.pictureName = pictureName;
        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.graphics2D = bufferedImage.createGraphics();

        graphics2D.setColor(backgroundColor);
    }

    /**
     * Add the Picture a text
     *
     * @param textColor text color
     * @param message   text message
     * @param x         start position to print
     * @param y         end position to print
     * @return return the PictureModel
     */
    public PictureModel addText(Color textColor, String message, int x, int y) {
        graphics2D.setFont(this.font);
        graphics2D.setColor(textColor);
        graphics2D.drawString(message, x, y);
        return this;
    }

    /**
     * Draw a new Rect
     *
     * @param color Color to draw
     * @return return the PictureModel
     */
    public PictureModel drawRect(Color color) {
        graphics2D.setColor(color);
        graphics2D.drawRect(0, 0, widht, height);

        return this;
    }

    /**
     * Draw a new 3D Rect
     *
     * @param color  Color to draw
     * @param raised set the 3DRect raised
     * @return the PictureModel
     */
    public PictureModel draw3DRect(Color color, boolean raised) {
        graphics2D.setColor(color);
        graphics2D.draw3DRect(0, 0, widht, height, raised);

        return this;
    }

    /**
     * Draw a new circle
     *
     * @param color Color to draw
     * @param x     center
     * @param y     height
     * @return result
     */
    public PictureModel drawCircle(Color color, int x, int y) {
        graphics2D.setColor(color);
        graphics2D.fillOval(x, y, widht, height);

        return this;
    }

    /**
     * Fill a Rect
     *
     * @param color color to draw
     * @return result
     */
    public PictureModel fillRect(Color color) {
        graphics2D.setColor(color);
        graphics2D.fillRect(0, 0, widht, height);

        return this;
    }

    /**
     * Set the font
     *
     * @param font font to set
     * @return result
     */
    public PictureModel setFont(Font font) {
        this.font = font;
        this.graphics2D.setFont(font);

        return this;
    }

    /**
     * Set the file to save
     *
     * @param file file to save
     * @return the result
     */
    public PictureModel setFile(File file) {
        this.file = file;

        return this;
    }

    /**
     * Save the picture as png file
     *
     * @return the result
     * @throws IOException if the file cannot be saved
     */
    public PictureModel saveToPngFile() throws IOException {
        graphics2D.dispose();

        file = new File(pictureName);
        ImageIO.write(bufferedImage, "png", file);
        return this;
    }

    /**
     * Save the picture as jpg file
     *
     * @return the result
     * @throws IOException if the file cannot be saved
     */
    public PictureModel saveToJpgFile() throws IOException {
        graphics2D.dispose();

        file = new File(pictureName);
        ImageIO.write(bufferedImage, "jpg", file);
        return this;
    }

    /**
     * @return the height of the picture
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the width of the picture
     */
    public int getWidht() {
        return widht;
    }

    /**
     * @return the file of the picture
     */
    public File getFile() {
        return file;
    }
}
