package GameObjects.Enemies;
import java.awt.Graphics;
import java.awt.Rectangle;

import Equations.Equation;
import GameObjects.GameObject;

public class Enemy extends GameObject {
    static int width = 90;
    static int height = 90;
    private Equation equation;
    private EnemyTexture texture;
    private Rectangle rect;

    public Enemy(int x, int y, Equation equation) {
        super(new Rectangle(x, y, width, height));
        this.rect = (Rectangle) this.shape;
        this.texture = new EnemyTexture(rect, equation);
        this.equation = equation;
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
    
    public Equation getEquation() {
        return this.equation;
    }
}
