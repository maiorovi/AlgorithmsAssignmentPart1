import java.util.Comparator;

public class Point implements Comparable<Point>{
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point firstPoint, Point secondPoint) {
            double firstSlope = slopeTo(firstPoint);
            double secondSlope = slopeTo(secondPoint);

            if (firstSlope > secondSlope) {
                return 1;
            } else if (firstSlope < secondSlope) {
                return -1;
            }

            return 0;
        }
    };

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double slopeTo(Point that) {
        if (this.equals(that)) {
            return Double.NEGATIVE_INFINITY;
        }

        if (isVerticalLine(that)) {
            return 0;
        }

        if (isHorizontalLine(that)) {
            return Double.POSITIVE_INFINITY;
        }

        double top = this.y - that.y;
        double bot = this.x - that.x;

        return top/bot;
    }

    private boolean isHorizontalLine(Point that) {
        return this.y == that.y;
    }

    private boolean isVerticalLine(Point that) {
        return this.x == that.x;
    }

    private boolean equals(Point that) {
        if (that == this)
            return true;
        if (!(that instanceof Point)) {
            return false;
        }

        Point other = that;
        if (this.x == other.x && this.y == other.y) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Point other) {
        if ((other.y > this.y) || (other.y == this.y && this.x < other.x)) {
            return -1;
        }

        if ((other.y <  this.y) || (other.y == this.y && this.x > other.x)) {
            return 1;
        }

        return 0;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
