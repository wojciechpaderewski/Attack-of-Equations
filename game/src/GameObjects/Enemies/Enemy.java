package GameObjects.Enemies;
import java.awt.Graphics;
import java.awt.Rectangle;
import GameObjects.GameObject;

public class Enemy extends GameObject {
    private int startX = 0, startY = 0;
    private EnemyTexture texture;
    private Rectangle rect;

    public Enemy(int x, int y) {
        super(new Rectangle(x, y, 50, 50));
        this.startX = x;
        this.startY = y;
        this.rect = (Rectangle) this.shape;
        this.texture = new EnemyTexture(rect);
    }

    public void tick() {
        rect.x += this.velX;
        rect.y += this.velY;
        this.texture.tick();
    }

    public void render(Graphics graphics) {
        this.texture.render(graphics);
    }

    public void onCollision(GameObject other) {
    }
    
}
