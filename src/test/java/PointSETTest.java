import org.junit.Before;
import org.junit.Test;

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
}
