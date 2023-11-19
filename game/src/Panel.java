import java.awt.Graphics;
import java.util.function.Function;

import Handlers.KeyInputHandler;
import Handlers.MouseHandler;
import States.GameStates;
import States.State;
import ui.Score;
import ui.popups.GameOver;
import ui.popups.Instruction;
import ui.popups.Menu;
import ui.popups.Start;

public class Panel {
    private State state;
    private Score score;
    private Menu gameMenu;
    private Start startPopup;
    private GameOver gameOverPopup;
    private GameOver gameWonPopup;
    private Instruction instructionPopup;
    private Game game;

    int windowWidth, windowHeight;
    MouseHandler mouseHandler;
    KeyInputHandler keyInputHandler;

    public Panel(int windowWidth,int windowHeight,MouseHandler mouseHandler, KeyInputHandler keyInputHandler) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.mouseHandler = mouseHandler;
        this.keyInputHandler = keyInputHandler;
        
        this.state = new State(GameStates.START);

        initGame();

        this.startPopup = new Start(windowWidth, windowHeight, mouseHandler, state);
        this.gameOverPopup = new GameOver(windowWidth, windowHeight, score, mouseHandler, state);
        this.gameWonPopup = new GameOver(windowWidth, windowHeight, score, mouseHandler, state);
        this.instructionPopup = new Instruction(windowWidth, windowHeight, state, mouseHandler, score);
        
        initEvents();
    }

    private void initGame () {
        this.score = new Score(windowWidth, windowHeight, state);
        this.gameMenu = new Menu(windowWidth, windowHeight, mouseHandler, state);
        this.game = new Game(windowWidth, windowHeight, state, keyInputHandler, mouseHandler, score);
    }
    
    private void initEvents() {
        startPopup.onStartGame = start();
        gameOverPopup.onRestartGame = start();
        gameWonPopup.onRestartGame = restartGame();
        gameMenu.onRestartGame = restartGame();
        gameMenu.onResumeGame = start();
    }

    private Function<Void, Void> start() {
        return (Void) -> {
            state.set(GameStates.GAME);
            score.startTimer();
            return null;
        };
    }

    private Function<Void, Void> restartGame() {
        return (Void) -> {
            state.set(GameStates.GAME);
            initGame();
            return null;
        };
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
            case INSTRUCTION:
                instructionPopup.render(graphics);
                break;
            default:
                break;
        }
    }
}
