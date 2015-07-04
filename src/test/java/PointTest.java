import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PointTest {

    private Point point;

    @Before
    public void setUp() {
        point = new Point(1,2);
    }

    @Test
    public void  slopeToReturnsPositiveZeroWhenPointsCreateAHorizontalLine() {
        Point compPoint = new Point(3,2);

        assertThat(point.slopeTo(compPoint)).isEqualTo(0);
    }

    @Test
    public void slopeToReturnPositiveInfinityWhenPointsCreateAVerticalLine() {
        Point compPoint = new Point(1,5);

        assertThat(point.slopeTo(compPoint)).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    public void pointIsEqualToItself() {
        assertThat(point.equals(point)).isTrue();
    }

    @Test
    public void equalsReturnsFalseWhenTryToCompareToNonPointObject() {
        assertThat(point.equals(new Object())).isFalse();
    }

    @Test
    public void pointWithDifferentCoordinatesShouldBeConsideredNotEquals() {
        Point other = new Point(2,3);

        assertThat(point.equals(other)).isFalse();
    }

    @Test
    public void pointWithSameCoordinatesShouldBeConsideredEquals() {
        Point other = new Point(1,2);

        assertThat(point.equals(other)).isTrue();
    }
}
