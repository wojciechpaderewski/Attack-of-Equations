package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.Function;

import Handlers.MouseHandler;
import States.GameStates;
import States.State;

public class StartView {
    static int width = 800;
    static int height = 600;
    static Color backgroundColor = Color.gray;
    static String title = "Attack of Equations";
    static int buttonWidth = 200;

    GameMap gameMap;
    MouseHandler mouseHandler;
    State state;

    private Button quitButton;
    private Button startButton;

    public StartView(GameMap gameMap, MouseHandler mouseHandler, State state) {
        this.mouseHandler = mouseHandler;
        this.gameMap = gameMap;
        this.state = state;
        initQuitButton();
        initStartButton();
    }


    private void initQuitButton() {
        quitButton = new Button(mouseHandler);
        quitButton.text = "Quit";
        quitButton.width = buttonWidth;
        quitButton.x = gameMap.getWidth() / 2 - quitButton.width / 2;
        quitButton.y = 500;
        quitButton.onClick = (Void) -> {
             if(state.getCurrentState() != GameStates.START) {
                return null;
            }
            onQuitGame.apply(null);
            return null;
        };
    }

    private void initStartButton() {
        startButton = new Button(mouseHandler);
        startButton.text = "Start game";
        startButton.width = buttonWidth;
        startButton.x = gameMap.getWidth() / 2 - startButton.width / 2;
        startButton.y = 350;

        startButton.onClick = (Void) -> {
             if(state.getCurrentState() != GameStates.START) {
                return null;
            }
            onStartGame.apply(null);
            return null;
        };
    }

    public Function<Void, Void> onQuitGame;
    public Function<Void, Void> onStartGame;

    private void renderBackground(Graphics graphics) {
        graphics.setColor(backgroundColor);
        // draw background on map center
        int mapWidth = gameMap.getWidth();
        int mapHeight = gameMap.getHeight();

        int x = mapWidth / 2 - width / 2;
        int y = mapHeight / 2 - height / 2;

        graphics.fillRect(x, y, width, height);
    }

    private void renderTitle(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(graphics.getFont().deriveFont(50f));
        int x = gameMap.getWidth() / 2 - graphics.getFontMetrics().stringWidth(title) / 2;
        int y = 200;
        graphics.drawString(title, x, y);
    }

    public void render(Graphics graphics) {
        renderBackground(graphics);
        renderTitle(graphics);
        startButton.render(graphics);
        quitButton.render(graphics);
    }
}
