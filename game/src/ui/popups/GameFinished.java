package ui.popups;

import java.awt.Graphics;
import java.util.function.Function;

import Handlers.MouseHandler;
import States.State;
import ui.Button;
import ui.Score;

public class GameFinished extends Popup {
    private Score score;
    private String title;
    private MouseHandler mouseHandler;
    private int buttonWidth = 200;
    
    public Function<Void, Void> onQuitGame;
    public Function<Void, Void> onRestartGame;

    private Button quitButton;
    private Button restartButton;

    public GameFinished(int windowWidth, int windowHeight, Score score, MouseHandler mouseHandler, State state, String title) {
        super(windowWidth, windowHeight);
        this.score = score;
        this.title = title;
        this.mouseHandler = mouseHandler;

        initQuitButton();
        initRestartButton();
    }

    public void initQuitButton() {
        quitButton = new Button(mouseHandler);
        quitButton.text = "Quit";
        quitButton.width = buttonWidth;
        quitButton.x = this.windowWidth / 2 - quitButton.width / 2;
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
        restartButton.x = this.windowWidth / 2 - restartButton.width / 2;
        restartButton.y = 400;

        restartButton.onClick = (Void) -> {
            onRestartGame.apply(null);
            return null;
        };
    }

    public void render(Graphics graphics) {
        super.render(graphics);
        graphics.setColor(java.awt.Color.WHITE);
        graphics.setFont(graphics.getFont().deriveFont(30f));
        graphics.drawString(this.title, this.windowWidth / 2 - graphics.getFontMetrics().stringWidth(title) / 2, 200);
        graphics.setFont(graphics.getFont().deriveFont(20f));
        graphics.drawString("Your score: " + score.getCurrentScore(), this.windowWidth / 2 - graphics.getFontMetrics().stringWidth("Your score: " + score.getCurrentScore()) / 2, 250);
        graphics.setFont(graphics.getFont().deriveFont(15f));
        graphics.drawString("Defeated enemies: " + score.getDefteadEnemiesCounter(), this.windowWidth / 2 - graphics.getFontMetrics().stringWidth("Defeated enemies: " + score.getDefteadEnemiesCounter()) / 2, 300);
        graphics.setFont(graphics.getFont().deriveFont(15f));
        graphics.drawString("Time: " + score.getFormattedTime(), this.windowWidth / 2 - graphics.getFontMetrics().stringWidth("Time: " + score.getFormattedTime()) / 2, 330);

        quitButton.render(graphics);
        restartButton.render(graphics);
    }
}
