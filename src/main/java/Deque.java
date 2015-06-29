import java.util.NoSuchElementException;

public class Deque<T> {

    private Node first;
    private Node last;
    private int dequeSize = 0;

    public boolean isEmpty() {
        return dequeSize == 0;
    }

    public void addFirst(T item) {
        throwExceptioWhenNull(item);
        dequeSize++;
        if (first == null && last == null) {
            first = new Node(first, null, item);
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

    public T removeFirst() {
        throwExceptionWhenEmpty();
        Node temp = first;
        first = first.next;
        dequeSize--;
        return temp.value;
    }

    public void addLast(T item) {
        throwExceptioWhenNull(item);
        if (last == null && first == null) {
            last = new Node(null, last, item);
            first = last;
        }
        Node oldLast = last;
        last = new Node(null, oldLast, item);
        oldLast.next = last;
        dequeSize++;
    }

    public T removeLast() {
        throwExceptionWhenEmpty();
        Node oldLast = last;
        last = oldLast.previous;
        dequeSize--;

        return oldLast.value;
    }

    private void throwExceptionWhenEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private void throwExceptioWhenNull(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    private class Node {
        private Node next;
        private Node previous;
        private T value;

        public Node(Node next, Node prev, T value) {
            this.next = next;
            this.previous = prev;
            this.value = value;
        }

    }
}
