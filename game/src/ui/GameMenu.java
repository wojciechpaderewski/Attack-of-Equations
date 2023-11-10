package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.Function;

import Handlers.MouseHandler;

public class GameMenu {
    static int width = 300;
    static int height = 600;
    static Color backgroundColor = Color.gray;

    MouseHandler mouseHandler;
    private Button stopButton;
    private Button startButton;
    private Button resumeButton;

    public GameMenu(GameMap gameMap, MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
        initStopButton();       
        initStartButton();
        initResumeButton(); 
    }

    private void initStopButton() {
        stopButton = new Button(mouseHandler);
        stopButton.text = "Stop";
        stopButton.x = width / 2 - stopButton.width / 2;
        stopButton.y = 100;

        stopButton.onClick = (Void) -> {
            onStopGame.apply(null);
            return null;
        };
    }

    private void initStartButton() {
        startButton = new Button(mouseHandler);
        startButton.text = "Start";
        startButton.x = width / 2 - startButton.width / 2;
        startButton.y = 300;

        startButton.onClick = (Void) -> {
            onStartGame.apply(null);
            return null;
        };
    }

    private void initResumeButton() {
        resumeButton = new Button(mouseHandler);
        resumeButton.text = "Resume";
        resumeButton.x = width / 2 - resumeButton.width / 2;
        resumeButton.y = 500;

        resumeButton.onClick = (Void) -> {
            onResumeGame.apply(null);
            return null;
        };
    }

    public Function<Void, Void> onStopGame;
    public Function<Void, Void> onStartGame;
    public Function<Void, Void> onResumeGame;

    private void renderBackground(Graphics graphics) {
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, width, height);
    }

    public void render(Graphics graphics) {
        renderBackground(graphics);
        stopButton.render(graphics);
        startButton.render(graphics);
        resumeButton.render(graphics);
    }
}
