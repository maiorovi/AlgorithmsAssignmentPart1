import org.junit.Test;

public class PercolationStatsTest {
    private PercolationStats percolationStats;

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenTimesIsZero() {
        PercolationStats  percolationStats = new PercolationStats(100, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenTimesIsLessThenZero() {
        PercolationStats  percolationStats = new PercolationStats(100, -5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenSizeIsLessThenZero() {
        PercolationStats  percolationStats = new PercolationStats(-5, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenSizeIsZero() {
        PercolationStats  percolationStats = new PercolationStats(0, 100);
    }
}
