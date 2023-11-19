package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import States.GameStates;
import States.State;

public class PlayerLives {
    private int maxLives = 3;
    private int currentLives = maxLives;

    private int heartWidth = 25;
    private int heartHeight = 25;
    private GameMap gameMap;
    private State state;
    private BufferedImage heartImg;

    public PlayerLives(GameMap gameMap, State state) {
        this.gameMap = gameMap;
        this.state = state;
        this.heartImg = importImg();
    }

    
    private BufferedImage importImg() {
        InputStream is = getClass().getResourceAsStream("../assets/heart.png");
        try {
            BufferedImage img = javax.imageio.ImageIO.read(is);
            return img;
        } catch (Exception e) {
            System.out.println("Error importing map.png");
            return null;
        }
    }
        

    public int getCurrentLives() { return currentLives; }
    public void decrementLives() { currentLives--; }

    public void renderLives(Graphics graphics) {
        int x = gameMap.getWidth() - 130;
        int y = 10;
        int padding = 10;

        for (int i = 0; i < currentLives; i++) {
            graphics.drawImage(heartImg, x, y, heartWidth, heartHeight, null);
            x += heartWidth + padding;
        }
    }

    private boolean isGameOver () {
        return currentLives == 0;
    }

    public void tick () {
        if (isGameOver()) {
            state.set(GameStates.GAME_OVER);
        }
    }
}
