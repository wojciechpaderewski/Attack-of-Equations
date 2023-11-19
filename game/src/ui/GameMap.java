package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class GameMap {
    private int width, height;
    private BufferedImage map;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = importImg();
    }

    private BufferedImage importImg() {
        InputStream is = getClass().getResourceAsStream("../assets/map.jpg");
        try {
            BufferedImage img = javax.imageio.ImageIO.read(is);
            return img;
        } catch (Exception e) {
            System.out.println("Error importing map.png");
            return null;
        }
    }
        

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void renderMap(Graphics graphics) {
        graphics.drawImage(map, 0, 0, null);
    }
}
