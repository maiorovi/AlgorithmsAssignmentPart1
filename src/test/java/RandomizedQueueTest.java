import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
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

    @Test
    public void iteratorsHasNextReturnsFalseWhenQueueIsEmpty() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        assertThat(queue.iterator().hasNext()).isFalse();
    }

    @Test
    public void iteratorsHasNextReturnsTrueWhenQueueIsNotEmpty() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        queue.enqueue(12);

        assertThat(queue.iterator().hasNext()).isTrue();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void throwUnsupportedOperationExceptionWhenRemoveOnIteratorCalled() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        queue.iterator().remove();
    }

    @Test
    public void nextCallOnIteratorReturnsRandomElementFromQueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        queue.enqueue(10);

        assertThat(queue.iterator().next()).isEqualTo(10);
    }

    @Test
    public void whileHasNextReturnsTrueIteratorGiveNextElement() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int counter = 0;

        for(int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }

        Iterator<Integer> it = queue.iterator();

        while (it.hasNext()) {
            it.next();
            counter++;
        }

        assertThat(counter).isEqualTo(20);
    }

    @Test
    public void twoIteratorsHasThereOwnRandomOrder() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        for(int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }

        Iterator<Integer> itOne = queue.iterator();
        Iterator<Integer> itTwo = queue.iterator();

        ArrayList<Integer> listOne = new ArrayList<>();
        ArrayList<Integer> listTwo = new ArrayList<>();

        while(itOne.hasNext() && itTwo.hasNext()) {
            listOne.add(itOne.next());
            listTwo.add(itTwo.next());
        }

        for(int i = 0; i < listOne.size(); i++) {
            if (listOne.get(i) != listTwo.get(i))
                break;

            if (i == listOne.size() - 1)
                fail("Iterators don`t return unique order");
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsNoSuchElementExceptionWhenCalledNextButThereIsNoMoreElement() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(10);
        queue.enqueue(12);

        Iterator<Integer> it = queue.iterator();

        it.next();
        it.next();
        it.next();
    }

    @Test(expected = NullPointerException.class)
    public void throwNullPointerExceptionOnAttemptToAddNullElement() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(null);
    }

    @Test(expected = NoSuchElementException .class)
    public void throwNoSuchElementExceptionWhenSampleOnEmptyQueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.sample();
    }

    @Test(expected = NoSuchElementException .class)
    public void throwNoSuchElementExceptionWhenDequeueOnEmptyQueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.dequeue();
    }

}
