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
import ui.PowerLevel;
import ui.StartView;
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
    private PowerLevel powerLevel = new PowerLevel(gameMap);
    private PlayerLives playerLives = new PlayerLives(gameMap);
    private Player player = new Player(keyInputHandler, gameMap, playerLives, powerLevel);
    private GameMenu gameMenu;
    private StartView startView;
    private EnemiesGenerator enemiesGenerator;
    private EnemiesDestroyer enemiesDestroyer;

    public Game() {
        Window window = new Window(width, height, "Attack of Equations", this);
        thread = new Thread(this);
        thread.start();

        this.addKeyListener(keyInputHandler);
        this.addMouseListener(mouseHandler);

        this.gameObjects = new GameObjects();
        this.gameObjects.add(player);
        this.gameMenu = new GameMenu(gameMap, mouseHandler, state);
        this.startView = new StartView(gameMap, mouseHandler, state);
        this.enemiesGenerator = new EnemiesGenerator(gameObjects, gameMap, powerLevel);
        this.enemiesDestroyer = new EnemiesDestroyer(gameObjects, gameMap, powerLevel);

        initEvents();
    }

    private void initEvents() {
        gameMenu.onQuitGame = (Void) -> {
            System.exit(0);
            return null;
        };

        gameMenu.onResumeGame = (Void) -> {
            start();
            return null;
        };

        startView.onQuitGame = (Void) -> {
            System.exit(0);
            return null;
        };

        startView.onStartGame = (Void) -> {
            start();
            return null;
        };
    }

    public synchronized void start() {
        System.out.println("Starting game");
        state.setCurrentState(GameStates.GAME);

    }

    public synchronized void stop() {
        state.setCurrentState(GameStates.MENU);
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
    }

    // render game state
    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();
        if (state.getCurrentState() == GameStates.GAME) {
            gameMap.renderMap(graphics);
            gameObjects.render(graphics);
            powerLevel.renderScore(graphics);
            playerLives.renderLives(graphics);
            gameMenu.renderShowMenuButton(graphics);
        } else if (state.getCurrentState() == GameStates.MENU) {
            gameMenu.render(graphics);
        } else if (state.getCurrentState() == GameStates.START) {
            startView.render(graphics);
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
