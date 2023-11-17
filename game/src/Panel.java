import java.awt.Graphics;
import java.util.function.Function;

import Handlers.KeyInputHandler;
import Handlers.MouseHandler;
import States.GameStates;
import States.State;
import ui.GameMap;
import ui.GameMenu;
import ui.Score;
import ui.views.GameOverView;
import ui.views.StartView;

public class Panel {
    private State state;
    private Score score;
    private GameMenu gameMenu;
    private StartView startView;
    private GameOverView gameOverView;
    private GameOverView gameWonView;
    private Game game;

    public Panel(int windowWidth,int windowHeight,MouseHandler mouseHandler, KeyInputHandler keyInputHandler) {
        this.score = new Score(windowWidth, windowHeight, state);
        this.gameMenu = new GameMenu(gameMap, mouseHandler, state);
        this.startView = new StartView(gameMap, mouseHandler, state);
        this.gameOverView = new GameOverView(gameMap, score, mouseHandler, state);
        this.gameWonView = new GameOverView(gameMap, score, mouseHandler, state);
        this.game = new Game(windowWidth, windowHeight, state, keyInputHandler, mouseHandler, score);
        
        initEvents();
    }
    
    private void initEvents() {
        startView.onStartGame = start();
        gameOverView.onRestartGame = start();
        gameWonView.onRestartGame = start();
        gameOverView.onQuitGame = quitGame();
        gameWonView.onQuitGame = quitGame();
        gameMenu.onQuitGame = quitGame();
    }

    private Function<Void, Void> quitGame() {
        System.exit(0);
        return null;
    }

    public Function<Void, Void> start() {
        state.set(GameStates.GAME);
        return null;
    }

    public synchronized void stop() {
        state.set(GameStates.MENU);
        score.stopTimer();
    }

    public void tick() {
        if (state.get() == GameStates.GAME) {
            game.tick();
        }
    }

    // render game state
    public void render(Graphics graphics) {
        game.render(graphics);

        if (state.get() == GameStates.MENU) {
            gameMenu.render(graphics);
        } else if (state.get() == GameStates.START) {
            startView.render(graphics);
        } else if (state.get() == GameStates.GAME_OVER) {
            gameOverView.render(graphics);
        } else if (state.get() == GameStates.GAME_WON) {
            gameWonView.render(graphics);
        }
    }
    
}
