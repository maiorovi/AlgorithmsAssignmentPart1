import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] obj;
    private int pointer = 0;

    public RandomizedQueue() {
        obj = (Item[]) new Object[3];
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public int size() {
        return pointer;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();

        if (pointer == obj.length - 1)
            changeQueueSize(obj.length * 2);

        obj[pointer++] = item;
    }

    public Item sample() {
        if (pointer == 0) {
            throw new NoSuchElementException();
        }

        int indexToReturn = StdRandom.uniform(pointer);
        return obj[indexToReturn];
    }

    private void changeQueueSize(int newSize) {
        Item[] temp = (Item[]) new Object[newSize];

        for (int i = 0; i < pointer; i++) {
            temp[i] = obj[i];
        }

        obj = temp;
    }

    public Item dequeue() {
        if (pointer == 0) {
            throw new NoSuchElementException();
        }
        if (pointer == obj.length / 4) {
            changeQueueSize(obj.length / 2);
        }

        int indexToReturn = StdRandom.uniform(pointer);
        exch(obj, indexToReturn, --pointer);
        Item value = obj[pointer];

        obj[pointer] = null;

        return value;
    }

    private void exch(Item[] container, int i, int j) {
        Item temp = container[i];
        container[i] = container[j];
        container[j] = temp;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int itPointer;
        private Item[] container;

        private RandomizedQueueIterator() {
            this.container = (Item[]) new Object[obj.length];
            System.arraycopy(obj, 0, container, 0, obj.length);
            this.itPointer = pointer;
        }

        @Override
        public boolean hasNext() {
            return itPointer > 0;
        }

        @Override
        public Item next() {
            if (itPointer == 0)
                throw new NoSuchElementException();

            int indextToReturn = StdRandom.uniform(itPointer);
            exch(container, indextToReturn, --itPointer);

            return container[itPointer];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
