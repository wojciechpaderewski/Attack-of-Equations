package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.function.Function;

import javax.swing.JFrame;

import Handlers.MouseHandler;
import States.GameState;

public class GameMenu {
    static int width = 800;
    static int height = 600;
    private JFrame frame;
    private Button stopButton;

    public GameMenu(JFrame frame, GameState state, MouseHandler mouseHandler) {
        this.frame = frame;
        stopButton = new Button(mouseHandler);
        stopButton.text = "Stop";
        stopButton.x = width / 2 - stopButton.width / 2;
        stopButton.y = height / 2 - stopButton.height / 2;

        stopButton.onClick = (Void) -> {
            onStopGame.apply(null);
            return null;
        };
    }

    public Function<Void, Void> onStopGame;
    public Function<Void, Void> onStartGame;
    public Function<Void, Void> onResumeGame;

    public void render(Graphics graphics) {
        stopButton.render(graphics);
    }
}
