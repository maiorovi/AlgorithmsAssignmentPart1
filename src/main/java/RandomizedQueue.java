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
        obj[pointer++] = item;
    }

}
