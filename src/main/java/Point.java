public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double slopeTo(Point that) {
        if (isVerticalLine(that)) {
            return 0;
        }

        if (isHorizontalLine(that)) {
            return Double.POSITIVE_INFINITY;
        }

        return -1;
    }

    private boolean isHorizontalLine(Point that) {
        return this.x == that.x;
    }

    private boolean isVerticalLine(Point that) {
        return this.y == that.y;
    }

    public boolean equals(Object that) {
        if (that == this)
            return true;
        if (!(that instanceof Point)) {
            return false;
        }

        Point other = (Point) that;
        if (this.x == other.x && this.y == other.y) {
            return true;
        } else {
            return false;
        }
    }
}
