public class RandomizedQueue<Item> {

    private Item obj[];
    private int pointer = 0;

    public RandomizedQueue() {
        obj = (Item[])new Object[3];
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public int size() {
        return pointer;
    }

    public void enqueue(Item item) {
        if (pointer == obj.length - 1)
            changeQueueSize(obj.length * 2);
        obj[pointer++] = item;
    }

    public Item sample() {
        int indexToReturn = StdRandom.uniform(pointer);
        return obj[indexToReturn];
    }

    private void changeQueueSize(int newSize) {
        Item[] temp = (Item[]) new Object[newSize];

        for(int i = 0; i < pointer; i++) {
            temp[i] = obj[i];
        }

        obj = temp;
    }

    public Item dequeue() {
        if (pointer == obj.length / 4) {
            changeQueueSize(obj.length / 2);
        }

        int indexToReturn = StdRandom.uniform(pointer);
        pointer--;
        exch(obj, indexToReturn, pointer);

        return obj[pointer];
    }

    private void exch(Item[] container, int i, int j) {
        Item temp = container[i];
        container[i] = container[j];
        container[j] = temp;
    }
}
