import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DequeTest {

    private Deque<Integer> deque;

    @Before
    public void setUp() {
        deque = new Deque<Integer>();
    }

    private void addManyToHeadOfDeque(int amount) {
        for (int i = 0; i < amount; i++) {
            deque.addFirst(i);
        }
    }

    @Test
    public void isEmptyReturnsTrueWhenDequeEmpty() {
        assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    public void isEmptyReturnFalseWhenDequeHasItems() {
        deque.addFirst(10);

        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void returnsZeroWhenDequeIsEmpty() {
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void returnsOneWhenDequeSizeIsOne() {
        deque.addFirst(10);

        assertThat(deque.size()).isEqualTo(1);
    }

    @Test
    public void returnsTwoWhenDequeSizeIsTwo() {
        addManyToHeadOfDeque(2);

        assertThat(deque.size()).isEqualTo(2);
    }

    @Test
    public void removeFirstItemFromDeque() {
        addManyToHeadOfDeque(10);

        Integer item = deque.removeFirst();

        assertThat(item).isEqualTo(9);
    }

    @Test
    public void increaseDequeSizeWhenAddingToTheEndOfTheDeque() {
        addManyToHeadOfDeque(10);
        deque.addLast(10);
        deque.addLast(10);

        assertThat(deque.size()).isEqualTo(12);
    }

    @Test
    public void returnsDequeSizeAfterRemovingItemsFromTheHeadOfTheDeque() {
        addManyToHeadOfDeque(10);
        deque.removeFirst();

        assertThat(deque.size()).isEqualTo(9);
    }

    @Test
    public void returnsDequeSizeAfterRemovingItemsFromTheTailOfTheDeque() {
        addManyToHeadOfDeque(10);
        deque.removeLast();
        deque.removeLast();

        assertThat(deque.size()).isEqualTo(8);
    }

    @Test
    public void returnsLastElemenAndRemovesItFromTheDeque() {
        addManyToHeadOfDeque(10);

        assertThat(deque.removeLast()).isEqualTo(0);
    }

    @Test(expected = NullPointerException.class)
    public void throwNullPointerExceptionWhenClientAddsNullElementViaAddFirst() {
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void throwNullPointerExceptionWhenClientAddsNullElementViaAddLast() {
        deque.addLast(null);
    }




}