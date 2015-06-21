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
            percolation.open(0, i);
            assertThat(percolation.isFull(0, i), is(true));
        }


    }

    @Test
    public void percolatesWhenTopAnyTopCellConnectedToAnyBottomCell() {
        percolation.open(0,0);
        percolation.open(1,0);
        percolation.open(2,0);

        assertThat(percolation.percolates(), is(true));
    }

    @Test
    public void percolatestWhenAConnectionExists() {
        Percolation percolation = new Percolation(5);

        percolation.open(0,3);
        percolation.open(1,3);
        percolation.open(2,3);
        percolation.open(3,3);
        percolation.open(3,4);
        percolation.open(4,4);

        assertThat(percolation.percolates(), is(true));
    }

    @Test
    public void notPercolateWhenThereIsNoConnectionBetweenTopAndBot() {
        Percolation percolation = new Percolation(5);

        percolation.open(0,3);
        percolation.open(1,3);
        percolation.open(2,3);
        percolation.open(3,3);
        percolation.open(3,4);

        assertThat(percolation.percolates(), is(false));
    }
}
