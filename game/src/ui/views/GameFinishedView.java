package ui.views;

import java.awt.Graphics;
import java.util.function.Function;

import Handlers.MouseHandler;
import States.GameStates;
import States.State;
import ui.Button;
import ui.GameMap;
import ui.Score;

public class GameFinishedView {
    private GameMap gameMap;
    private Score score;
    private String title;
    private MouseHandler mouseHandler;
    private int buttonWidth = 200;
    private State state;
    
    public Function<Void, Void> onQuitGame;
    public Function<Void, Void> onRestartGame;

    private Button quitButton;
    private Button restartButton;

    public GameFinishedView(GameMap gameMap, Score score, MouseHandler mouseHandler, State state, String title) {
        this.gameMap = gameMap;
        this.score = score;
        this.title = title;
        this.mouseHandler = mouseHandler;
        this.state = state;

        initQuitButton();
        initRestartButton();
    }

    public void initQuitButton() {
        quitButton = new Button(mouseHandler);
        quitButton.text = "Quit";
        quitButton.width = buttonWidth;
        quitButton.x = gameMap.getWidth() / 2 - quitButton.width / 2;
        quitButton.y = 500;
        quitButton.onClick = (Void) -> {
            onQuitGame.apply(null);
            return null;
        };
    }

    public void initRestartButton() {
        restartButton = new Button(mouseHandler);
        restartButton.text = "Restart game";
        restartButton.width = buttonWidth;
        restartButton.x = gameMap.getWidth() / 2 - restartButton.width / 2;
        restartButton.y = 400;

        restartButton.onClick = (Void) -> {
            onRestartGame.apply(null);
            return null;
        };
    }

    public void render(Graphics graphics) {
        graphics.setColor(java.awt.Color.BLACK);
        graphics.fillRect(0, 0, gameMap.getWidth(), gameMap.getHeight());
        graphics.setColor(java.awt.Color.WHITE);
        graphics.setFont(graphics.getFont().deriveFont(30f));
        graphics.drawString(this.title, gameMap.getWidth() / 2 - graphics.getFontMetrics().stringWidth(title) / 2, 200);
        graphics.setFont(graphics.getFont().deriveFont(20f));
        graphics.drawString("Your score: " + score.getCurrentScore(), gameMap.getWidth() / 2 - graphics.getFontMetrics().stringWidth("Your score: " + score.getCurrentScore()) / 2, 250);
        graphics.setFont(graphics.getFont().deriveFont(15f));
        graphics.drawString("Defeated enemies: " + score.getDefteadEnemiesCounter(), gameMap.getWidth() / 2 - graphics.getFontMetrics().stringWidth("Defeated enemies: " + score.getDefteadEnemiesCounter()) / 2, 300);
        graphics.setFont(graphics.getFont().deriveFont(15f));
        graphics.drawString("Time: " + score.getFormattedTime(), gameMap.getWidth() / 2 - graphics.getFontMetrics().stringWidth("Time: " + score.getFormattedTime()) / 2, 330);


        quitButton.render(graphics);
        restartButton.render(graphics);
    }
}
