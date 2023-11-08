package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Graphics.GameMap;
import Handlers.KeyInputHandler;

public class Player extends GameObject {
    static int width = 32, height = 32;
    static Color color = Color.white;
    private KeyInputHandler keyInputHandler;
    private GameMap gameMap;

    public Player(int x, int y, KeyInputHandler keyInputHandler, GameMap gameMap) {
        super(new Shapes.Rect(x, y, width, height));
        this.keyInputHandler = keyInputHandler;
        this.gameMap = gameMap;
    }

    public void tick() {
        shape.setY(shape.getY() + this.velY);
        shape.setX(shape.getX() + this.velX);

        if (isOutOfMapBounds()) {
            shape.setY(shape.getY() - this.velY);
            shape.setX(shape.getX() - this.velX);
        }

        handlePlayerMove();
    }

    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(shape.getX(), shape.getY(), 32, 32);
    }

    private boolean isOutOfMapBounds() {
        int x = shape.getX();
        int y = shape.getY();
        int mapWidth = gameMap.getWidth();
        int mapHeight = gameMap.getHeight();
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