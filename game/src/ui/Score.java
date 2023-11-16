package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.time.Duration;
import java.time.Instant;

import States.GameStates;
import States.State;


public class Score {
    private int scoreLimit = 100;
    private int startingScore = 10;
    private int currentScore = startingScore;
    private GameMap gameMap;
    private State state;
    private int defteadEnemiesCounter = 0;

    Instant startTime = Instant.now();
    Instant endTime = Instant.now();
    Instant stopTime = Instant.now();

    public Score(GameMap gameMap, State state) {
        this.gameMap = gameMap;
        this.state = state;
    }

    public int getDefteadEnemiesCounter() {
        return defteadEnemiesCounter;
    }

    public void incrementScore(int powerLevel) {
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
            endTime = Instant.now();
        }

        if (state.getCurrentState() == GameStates.GAME_OVER) {
            endTime = Instant.now();
        }
    }

    public void incrementDefteadEnemiesCounter() {
        this.defteadEnemiesCounter++;
    }

    public void startTimer() {
        startTime = Instant.now();
    }

    public void stopTimer() {
        stopTime = Instant.now();
    }

    public void resumeTimer() {
        Duration duration = Duration.between(stopTime, Instant.now());
        startTime = startTime.plus(duration);
    }

    public void renderScore(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(graphics.getFont().deriveFont(19f));
        graphics.drawString("Score: " + currentScore, 20, 30);
    }

    public String getFormattedTime() {
        Duration duration = Duration.between(startTime, endTime);
        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
