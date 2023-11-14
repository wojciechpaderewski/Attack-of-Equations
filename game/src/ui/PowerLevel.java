package ui;

import java.awt.Color;
import java.awt.Graphics;

import States.GameStates;
import States.State;

public class PowerLevel {
    private int powerLevelLimit = 10;
    private int currentPowerLevel = this.powerLevelLimit;
    private GameMap gameMap;
    private State state;
    private int defteadEnemiesCounter = 0;

    public PowerLevel(GameMap gameMap, State state) {
        this.gameMap = gameMap;
        this.state = state;
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

    public boolean isGameWon() {
        return currentPowerLevel >= powerLevelLimit;
    }

    public void tick () {
        if (isGameWon()) {
            state.setCurrentState(GameStates.GAME_WON);
        }
    }

    public void renderScore(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(graphics.getFont().deriveFont(19f));
        graphics.drawString("Score: " + currentPowerLevel, 20, 30);
    }
}
