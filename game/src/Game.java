import java.awt.Graphics;

import GameObjects.GameObjects;
import GameObjects.Enemies.EnemiesDestroyer;
import GameObjects.Enemies.EnemiesGenerator;
import GameObjects.Players.Player;
import Handlers.KeyInputHandler;
import Handlers.MouseHandler;
import States.GameStates;
import States.State;
import ui.GameMap;
import ui.PlayerLives;
import ui.Score;

public class Game {
    private GameMap gameMap;
    private State state;
    private GameObjects gameObjects;
    private Score score;
    private PlayerLives playerLives;
    private Player player;
    private EnemiesGenerator enemiesGenerator;
    private EnemiesDestroyer enemiesDestroyer;

    public Game(int windowWidth,int windowHeight, State state,KeyInputHandler keyInputHandler, MouseHandler mouseHandler, Score score) {
        this.gameMap = new GameMap(windowWidth, windowHeight);
        this.gameObjects = new GameObjects();
        this.score = score;
        this.playerLives = new PlayerLives(gameMap, state);
        this.player = new Player(keyInputHandler, gameMap, playerLives, score);

        this.enemiesGenerator = new EnemiesGenerator(gameObjects, gameMap, score);
        this.enemiesDestroyer = new EnemiesDestroyer(gameObjects, gameMap, score);
        
        this.gameObjects.add(player);
    }

    // update game state
    public void tick() {
        gameObjects.tick();
        enemiesGenerator.tick();
        enemiesDestroyer.tick();
        playerLives.tick();
        score.tick();
    }

    // render game state
    public void render(Graphics graphics) {
        gameMap.renderMap(graphics);
        gameObjects.render(graphics);
        score.renderScore(graphics);
        playerLives.renderLives(graphics);
        //TODO:button
    }
}
