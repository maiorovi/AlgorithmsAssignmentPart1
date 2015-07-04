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

    @Test
    public void slopeBetweenTheSamePointIsNegativeInfinity() {
        assertThat(point.slopeTo(point)).isEqualTo(Double.NEGATIVE_INFINITY);
    }

    @Test
    public void slopeIsCalculatedOnCustomLine() {
        Point other = new Point(5,10);

        assertThat(point.slopeTo(other)).isEqualTo(2);
    }

    @Test
    public void whenPointsAreEqualZeroShouldBeReturned() {
        Point other = new Point(1,2);

        assertThat(point.compareTo(other)).isEqualTo(0);
    }

    @Test
    public void givenPointIsBiggerWhenY1BiggerThenY0() {
        Point other = new Point(1,3);

        assertThat(point.compareTo(other)).isEqualTo(-1);
    }

    @Test
    public void givenPointIsBiggerWhenY1AndY0AreEqualButX1IsBiggerThenX0() {
        Point other = new Point(2,2);

        assertThat(point.compareTo(other)).isEqualTo(-1);
    }

    @Test
    public void givenPointIsLessWhenY1IsLessThenY0() {
        Point that = new Point(5,6);
        Point other = new Point(4,5);

        assertThat(that.compareTo(other)).isEqualTo(1);

    }

    @Test
    public void givenPointIsLessWhenY1AndY0AreEqualButX1IsLessThenX0() {
        Point that = new Point(5,6);
        Point other = new Point(4,6);

        assertThat(that.compareTo(other)).isEqualTo(1);
    }

    @Test
    public void compareToReturnZeroWhenGivenPointsAreEqualWhenCoordinatesAreEqual() {
        Point that = new Point(5,6);
        Point other = new Point(5,6);

        assertThat(that.compareTo(other)).isEqualTo(0);
    }
}
