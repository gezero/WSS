package cz.peinlich.exam.color.grid;

/**
 * Represents coordinates
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 9:02
 */
public class Point {
    /** horizontal coordinate */
    final int x;
    /** vertical coordinate */
    final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point move(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }
}
