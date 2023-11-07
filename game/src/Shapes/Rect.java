package Shapes;

public class Rect extends Shape {
    private int width, height;

    public Rect(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    // check if point is in bounds of rectangle
    public boolean isInBounds(int x, int y) {
        return (x >= this.x && x <= this.x + width) && (y >= this.y && y <= this.y + height);
    }
}
