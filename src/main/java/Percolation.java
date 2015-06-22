public class Percolation {

    private final int VIRTUAL_TOP_INDEX = 0;
    private final int VIRTUAL_BOTTOM_INDEX = 1;

    private boolean isOpened[];
    private WeightedQuickUnionUF quickUnion;
    private int size;

    public Percolation(int N) {
        if (N < 0)
            throw new IllegalArgumentException();

        this.quickUnion = new WeightedQuickUnionUF(N * N + 2);
        this.size = N;
        isOpened = new boolean[N * N + 2];

        for (int i = 2; i < N + 2; i++) {
            quickUnion.union(VIRTUAL_TOP_INDEX, i);
        }

        for (int i = getIndexFromMatrixCoords(N+1, 1) - size; i < getIndexFromMatrixCoords(N+1, 1); i++) {
            quickUnion.union(VIRTUAL_BOTTOM_INDEX, i);
        }
    }

    public void open(int i, int j) {
        validateIndexes(i, j);

        int index = getIndexFromMatrixCoords(i, j);

        if (i == 1 && j == 1) {
            int index_bottom = getIndexFromMatrixCoords(i + 1, j);
            int index_right = getIndexFromMatrixCoords(i, j + 1);
            if (isOpen(i + 1, j))
                quickUnion.union(index, index_bottom);
            if (isOpen(i, j + 1))
                quickUnion.union(index, index_right);
        } else if (i == 1 && j == size) {
            int index_left = getIndexFromMatrixCoords(i, j - 1);
            int index_bottom = getIndexFromMatrixCoords(i + 1, j);
            if (isOpen(i, j - 1))
                quickUnion.union(index, index_left);
            if (isOpen(i + 1, j))
                quickUnion.union(index, index_bottom);
        } else if (i == 1) {
            int index_left = getIndexFromMatrixCoords(i, j - 1);
            int index_bottom = getIndexFromMatrixCoords(i + 1, j);
            int index_right = getIndexFromMatrixCoords(i, j + 1);
            if (isOpen(i,j - 1))
                quickUnion.union(index, index_left);
            if (isOpen(i+1, j))
                quickUnion.union(index, index_bottom);
            if (isOpen(i, j + 1))
                quickUnion.union(index, index_right);
        } else if (i == size && j == 1) {
            int index_top = getIndexFromMatrixCoords(i - 1, j);
            int index_right = getIndexFromMatrixCoords(i, j + 1);
            if (isOpen(i - 1, j))
                quickUnion.union(index, index_top);
            if (isOpen(i, j + 1))
                quickUnion.union(index, index_right);
        } else if (i == size && j == size) {
            int index_top = getIndexFromMatrixCoords(i - 1, j);
            int index_left = getIndexFromMatrixCoords(i, j - 1);
            if (isOpen(i-1,j))
                quickUnion.union(index, index_top);
            if (isOpen(i, j - 1))
                quickUnion.union(index, index_left);
        } else if (i == size) {
            int index_top = getIndexFromMatrixCoords(i - 1, j);
            int index_left = getIndexFromMatrixCoords(i, j - 1);
            int index_right = getIndexFromMatrixCoords(i, j + 1);
            if (isOpen(i - 1, j))
                quickUnion.union(index, index_top);
            if (isOpen(i, j - 1))
                quickUnion.union(index, index_left);
            if (isOpen(i, j + 1))
                quickUnion.union(index, index_right);
        } else if (j == 1 && i > 1) {
            int index_top = getIndexFromMatrixCoords(i - 1, j);
            int index_bottom = getIndexFromMatrixCoords(i + 1, j);
            int index_right = getIndexFromMatrixCoords(i, j + 1);
            if (isOpen(i - 1, j))
                quickUnion.union(index, index_top);
            if (isOpen(i+1, j))
                quickUnion.union(index, index_bottom);
            if (isOpen(i, j + 1))
                quickUnion.union(index, index_right);
        } else if (i > 1 && j == size) {
            int index_top = getIndexFromMatrixCoords(i - 1, j);
            int index_left = getIndexFromMatrixCoords(i, j - 1);
            int index_bottom = getIndexFromMatrixCoords(i + 1, j);
            if (isOpen(i - 1, j))
                quickUnion.union(index, index_top);
            if (isOpen(i, j - 1))
                quickUnion.union(index, index_left);
            if (isOpen(i + 1, j))
                quickUnion.union(index, index_bottom);
        } else if (i > 1 && j > 1) {
            int index_top = getIndexFromMatrixCoords(i - 1, j);
            int index_left = getIndexFromMatrixCoords(i, j - 1);
            int index_bottom = getIndexFromMatrixCoords(i + 1, j);
            int index_right = getIndexFromMatrixCoords(i, j + 1);
            if (isOpen(i - 1, j))
                quickUnion.union(index, index_top);
            if (isOpen(i, j - 1))
                quickUnion.union(index, index_left);
            if (isOpen(i+1, j))
                quickUnion.union(index, index_bottom);
            if (isOpen(i, j + 1))
                quickUnion.union(index, index_right);
        }

        isOpened[index] = true;

    }

    public boolean isOpen(int i, int j) {
        validateIndexes(i, j);
        int index = getIndexFromMatrixCoords(i, j);
        return isOpened[index];
    }

    public boolean isFull(int i, int j) {
        validateIndexes(i, j);
        int index = getIndexFromMatrixCoords(i, j);
        return isOpen(i, j) && quickUnion.connected(VIRTUAL_TOP_INDEX, index);
    }

    public boolean percolates() {
        return quickUnion.connected(VIRTUAL_TOP_INDEX, VIRTUAL_BOTTOM_INDEX);
    }

    private int getIndexFromMatrixCoords(int i, int j) {
        return size * (i - 1) + (j-1) + 2;
    }

    private void validateIndexes(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size)
            throw new IndexOutOfBoundsException();
    }
}
