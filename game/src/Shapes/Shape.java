package Shapes;

public abstract class Shape {
    protected int x, y;

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean isInBounds(int x, int y);
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int newX) {
        x = newX;
    }
    public void setY(int newY) {
        y = newY;
    }
}
