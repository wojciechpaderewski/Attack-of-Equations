import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import GameObjects.GameObjects;
import GameObjects.Player.Player;
import Handlers.KeyInputHandler;
import Interface.GameMap;
import Interface.PlayerLives;
import Interface.PowerLevel;

public class Game extends Canvas implements Runnable {
    static int width = 1024, height = 768;

    private boolean isRunning = false;
    private Thread thread;
    private GameObjects gameObjects;
    private KeyInputHandler keyInputHandler = new KeyInputHandler();
    private GameMap gameMap = new GameMap(width, height);
    private PowerLevel powerLevel = new PowerLevel(gameMap);
    private PlayerLives playerLives = new PlayerLives(gameMap);
    private Player player = new Player(keyInputHandler, gameMap, playerLives, powerLevel);

    public Game() {
        new Window(width, height, "Attack of Equations", this);
        this.start();
        this.addKeyListener(keyInputHandler);
        this.gameObjects = new GameObjects();
        this.gameObjects.addObject(player);
    }

    public synchronized void start() {
        if (isRunning) return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!isRunning) return;
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // write game loop limit to 60 fps
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks; // nanoseconds per tick
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns; // time passed divided by time per tick
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (isRunning) render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames); // print fps
                frames = 0;
            }
        }
        stop();
    }

    // update game state
    private void tick() {
        gameObjects.tick();
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
        gameObjects.render(graphics);
        powerLevel.renderScore(graphics);
        playerLives.renderLives(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args) throws Exception {
        new Game();
    }
}
