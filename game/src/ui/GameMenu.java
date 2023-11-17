package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.Function;

import Handlers.MouseHandler;
import States.GameStates;
import States.State;

public class GameMenu {
    static int width = 300;
    static int height = 280;
    static Color backgroundColor = Color.gray;

    GameMap gameMap;
    MouseHandler mouseHandler;
    State state;

    private Button quitButton;
    private Button resumeButton;
    private Button showMenuButton;

    public GameMenu(GameMap gameMap, MouseHandler mouseHandler, State state) {
        this.mouseHandler = mouseHandler;
        this.gameMap = gameMap;
        this.state = state;
        initQuitButton();
        initResumeButton();
        initShowMenuButton();
    }

    private void initShowMenuButton() {
        showMenuButton = new Button(mouseHandler);
        showMenuButton.text = "Menu";
        showMenuButton.x = gameMap.getWidth() - showMenuButton.width - 50;
        showMenuButton.y = gameMap.getHeight() - showMenuButton.height - 50;

        showMenuButton.onClick = (Void) -> {
            state.set(GameStates.MENU);
            return null;
        };
    }

    private void initQuitButton() {
        quitButton = new Button(mouseHandler);
        quitButton.text = "Quit";
        quitButton.x = gameMap.getWidth() / 2 - quitButton.width / 2;
        quitButton.y = 300;
        quitButton.onClick = (Void) -> {
             if(state.get() != GameStates.MENU) {
                return null;
            }
            onQuitGame.apply(null);
            return null;
        };
    }

    private void initResumeButton() {
        resumeButton = new Button(mouseHandler);
        resumeButton.text = "Resume";
        resumeButton.x = gameMap.getWidth() / 2 - resumeButton.width / 2;
        resumeButton.y = 420;

        resumeButton.onClick = (Void) -> {
             if(state.get() != GameStates.MENU) {
                return null;
            }
            onResumeGame.apply(null);
            return null;
        };
    }

    public Function<Void, Void> onQuitGame;
    public Function<Void, Void> onResumeGame;

    private void renderBackground(Graphics graphics) {
        graphics.setColor(backgroundColor);
        // draw background on map center
        int mapWidth = gameMap.getWidth();
        int mapHeight = gameMap.getHeight();

        int x = mapWidth / 2 - width / 2;
        int y = mapHeight / 2 - height / 2;

        graphics.fillRect(x, y, width, height);
    }

    public void render(Graphics graphics) {
        renderBackground(graphics);
        quitButton.render(graphics);
        resumeButton.render(graphics);
    }

    public void renderShowMenuButton(Graphics graphics) {
        showMenuButton.render(graphics);
    }
}
