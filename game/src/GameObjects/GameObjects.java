package GameObjects;

import java.awt.Graphics;
import java.util.LinkedList;

public class GameObjects {
    LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();

    public void addObject(GameObject object) {
        gameObjects.add(object);
    }

    public void removeObject(GameObject object) {
        gameObjects.remove(object);
    }

    public void tick() {
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
}
