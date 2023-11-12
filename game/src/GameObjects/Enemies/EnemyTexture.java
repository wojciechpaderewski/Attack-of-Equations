package GameObjects.Enemies;

import java.awt.Color;
import java.awt.Rectangle;

import Equations.Equation;
import GameObjects.Texture;

public class EnemyTexture implements Texture {
    private Rectangle rect;
    private int width, height;
    private Color color;
    private Equation equation;
    private int longestEquationLength = 5;

    EnemyTexture(Rectangle rect, Equation equation) {
        this.rect = rect;
        this.color = Color.GREEN;
        this.width = (int) rect.getWidth();
        this.height = (int) rect.getHeight();
        this.equation = equation;
    }

    public void tick() {

    }

    public void render(java.awt.Graphics graphics) {
        graphics.setColor(color);
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        graphics.fillRect(x, y, width, height);

        graphics.setColor(Color.black);
        String equationString = equation.getEquationToRender();
        if (equationString.length() > this.longestEquationLength) {
            equationString = wrapToLongEquation(equationString);
        }

        graphics.setFont(graphics.getFont().deriveFont(15f));
        System.out.println(equationString);
        int stringWidth = graphics.getFontMetrics().stringWidth(equationString);
        int stringHeight = graphics.getFontMetrics().getHeight();
        graphics.drawString(equationString, x + width / 2 - stringWidth / 2, y + height / 2 + stringHeight / 2);
        
    }

    private  String wrapToLongEquation(String equation) {
        if (equation.length() > this.longestEquationLength) {
            return equation.substring(0, this.longestEquationLength) + "\n" + equation.substring(this.longestEquationLength);
        }
        return equation;
    }
}
