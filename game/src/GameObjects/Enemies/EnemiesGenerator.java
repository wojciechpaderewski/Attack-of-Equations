package GameObjects.Enemies;
import java.awt.Rectangle;

import Equations.Equation;
import Equations.EquationGenerator;
import GameObjects.GameObject;
import GameObjects.GameObjects;
import GameObjects.Players.Player;
import ui.GameMap;
import ui.Score;

public class EnemiesGenerator {
    private EquationGenerator equationGenerator;
    private GameObjects gameObjects;
    private GameMap gameMap;
    private Score score;
    private int enemiesLimit = 13;
    private int maxSpeed = 5;

    private int enemySpawnRate = 60;
    private int enemySpawnRateLimit = 30;
    private int ticks = 0;

    public EnemiesGenerator(GameObjects gameObjects, GameMap gameMap, Score powerLevel) {
        this.gameObjects = gameObjects;
        this.gameMap = gameMap;
        this.score = powerLevel;
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
        int powerLevel = this.score.getCurrentScore();
        int speed = 2 + (int) powerLevel / 20;
        if (speed > maxSpeed) {
            speed = maxSpeed;
        }
        return -speed;
    }

    private int calcEnemySpawnRate() {
        int powerLevel = this.score.getCurrentScore();
        int spawnRate = enemySpawnRate - (int) (powerLevel / 1.7);
        if (spawnRate < enemySpawnRateLimit) {
            spawnRate = enemySpawnRateLimit;
        }
        System.out.println("SpawnRate: " + spawnRate);
        return spawnRate;
    }

    private void generateEnemy(int y) {
        int x = gameMap.getWidth() - 70 - Enemy.width;
        Equation equation = this.equationGenerator.generateEquation();
        this.gameObjects.add(new Enemy(x, y, equation));
        System.out.println("Enemy generated");
        System.out.println("CurrentSpeed: " + calcSpeed());
    }

    private void removeEquatinons() {
        for(int i = 0; i < gameObjects.getGameObjects().size(); i++) {
            GameObject gameObject = gameObjects.getGameObjects().get(i);
            if (gameObject instanceof Enemy) {
                Enemy enemy = (Enemy) gameObject;
                Rectangle enemyRect = enemy.getShape();
                int x = (int) enemyRect.getX();
                int y = (int) enemyRect.getY();
                int mapWidth = gameMap.getWidth();
                int mapHeight = gameMap.getHeight();
                int width = (int) enemyRect.getWidth();
                int height = (int) enemyRect.getHeight();
                if (x < 0 || x > mapWidth - 1.5 * width || y < 0 || y > mapHeight - 2 * height) {
                    equationGenerator.removeEquation(enemy.getEquation());
                }
            }
        }
    }

    private void removeEquationOfDestroyedEnemy() {
        Player player =  gameObjects.get(Player.class);
        if (player == null) {
            return;
        }

        for (int i = 0; i < gameObjects.getGameObjects().size(); i++) {
            GameObject gameObject = gameObjects.getGameObjects().get(i);
            if (gameObject instanceof Enemy) {
                Enemy enemy = (Enemy) gameObject;
                Rectangle enemyRect = enemy.getShape();
                Rectangle playerRect = player.getShape();
                if (enemyRect.intersects(playerRect)) {
                    equationGenerator.removeEquation(enemy.getEquation());
                }
            }
        }
    }


    private void generateEnemies() {
        if (ticks >= calcEnemySpawnRate()) {
            int allEnemies = gameObjects.getGameObjects().size() - 1;

            if (allEnemies >= enemiesLimit) {
                System.out.println("Enemies limit reached");
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
        removeEquatinons();
        removeEquationOfDestroyedEnemy();
        ticks++;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
