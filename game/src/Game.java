import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import GameObjects.GameObjects;
import GameObjects.Players.Player;
import Handlers.KeyInputHandler;
import Handlers.MouseHandler;
import States.GameStates;
import States.State;
import ui.GameMap;
import ui.GameMenu;
import ui.PlayerLives;
import ui.Score;
import ui.views.GameOverView;
import ui.views.StartView;
import GameObjects.Enemies.EnemiesDestroyer;
import GameObjects.Enemies.EnemiesGenerator;

public class Game extends Canvas implements Runnable {
    static int width = 1024, height = 768;
    private State state = new State(GameStates.START);
    private Thread thread;

    private KeyInputHandler keyInputHandler = new KeyInputHandler();
    private MouseHandler mouseHandler = new MouseHandler();

    private GameObjects gameObjects;
    private GameMap gameMap = new GameMap(width, height);
    private Score score = new Score(gameMap, state);
    private PlayerLives playerLives = new PlayerLives(gameMap, state);
    private Player player = new Player(keyInputHandler, gameMap, playerLives, score);
    private GameMenu gameMenu;
    private StartView startView;
    private GameOverView gameOverView;
    private GameOverView gameWonView;
    private EnemiesGenerator enemiesGenerator;
    private EnemiesDestroyer enemiesDestroyer;

    public Game() {
        Window window = new Window(width, height, "Attack of Equations", this);
        
        this.addKeyListener(keyInputHandler);
        this.addMouseListener(mouseHandler);
        
        this.gameObjects = new GameObjects();
        this.gameObjects.add(player);
        this.gameMenu = new GameMenu(gameMap, mouseHandler, state);
        this.startView = new StartView(gameMap, mouseHandler, state);
        this.gameOverView = new GameOverView(gameMap, score, mouseHandler, state);
        this.gameWonView = new GameOverView(gameMap, score, mouseHandler, state);
        this.enemiesGenerator = new EnemiesGenerator(gameObjects, gameMap, score);
        this.enemiesDestroyer = new EnemiesDestroyer(gameObjects, gameMap, score);
        
        thread = new Thread(this);
        thread.start();

        initEvents();
    }
    
    private void initEvents() {
        gameMenu.onQuitGame = (Void) -> {
            System.exit(0);
            return null;
        };

        gameMenu.onResumeGame = (Void) -> {
            start();
            this.score.resumeTimer();
            return null;
        };

        startView.onQuitGame = (Void) -> {
            System.exit(0);
            return null;
        };

        startView.onStartGame = (Void) -> {
            start();
            this.score.startTimer();
            return null;
        };

        gameOverView.onQuitGame = (Void) -> {
            System.exit(0);
            return null;
        };

        gameOverView.onRestartGame = (Void) -> {
            restartGame();
            return null;
        };

        gameWonView.onQuitGame = (Void) -> {
            System.exit(0);
            return null;
        };

        gameWonView.onRestartGame = (Void) -> {
            restartGame();
            return null;
        };
    }

    private void restartGame() {
        System.out.println("Restarting game");
    }

    public synchronized void start() {
        System.out.println("Starting game");
        state.setCurrentState(GameStates.GAME);
    }

    public synchronized void stop() {
        state.setCurrentState(GameStates.MENU);
        score.stopTimer();
    }

    public void run() {
        // write game loop limit to 60 fps
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks; // nanoseconds per tick
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns; // time passed divided by time per tick
            lastTime = now;
            while (delta >= 1) {
                if (state.getCurrentState() == GameStates.GAME) {
                    tick();
                }
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames); // print fps
                frames = 0;
            }
        }
    }

    // update game state
    private void tick() {
        gameObjects.tick();
        enemiesGenerator.tick();
        enemiesDestroyer.tick();
        playerLives.tick();
        score.tick();
    }

    // render game state
    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();
        gameMap.renderMap(graphics);
        if (state.getCurrentState() == GameStates.GAME ) {
            gameObjects.render(graphics);
            score.renderScore(graphics);
            playerLives.renderLives(graphics);
            gameMenu.renderShowMenuButton(graphics);
        } else if (state.getCurrentState() == GameStates.MENU) {
            gameMenu.render(graphics);
        } else if (state.getCurrentState() == GameStates.START) {
            startView.render(graphics);
        } else if (state.getCurrentState() == GameStates.GAME_OVER) {
            gameOverView.render(graphics);
        } else if (state.getCurrentState() == GameStates.GAME_WON) {
            gameWonView.render(graphics);
        }

        graphics.dispose();
        bufferStrategy.show();
        
        // fixes lags on linux systems
        Toolkit.getDefaultToolkit().sync();
    }

    public static void main(String[] args) throws Exception {
        new Game();
    }
}
