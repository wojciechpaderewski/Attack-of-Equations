package GameObjects.Enemies;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import Equations.Equation;
import GameObjects.Texture;

public class EnemyTexture implements Texture {
    private Rectangle rect;
    private int width, height;
    private Equation equation;
    private BufferedImage enemyImg;

    EnemyTexture(Rectangle rect, Equation equation) {
        this.rect = rect;
        this.width = (int) rect.getWidth();
        this.height = (int) rect.getHeight();
        this.equation = equation;
        this.enemyImg = importImg();
    }

    private BufferedImage importImg() {
        InputStream is = getClass().getResourceAsStream("../../assets/enemy.png");
        try {
            BufferedImage img = javax.imageio.ImageIO.read(is);
            return img;
        } catch (Exception e) {
            System.out.println("Error importing map.png");
            return null;
        }
    }

    public void tick() {

    }

    public void render(java.awt.Graphics graphics) {
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        graphics.drawImage(enemyImg, x, y, null);

        graphics.setColor(Color.black);
        String equationString = equation.getEquationToRender();

        graphics.setFont(graphics.getFont().deriveFont(18f));
        int stringWidth = graphics.getFontMetrics().stringWidth(equationString);
        int stringHeight = graphics.getFontMetrics().getHeight();
        graphics.drawString(equationString, x + width / 2 - stringWidth / 2, y + height / 2 + stringHeight / 3);
    }
}
