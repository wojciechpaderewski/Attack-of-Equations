package Interface;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerLives {
    private int maxLives = 3;
    private int currentLives = maxLives;

    private int heartWidth = 20;
    private int heartHeight = 20;
    private GameMap gameMap;

    public PlayerLives(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public int getCurrentLives() { return currentLives; }
    public void setCurrentLives(int lives) { this.currentLives = lives; }

    public void renderLives(Graphics graphics) {
        int x = gameMap.getWidth() - 100;
        int y = 10;
        int padding = 10;

        for (int i = 0; i < currentLives; i++) {
            drawHeart(graphics, x, y);
            x += heartWidth + padding;
        }
    }

    private void drawHeart(Graphics graphics, int x, int y) {
        graphics.setColor(Color.red);
        graphics.fillOval(x, y, heartWidth / 2, heartHeight / 2);
        graphics.fillOval(x + heartWidth / 2, y, heartWidth / 2, heartHeight / 2);
        graphics.fillRect(x + heartWidth / 4, y + heartHeight / 4, heartWidth / 2, heartHeight / 2);

        // draw outline
        graphics.setColor(Color.black);
        graphics.drawOval(x, y, heartWidth / 2, heartHeight / 2);
        graphics.drawOval(x + heartWidth / 2, y, heartWidth / 2, heartHeight / 2);
        graphics.drawRect(x + heartWidth / 4, y + heartHeight / 4, heartWidth / 2, heartHeight / 2);
    }
}
