import java.awt.Graphics;
import java.util.function.Function;

import Handlers.KeyInputHandler;
import Handlers.MouseHandler;
import States.GameStates;
import States.State;
import ui.Score;
import ui.popups.GameOver;
import ui.popups.Menu;
import ui.popups.Start;

public class Panel {
    private State state;
    private Score score;
    private Menu gameMenu;
    private Start startPopup;
    private GameOver gameOverPopup;
    private GameOver gameWonPopup;
    private Game game;

    public Panel(int windowWidth,int windowHeight,MouseHandler mouseHandler, KeyInputHandler keyInputHandler) {
        this.state = new State(GameStates.START);
        this.score = new Score(windowWidth, windowHeight, state);
        this.gameMenu = new Menu(windowWidth, windowHeight, mouseHandler, state);
        this.startPopup = new Start(windowWidth, windowHeight, mouseHandler, state);
        this.gameOverPopup = new GameOver(windowWidth, windowHeight, score, mouseHandler, state);
        this.gameWonPopup = new GameOver(windowWidth, windowHeight, score, mouseHandler, state);
        this.game = new Game(windowWidth, windowHeight, state, keyInputHandler, mouseHandler, score);
        
        initEvents();
    }
    
    private void initEvents() {
        startPopup.onStartGame = start();
        gameOverPopup.onRestartGame = start();
        gameWonPopup.onRestartGame = start();
        gameOverPopup.onQuitGame = quitGame();
        gameWonPopup.onQuitGame = quitGame();
        gameMenu.onQuitGame = quitGame();
        startPopup.onQuitGame = quitGame();
    }

    private Function<Void, Void> quitGame() {
        return (Void) -> {
            if (state.get() == GameStates.GAME) {
                return null;
            }
            System.exit(0);
            return null;
        };
    }

    public Function<Void, Void> start() {
        return (Void) -> {
            state.set(GameStates.GAME);
            score.startTimer();
            return null;
        };
    }

    public synchronized void stop() {
        state.set(GameStates.GAME_OVER);
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
        
        switch (state.get()) {
            case MENU:
                gameMenu.render(graphics);
                break;
            case GAME_OVER:
                gameOverPopup.render(graphics);
                break;
            case GAME_WON:
                gameWonPopup.render(graphics);
                break;
            case START:
                startPopup.render(graphics);
                break;
            default:
                break;
        }
    }
    
}
