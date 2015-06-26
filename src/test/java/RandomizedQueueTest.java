import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomizedQueueTest {

    private RandomizedQueue queue;

    @Before
    public void setUp() {
        queue = new RandomizedQueue();
    }

    @Test
    public void  returnsTrueWhenQueueIsEmpty() {
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    public void returnsZeroWhenQueueIsEmpty() {
        assertThat(queue.size()).isEqualTo(0);
    }

    @Test
    public void isEmptyReturnFalseWhenQueueHaveAtLeastOneElement() {
        RandomizedQueue queue = new RandomizedQueue();
        Object item = new Object();
        queue.enqueue(item);

        assertThat(queue.isEmpty()).isFalse();
    }

    @Test
    public void sizeReturnsOneWhenThereIsOneObjectInQueue() {
        RandomizedQueue queue = new RandomizedQueue();
        Object item = new Object();
        queue.enqueue(item);

        assertThat(queue.size()).isEqualTo(1);
    }

    @Test
    public void sizeReturnsThreeWhenThereIsThreeObjectsInQueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        Integer item = new Integer(1);
        Integer item2 = new Integer(2);
        Integer item3 = new Integer(3);

        queue.enqueue(item);
        queue.enqueue(item2);
        queue.enqueue(item3);

        assertThat(queue.size()).isEqualTo(3);
    }
}
