public class Board {

    private int[][] board;

    public Board(int board[][]) {
        if (board.length != board[0].length)
            throw new IllegalArgumentException();

        this.board = board;
    }

    public int dimension() {
        return board.length;
    }

    public int hamming() {
        int priority = 0;

        for(int i = 0, counter = 1; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != counter && board[i][j] != 0) {
                    priority++;
                }
                counter++;
            }
        }

        return priority;
    }

    public int manhattan() {
        int priority = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != computeExpectedValue(i, j) + 1 && board[i][j] != 0) {
                    priority = priority + Math.abs(j - getCorrectX(board[i][j])) +
                            Math.abs(i - getCorrectY(board[i][j]));
                }
            }
        }
        return priority;
    }

    public boolean isGoal() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != computeExpectedValue(i,j) + 1 && !isLastAndEqualZero(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isLastAndEqualZero(int i, int j) {
        if (dimension() - i == 1 && dimension() - j == 1 && board[i][j] == 0)
            return true;

        return false;
    }

    private int computeExpectedValue(int i, int j) {
        return i * dimension() + j;
    }

    private int getCorrectX(int value) {
        return (value - 1) % dimension();
    }

    private int getCorrectY(int value) {
        return (value - 1) / dimension();
    }
}
