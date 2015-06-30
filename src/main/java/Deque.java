import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private short dequeSize = 0;

    public boolean isEmpty() {
        return dequeSize == 0;
    }

    public void addFirst(Item item) {
        throwExceptioWhenNull(item);
        dequeSize++;
        if (first == null && last == null) {
            first = new Node(last, null, item);
            last = first;
            return;
        }

        Node oldFirst = first;
        first = new Node(oldFirst, null, item);
        oldFirst.previous = first;
    }

    public int size() {
        return dequeSize;
    }

    public Item removeFirst() {
        throwExceptionWhenEmpty();
        Item value = first.value;
        first = first.next;
        if (first != null) {
            first.previous = null;
        }
        dequeSize--;

        clearLinksIfDequeIsEmpty();

        return value;
    }

    public void addLast(Item item) {
        throwExceptioWhenNull(item);
        dequeSize++;
        if (last == null && first == null) {
            last = new Node(null, first, item);
            first = last;
            return;
        }

        Node oldLast = last;
        last = new Node(null, oldLast, item);
        oldLast.next = last;
    }

    public Item removeLast() {
        throwExceptionWhenEmpty();
        Node oldLast = last;
        last = oldLast.previous;
        if (last != null) {
            last.next = null;
        }
        dequeSize--;

        clearLinksIfDequeIsEmpty();

        return oldLast.value;
    }

    private void clearLinksIfDequeIsEmpty() {
        if(isEmpty()) {
            first = null;
            last = null;
        }
    }

    private void throwExceptionWhenEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private void throwExceptioWhenNull(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private short itDequeSize;
        private Node current;

        private DequeIterator() {
            itDequeSize = dequeSize;
            if (first != null)
                current = new Node(first.next, first.previous, first.value);
        }

        @Override
        public boolean hasNext() {
            return itDequeSize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item valueToReturn = current.value;
            current = current.next;
            itDequeSize--;

            return valueToReturn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        private Node next;
        private Node previous;
        private Item value;

        public Node(Node next, Node prev, Item value) {
            this.next = next;
            this.previous = prev;
            this.value = value;
        }

    }
}
