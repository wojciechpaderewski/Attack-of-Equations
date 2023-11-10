package GameObjects.Players;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import GameObjects.GameObject;
import Handlers.KeyInputHandler;
import Interface.GameMap;
import Interface.PlayerLives;
import Interface.PowerLevel;


public class Player extends GameObject {
    static int startX = 0, startY = 0;
    private KeyInputHandler keyInputHandler;
    private GameMap gameMap;
    private static Rectangle rect = new Rectangle(startX, startY, 50, 50);
    private PlayerLives playerLives;
    private PowerLevel powerLevel;
    private PlayerTexture texture;

    public Player( KeyInputHandler keyInputHandler, GameMap gameMap, PlayerLives playerLives, PowerLevel powerLevel) {
        super(rect);
        this.keyInputHandler = keyInputHandler;
        this.gameMap = gameMap;
        this.playerLives = playerLives;
        this.powerLevel = powerLevel;
        this.texture = new PlayerTexture(rect, powerLevel);
    }

    public void tick() {
        rect.x += this.velX;
        rect.y += this.velY;

        if (isOutOfMapBounds()) {
            rect.x -= this.velX;
            rect.y -= this.velY;
        }

        handlePlayerMove();
        this.texture.tick();
    }

    public void render(Graphics graphics) {
        texture.render(graphics);
    }

    private boolean isOutOfMapBounds() {
        int x = rect.x;
        int y = rect.y;
        int mapWidth = gameMap.getWidth();
        int mapHeight = gameMap.getHeight();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        return x < 0 || x > mapWidth - 1.5 * width || y < 0 || y > mapHeight - 2 * height;
    }

    public void onCollision(GameObject other) {
        System.err.println("Player collided with " + other.getClass().getName());
    }

    private void handlePlayerMove() {
        if(this.keyInputHandler.isKeyPressed(KeyEvent.VK_W)) {
            this.velY = -5;
        } else if (!this.keyInputHandler.isKeyPressed(KeyEvent.VK_S)) {
            this.velY = 0;
        } 

        if(this.keyInputHandler.isKeyPressed(KeyEvent.VK_S)) {
            this.velY = 5;
        } else if (!this.keyInputHandler.isKeyPressed(KeyEvent.VK_W)) {
            this.velY = 0;
        }

        if(this.keyInputHandler.isKeyPressed(KeyEvent.VK_A)) {
            this.velX = -5;
        } else if (!this.keyInputHandler.isKeyPressed(KeyEvent.VK_D)) {
            this.velX = 0;
        }

        if(this.keyInputHandler.isKeyPressed(KeyEvent.VK_D)) {
            this.velX = 5;
        } else if (!this.keyInputHandler.isKeyPressed(KeyEvent.VK_A)) {
            this.velX = 0;
        }
    }

}