package GameObjects.Enemies;
import GameObjects.GameObjects;
import Interface.GameMap;

public class EnemiesGenerator {
    private GameObjects gameObjects;
    private GameMap gameMap;

    public EnemiesGenerator(GameObjects gameObjects, GameMap gameMap) {
        this.gameObjects = gameObjects;
        this.gameMap = gameMap;

        //test
        this.gameObjects.add(new Enemy(gameMap.getWidth() - 100, gameMap.getHeight() / 2 - 100));
    }

    public void tick () {
        //
    }
}
