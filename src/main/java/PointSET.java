import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {

    private TreeSet<Point2D> pointSet = new TreeSet<Point2D>();

    public boolean empty() {
        return pointSet.isEmpty();
    }

    public void insert(Point2D point) {
        pointSet.add(point);
    }

    public int size() {
        return pointSet.size();
    }

    public boolean contains(Point2D p) {
        return pointSet.contains(p);
    }

    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> pointsInsideRect = new ArrayList<Point2D>();

        for (Point2D point : pointSet) {
            if (rect.contains(point)) {
                pointsInsideRect.add(point);
            }
        }

        return pointsInsideRect;
    }

    public Point2D nearest(Point2D p) {
        Point2D nearestPoint = null;
        double distance = Double.MAX_VALUE;

        for (Point2D point : pointSet) {
            double tempDistance = Math.sqrt(Math.pow(point.x() - p.x(), 2) + Math.pow(point.y() - p.y(), 2));
            if (tempDistance < distance) {
                distance = tempDistance;
                nearestPoint = point;
            }
        }

        return nearestPoint;
    }
}
