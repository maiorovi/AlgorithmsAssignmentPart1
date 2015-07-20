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
        int outOfPlaceBlocks = 0;

        for(int i = 0, counter = 1; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != counter && board[i][j] != 0) {
                    outOfPlaceBlocks++;
                }
                counter++;
            }
        }

        return outOfPlaceBlocks;
    }
}
