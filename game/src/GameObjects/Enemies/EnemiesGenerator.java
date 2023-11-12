package GameObjects.Enemies;
import Equations.Equation;
import Equations.EquationGenerator;
import GameObjects.GameObjects;
import ui.GameMap;
import ui.PowerLevel;

public class EnemiesGenerator {
    private EquationGenerator equationGenerator;
    private GameObjects gameObjects;
    private GameMap gameMap;
    private PowerLevel powerLevel;
    private int enemiesLimit = 10;
    private int maxSpeed = 7;

    private int enemySpawnRate = 100;
    private int ticks = 0;

    public EnemiesGenerator(GameObjects gameObjects, GameMap gameMap, PowerLevel powerLevel) {
        this.gameObjects = gameObjects;
        this.gameMap = gameMap;
        this.powerLevel = powerLevel;
        this.equationGenerator = new EquationGenerator(powerLevel);
    }

    private void changeEnemiesSpeed() {
        gameObjects.getGameObjects().forEach((gameObject) -> {
            if (gameObject instanceof Enemy) {
                Enemy enemy = (Enemy) gameObject;
                enemy.setSpeed(calcSpeed());
            }
        });
    }

    private int calcSpeed() {
        int powerLevel = this.powerLevel.getCurrentPowerLevel();
        int speed = 2 + (int) powerLevel / 20;
        if (speed > maxSpeed) {
            speed = maxSpeed;
        }
        return -speed;
    }

    private void generateEnemy(int y) {
        int x = gameMap.getWidth() - 50 - Enemy.width;
        Equation equation = this.equationGenerator.generateEquation();
        this.gameObjects.add(new Enemy(x, y, equation));
        System.out.println("Enemy generated");
        System.out.println("CurrentSpeed: " + calcSpeed());
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
