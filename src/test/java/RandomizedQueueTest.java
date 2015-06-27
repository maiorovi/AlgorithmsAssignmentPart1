import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StdRandom.class)
public class RandomizedQueueTest {

    private RandomizedQueue queue;

    @Before
    public void setUp() {
        queue = new RandomizedQueue();
    }

    @Test
    public void returnsTrueWhenQueueIsEmpty() {
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

    @Test
    public void doubleTheSizeOfQueueWhenQueueIsFull() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }

        assertThat(queue.size()).isEqualTo(5);

    }

    @Test
    public void returnsElementFromOnElementQueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        queue.enqueue(1);

        assertThat(queue.sample()).isEqualTo(1);
        assertThat(queue.sample()).isEqualTo(1);
    }


    @Test
    public void returnsRandomElementFromQueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        mockStatic(StdRandom.class);

        for (int i = 0; i < 3; i++) {
            queue.enqueue(i);
        }

        when(StdRandom.uniform(any(Integer.class))).thenReturn(2);

        assertThat(queue.sample()).isEqualTo(2);
        assertThat(queue.size()).isEqualTo(3);
    }

    @Test
    public void returnAndRemoveRandomElementFromQueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        mockStatic(StdRandom.class);

        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }

        when(StdRandom.uniform(any(Integer.class))).thenReturn(2);

        assertThat(queue.size()).isEqualTo(5);
        assertThat(queue.dequeue()).isEqualTo(2);
        assertThat(queue.size()).isEqualTo(4);
    }

    @Test
    public void decreaseQueueSizeWhenElementsAmountIsLow() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        for(int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }

        for(int i=0; i < 16; i++) {
            queue.dequeue();
        }

        assertThat(queue.size()).isEqualTo(4);
    }
}
