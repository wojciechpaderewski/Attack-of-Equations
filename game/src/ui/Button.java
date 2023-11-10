package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.Function;

import Handlers.MouseHandler;


public class Button {
    public String text = "Button";
    public int x = 0, y = 0, width = 100, height = 45;

    public Color buttonColor = Color.white;
    public Color textColor = Color.black;
    public Color borderColor = Color.black;

    Function<Void, Void> onClick;

    public Button(MouseHandler mouseHandler) {
        mouseHandler.onClick((e) -> {
            int x = e.getX();
            int y = e.getY();

            if (isInBounds(x, y)) {
                onClick.apply(null);
            }
            return null;
        });
    }

    private boolean isInBounds(int x, int y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

    public void render(Graphics graphics) {
        graphics.setColor(buttonColor);
        graphics.fillRect(x, y, width, height);

        graphics.setColor(borderColor);
        graphics.drawRect(x, y, width, height);

        graphics.setColor(textColor);
        graphics.drawString(text, x + width / 2 - graphics.getFontMetrics().stringWidth(text) / 2, y + height / 2 + graphics.getFontMetrics().getHeight() / 3);
    }

}