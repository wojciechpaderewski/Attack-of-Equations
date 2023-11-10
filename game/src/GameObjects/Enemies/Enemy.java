package GameObjects.Enemies;
import java.awt.Graphics;
import java.awt.Rectangle;
import GameObjects.GameObject;

public class Enemy extends GameObject {
    private EnemyTexture texture;
    private Rectangle rect;

    public Enemy(int x, int y) {
        super(new Rectangle(x, y, 50, 50));
        this.rect = (Rectangle) this.shape;
        this.texture = new EnemyTexture(rect);
    }

    public void setSpeed(int speed) {
        this.velX = speed;
    }

    public void tick() {
        rect.x += this.velX;
        this.texture.tick();
    }

    public void render(Graphics graphics) {
        this.texture.render(graphics);
    }

    public void onCollision(GameObject other) {
    }
    
}
