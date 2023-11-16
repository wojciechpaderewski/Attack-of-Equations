package ui;

import java.awt.Color;
import java.awt.Graphics;

import States.GameStates;
import States.State;

public class Score {
    private int scoreLimit = 10;
    private int currentScore = this.scoreLimit;
    private GameMap gameMap;
    private State state;
    private int defteadEnemiesCounter = 0;

    public Score(GameMap gameMap, State state) {
        this.gameMap = gameMap;
        this.state = state;
    }

    public void get(int powerLevel) {
        this.currentScore += powerLevel;
    }

    public int getCurrentScore() {
        return currentScore;
    }
    
    public void resetScore() {
        currentScore = 0;
    }

    public boolean isGameWon() {
        return currentScore >= scoreLimit;
    }

    public void tick () {
        if (isGameWon()) {
            state.setCurrentState(GameStates.GAME_WON);
        }
    }

    public void renderScore(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(graphics.getFont().deriveFont(19f));
        graphics.drawString("Score: " + currentScore, 20, 30);
    }
}
