package GameObjects;

import java.util.LinkedList;

public class ColisionDetector {
    LinkedList<GameObject> gameObjects;
    public ColisionDetector(LinkedList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void tick() {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject other = gameObjects.get(j);
                if (tempObject.getShape().intersects(other.getShape().getBounds2D())) {
                    tempObject.onCollision(other);
                    other.onCollision(tempObject);
                }
            }
        }
    }
}
