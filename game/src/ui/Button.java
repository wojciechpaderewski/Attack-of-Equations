package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.function.Function;

import Handlers.MouseHandler;


public class Button {
    public String text = "Button";
    public int x = 0, y = 0, width = 200, height = 80;

    public Color buttonColor = Color.white;
    public Color textColor = Color.white;
    public Color borderColor = Color.black;
    public float fontSize = 19f;
    public BufferedImage img;

    public Function<Void, Void> onClick;

    public Button(MouseHandler mouseHandler) {
        mouseHandler.onClick((e) -> {
            int x = e.getX();
            int y = e.getY();

            if (isInBounds(x, y)) {
                onClick.apply(null);
            }
            return null;
        });

        setImg("../assets/button.png");
        textColor = Color.white;
    }

    public void setImg(String path) {
        InputStream is = getClass().getResourceAsStream(path);
        try {
            img = javax.imageio.ImageIO.read(is);
        } catch (Exception e) {
            System.out.println("Error importing " + path);
        }
    }

    private boolean isInBounds(int x, int y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

    public void render(Graphics graphics) {
        if (img != null) {
            graphics.drawImage(img, x, y, width, height, null);
        } else {
            graphics.setColor(buttonColor);
            graphics.fillRect(x, y, width, height);

            graphics.setColor(borderColor);
            graphics.drawRect(x, y, width, height);
            graphics.setColor(Color.black);
        }
        
        graphics.setColor(textColor);
        graphics.setFont(graphics.getFont().deriveFont(fontSize));
        graphics.drawString(text, x + width / 2 - graphics.getFontMetrics().stringWidth(text) / 2, y + height / 2 + graphics.getFontMetrics().getHeight() / 3);
    }

}