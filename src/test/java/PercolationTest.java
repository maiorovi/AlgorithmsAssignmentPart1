import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PercolationTest {

    private Percolation percolation;
    private int size = 3;

    @Before
    public void setUp() {
        percolation = new Percolation(size);
    }

    @Test
    public void anyCellIsCloseAtTheBeginning() {
        assertThat(percolation.isOpen(1, 1), is(false));
    }

    @Test
    public void anyCellIsOpenAfterClientOpenedIt() {
        percolation.open(1,1);

        assertThat(percolation.isOpen(1,1), is(true));
    }

    @Test
    public void anyTopRowCellIsFullWhenItIsOpened() {
        for(int i = 2; i < size; i++) {
            percolation.open(1, i);
            assertThat(percolation.isFull(1, i), is(true));
        }
    }

    @Test
    public void percolatesWhenTopAnyTopCellConnectedToAnyBottomCell() {
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(3,1);

        assertThat(percolation.percolates(), is(true));
    }

    @Test
    public void percolatestWhenAConnectionExists() {
        Percolation percolation = new Percolation(5);

        percolation.open(1,4);
        percolation.open(2,4);
        percolation.open(3,4);
        percolation.open(4,4);
        percolation.open(4,5);
        percolation.open(5,5);

        assertThat(percolation.percolates(), is(true));
    }

    @Test
    public void notPercolateWhenThereIsNoConnectionBetweenTopAndBot() {
        Percolation percolation = new Percolation(5);

        percolation.open(1,4);
        percolation.open(2,4);
        percolation.open(3,4);
        percolation.open(4,4);
        percolation.open(4,5);

        assertThat(percolation.percolates(), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenArgumentIs() {
        Percolation percolation = new Percolation(-5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwIndexOutOfBoundsExceptionWhenOpenArgumentIsOutOfBounds() {
        percolation.open(-1,-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwIndexOutOfBoundsExceptionWhenIsOpenArgumentIsOutOfBounds() {
        percolation.isOpen(-1, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwIndexOutOfBoundsExceptionWhenIsFullArgumentIsOutOfBounds() {
        percolation.isFull(-1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenZeroIsGivenToConstructor() {
        Percolation percolation = new Percolation(0);
    }

    @Test
    public void whenSizeIsOneAndCellIsOpenThenPercolatesReturnsFalse() {
        Percolation percolation = new Percolation(1);
        percolation.open(1,1);

        assertThat(percolation.percolates(), is(false));
    }

    @Test
    public void whenSizeIsOneAndCellIsBlockedThenPercolationReturnsFalse() {
        Percolation percolation = new Percolation(1);

        assertThat(percolation.percolates(), is(false));
    }

    @Test
    public void whenCellIsNotConnectedToTopThenItShouldBeEmpty() {
        percolation.open(2,2);

        assertThat(percolation.isFull(2,2), is(false));
    }

    @Test
    public void test1() {
        Percolation percolation = new Percolation(4);
        percolation.open(4,1);
        percolation.open(3,1);
        percolation.open(2,1);
        percolation.open(1,1);
        percolation.open(1,4);
        percolation.open(2,4);
        percolation.open(4,4);

        assertThat(percolation.isFull(4,4), is(false));
    }

}
