import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        addManyToTailDeque(2);

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

    @Test
    public void addLastThenReturnFirst() {
        addManyToTailDeque(10);

        assertThat(deque.removeFirst()).isEqualTo(0);
    }

    @Test
    public void addLastAddFirstTwiceThenCheckFirst() {
        deque.addLast(3);
        deque.addFirst(1);
        deque.addFirst(2);

        assertThat(deque.removeFirst()).isEqualTo(2);
    }

    @Test
    public void seriesOfAddFirstAndThenRemoveLast() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        assertThat(deque.removeLast()).isEqualTo(1);
    }

    @Test
    public void seriesOfAddLastThenRemoveFirst() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertThat(deque.removeFirst()).isEqualTo(1);
        assertThat(deque.removeLast()).isEqualTo(3);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenAttemptToRemoveFirstFromEmptyDeque() {
        deque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenAttemptToRemoveLastFromEptyDeque() {
        deque.removeLast();
    }


    private void addManyToTailDeque(int amount) {
        for (int i = 0; i < amount; i++)
            deque.addLast(i);
    }

    @Test(expected = NullPointerException.class)
    public void throwNullPointerExceptionWhenClientAddsNullElementViaAddFirst() {
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void throwNullPointerExceptionWhenClientAddsNullElementViaAddLast() {
        deque.addLast(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void throwExceptionWhenIteratorRemoveCalled() {
        deque.iterator().remove();
    }

    @Test
    public void hasNextReturnsTrueWhenThereIsElementToIterate() {
        deque.addFirst(10);
        Iterator<Integer> it = deque.iterator();

        assertThat(it.hasNext()).isTrue();
    }

    @Test
    public void hasNextReturnsFalseWhenThereIsNoElementToIterate() {
        Iterator<Integer> it =  deque.iterator();

        assertThat(it.hasNext()).isFalse();
    }

    @Test
    public void hasNextReturnsNextElement() {
        deque.addFirst(5);
        Iterator<Integer> it = deque.iterator();

        assertThat(it.next()).isEqualTo(5);
    }

    @Test
    public void iteratesThroughFullDeque() {
        addManyToTailDeque(10);
        int counter = 0;
        Iterator<Integer> it = deque.iterator();

        assertThat(it.next()).isEqualTo(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenIteratorReturnedLastElement() {
        deque.addFirst(5);
        Iterator<Integer> it = deque.iterator();
        it.next();
        it.next();
    }

    @Test
    public void addFirstRemoveFirstInteraction() {
        deque.addFirst(5);
        deque.removeFirst();
        deque.addFirst(6);

        assertThat(deque.removeFirst()).isEqualTo(6);
    }

    @Test
    public void addFirstRemoveLastInteraction() {
        deque.addFirst(5);
        deque.removeLast();
        deque.addFirst(3);

        assertThat(deque.removeLast()).isEqualTo(3);
    }

    @Test
    public void seriesAddLastAndTwoRemoveFirstInteractions() {
        deque.addLast(0);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.removeFirst();
        deque.addLast(5);
        assertThat(deque.removeFirst()).isEqualTo(1);
    }

    @Test
    public void seriesofAddFirstRemoveLastAndAddLastOperation() {
        deque.isEmpty();
        deque.addFirst(1);
        deque.removeLast();
        deque.addLast(3);
    }

}