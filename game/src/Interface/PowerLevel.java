package Interface;

import java.awt.Color;
import java.awt.Graphics;

public class PowerLevel {
    private int powerLevelLimit = 50;
    private int currentPowerLevel = this.powerLevelLimit;
    private GameMap gameMap;

    public PowerLevel(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setCurrentPowerLevel(int powerLevel) {
        this.currentPowerLevel += powerLevel;
    }

    public int getCurrentPowerLevel() {
        return currentPowerLevel;
    }
    
    public void resetScore() {
        currentPowerLevel = 0;
    }

    public void renderScore(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(graphics.getFont().deriveFont(19f));
        graphics.drawString("Score: " + currentPowerLevel, 20, 30);
    }
}
