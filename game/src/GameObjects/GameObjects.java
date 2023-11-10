package GameObjects;

import java.awt.Graphics;
import java.util.LinkedList;

public class GameObjects {
    LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
    ColisionDetector colisionDetector = new ColisionDetector(gameObjects);

    public void add(GameObject object) {
        gameObjects.add(object);
    }

    public void remove(GameObject object) {
        gameObjects.remove(object);
    }

    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void tick() {
        colisionDetector.tick();

        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics graphics) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.render(graphics);
        }
    }

    public <T extends GameObject> T get(Class<T> type) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            if (type.isInstance(tempObject)) {
                return type.cast(tempObject);
            }
        }
        return null;
    }
}
