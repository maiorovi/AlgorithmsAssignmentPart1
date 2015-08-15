import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class PointSETTest {

    private PointSET pointSet;

    @Before
    public void setUp() {
        pointSet = new PointSET();
    }

    @Test
    public void emptyReturnsTrueWhenNoElementsInTheList() {
        assertThat(pointSet.empty()).isTrue();
    }

    @Test
    public void emptyReturnsFalseWhenListContainsElements() {
        Point2D point2D = new Point2D(0,0);
        pointSet.insert(point2D);

        assertThat(pointSet.empty()).isFalse();
    }

    @Test
    public void sizeReturnOneWhenOneElementInASet() {
        pointSet.insert(new Point2D(0,0));

        assertThat(pointSet.size()).isEqualTo(1);
    }

    @Test
    public void sizeReturnTwoWhenOneElementInASet() {
        pointSet.insert(new Point2D(0,0));
        pointSet.insert(new Point2D(1,1));

        assertThat(pointSet.size()).isEqualTo(2);
    }

    @Test
    public void containsReturnsTrueWhenAPointInASet() {
        Point2D point2D = new Point2D(0,0);

        pointSet.insert(point2D);

        assertThat(pointSet.contains(point2D)).isTrue();
    }

    @Test
    public void containsReturnsFalseWhenPointNotInASet() {
        pointSet.insert(new Point2D(0,0));

        assertThat(pointSet.contains(new Point2D(1,1))).isFalse();
    }

    @Test
    public void rangeReturnsAllPointsInsideTheRectangle() {
        RectHV rect = new RectHV(0,0,10,10);

        pointSet.insert(point(1,1));
        pointSet.insert(point(2,3));

        ArrayList<Point2D> collection = (ArrayList)pointSet.range(rect);

        assertThat(collection.size()).isEqualTo(2);
        Iterator<Point2D> it = collection.iterator();
        assertThat(it.next()).isEqualTo(point(1,1));
        assertThat(it.next()).isEqualTo(point(2,3));
    }

    @Test
    public void dontReturnPointNotInsideTheRectangle() {
        RectHV rect = new RectHV(0,0,10,10);

        pointSet.insert(point(1,1));
        pointSet.insert(point(2,3));
        pointSet.insert(point(11,11));

        ArrayList<Point2D> collection = (ArrayList)pointSet.range(rect);

        assertThat(collection.size()).isEqualTo(2);
        Iterator<Point2D> it = collection.iterator();
        assertThat(it.next()).isEqualTo(point(1,1));
        assertThat(it.next()).isEqualTo(point(2,3));
    }

    @Test
    public void distanceToNearestPointIsZero() {
        Point2D expectedPoint = point(0, 0);

        pointSet.insert(point(0,0));

        assertThat(pointSet.nearest(point(0,0))).isEqualTo(expectedPoint);
    }

    @Test
    public void returnsPointWithTheSmallestDistance() {
        Point2D expectedPoint = point(3,3);

        pointSet.insert(point(0,0));
        pointSet.insert(point(1,1));
        pointSet.insert(point(2,2));
        pointSet.insert(point(3,3));

        assertThat(pointSet.nearest(point(4,4))).isEqualTo(expectedPoint);
    }

    private static Point2D point(int x, int y) {
        return new Point2D(x,y);
    }
}
