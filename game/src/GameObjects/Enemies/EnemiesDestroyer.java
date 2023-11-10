package GameObjects.Enemies;

import java.awt.Rectangle;

import GameObjects.GameObject;
import GameObjects.GameObjects;
import GameObjects.Players.Player;
import ui.GameMap;
import ui.PowerLevel;

public class EnemiesDestroyer {
    private GameObjects gameObjects;
    private GameMap gameMap;
    private PowerLevel powerLevel;

    public EnemiesDestroyer(GameObjects gameObjects, GameMap gameMap, PowerLevel powerLevel) {
        this.gameObjects = gameObjects;
        this.gameMap = gameMap;
        this.powerLevel = powerLevel;
    }

    private void destroyIfOutOfMapBounds() {
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
                    gameObjects.remove(enemy);
                    System.out.println("Enemy destroyed");
                }
            }
        }
    }

    private void destroyOnColisionWithPlayer() {
        Player player =  gameObjects.get(Player.class);

        if (player == null) {
            return;
        }

        // for beacuse we are removing elements from list
        for (int i = 0; i < gameObjects.getGameObjects().size(); i++) {
            GameObject gameObject = gameObjects.getGameObjects().get(i);
            if (gameObject instanceof Enemy) {
                Enemy enemy = (Enemy) gameObject;
                Rectangle enemyRect = enemy.getShape();
                Rectangle playerRect = player.getShape();
                if (enemyRect.intersects(playerRect)) {
                    player.onCollision(enemy);
                    gameObjects.remove(enemy);
                    System.out.println("Enemy destroyed");
                }
            }
        }
    }

    public void tick () {
        destroyOnColisionWithPlayer();
        destroyIfOutOfMapBounds();
    }
}
