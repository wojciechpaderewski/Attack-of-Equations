package GameObjects.Player;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import GameObjects.GameObject;
import Handlers.KeyInputHandler;
import Interface.GameMap;
import Interface.PlayerLives;
import Interface.PowerLevel;
import Shapes.Rect;

public class Player extends GameObject {
    static int startX = 0, startY = 0;
    private KeyInputHandler keyInputHandler;
    private GameMap gameMap;
    private static Rect rect = new Rect(startX, startY, 40, 40);
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
        rect.setY(rect.getY() + this.velY);
        rect.setX(rect.getX() + this.velX);

        if (isOutOfMapBounds()) {
            rect.setY(rect.getY() - this.velY);
            rect.setX(rect.getX() - this.velX);
        }

        handlePlayerMove();
        this.texture.tick();
    }

    public void render(Graphics graphics) {
        texture.render(graphics);
    }

    private boolean isOutOfMapBounds() {
        int x = rect.getX();
        int y = rect.getY();
        int mapWidth = gameMap.getWidth();
        int mapHeight = gameMap.getHeight();
        int width = rect.getWidth();
        int height = rect.getHeight();
        return x < 0 || x > mapWidth - 1.5 * width || y < 0 || y > mapHeight - 2 * height;
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