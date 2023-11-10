package GameObjects.Enemies;
import GameObjects.GameObjects;
import Interface.GameMap;
import Interface.PowerLevel;

public class EnemiesGenerator {
    private GameObjects gameObjects;
    private GameMap gameMap;
    private PowerLevel powerLevel;
    private int enemiesLimit = 10;

    private int enemySpawnRate = 100;
    private int ticks = 0;

    public EnemiesGenerator(GameObjects gameObjects, GameMap gameMap, PowerLevel powerLevel) {
        this.gameObjects = gameObjects;
        this.gameMap = gameMap;
        this.powerLevel = powerLevel;
    }

    private void changeEnemiesSpeed() {
        int powerLevel = this.powerLevel.getCurrentPowerLevel();
        int speed = 1 + powerLevel / 10;
        gameObjects.getGameObjects().forEach((gameObject) -> {
            if (gameObject instanceof Enemy) {
                Enemy enemy = (Enemy) gameObject;
                enemy.setSpeed(-speed);
            }
        });
    }

    private void generateEnemy(int y) {
        int x = gameMap.getWidth() - 100;
        this.gameObjects.add(new Enemy(x, y));
        System.out.println("Enemy generated");
    }

    private void generateEnemies() {
        if (ticks == enemySpawnRate) {
            if (gameObjects.getGameObjects().size() >= enemiesLimit) {
                return;
            }

            System.out.println("Generating enemy");
            int offset = 150;
            int y = (int) getRandomNumber(offset, gameMap.getHeight() - offset);
            System.out.println("y: " + y);
            generateEnemy(y);
            ticks = 0;
        }
    }

    public void tick () {
        generateEnemies();
        changeEnemiesSpeed();
        ticks++;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
