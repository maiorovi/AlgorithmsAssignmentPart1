import java.util.ArrayList;

public class Board {

    private final int[][] board;

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

    public Iterable<Board> neighbors() {
        int index = findZeroIndex();
//        int xValue = index % dimension();
        return getNeighboringBoards(getRow(index), getCol(index));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Board))
            return false;

        Board other = (Board) obj;

        if (other.dimension() != this.dimension())
            return false;

        for(int i = 0; i < dimension(); i++) {
            for(int j = 0; j < dimension(); j++) {
                if (this.board[i][j] != other.board[i][j]) {
                    return false;
                }
            }
        }

        return true;
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

    private int findZeroIndex() {
        int value = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j]  == 0) {
                    value = computeExpectedValue(i, j);
                }
            }
        }
        return value;
    }

    private ArrayList<Board> getNeighboringBoards(int x, int y) {
        ArrayList<Board> boards = new ArrayList<Board>();

        if(y - 1 >= 0) {
            boards.add(swapAndReturnNewBoard(x, y, x, y - 1));
        }

        if (x - 1 >= 0 ) {
            boards.add(swapAndReturnNewBoard(x, y, x - 1, y));
        }

        if ( x + 1 < board.length ) {
            boards.add(swapAndReturnNewBoard(x, y, x + 1, y));
        }

        if (y + 1 < board.length) {
            boards.add(swapAndReturnNewBoard(x, y, x, y + 1));
        }

        return boards;
    }

    private Board swapAndReturnNewBoard(int x, int y, int x1, int y1) {
        int[][] newBoard = new int[board.length][board.length];

        for (int i = 0; i < board.length; i++) {
            for(int j = 0; j <board.length; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        int temp = newBoard[x][y];
        newBoard[x][y] = newBoard[x1][y1];
        newBoard[x1][y1] = temp;

        return new Board(newBoard);
    }

    public Board twin() {
        int startPosition = 0;
        while (board[getRow(startPosition)][getCol(startPosition)] == 0 || board[getRow(startPosition)][getCol(startPosition) + 1] == 0) {
            if (getCol(startPosition) == dimension() - 2) {
                startPosition++;
            }
            startPosition++;
        }
        return swapAndReturnNewBoard(getRow(startPosition), getCol(startPosition), getRow(startPosition), getCol(startPosition)+1);
    }

    public String toString() {
        StringBuilder representationBuilder = new StringBuilder();
        representationBuilder.append(board.length);
        representationBuilder.append("\n");
        for (int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                representationBuilder.append(board[i][j]);
                appendSymbolToRepresentationButNotBeforeLastSymbol(representationBuilder, j, " ");
            }
            appendSymbolToRepresentationButNotBeforeLastSymbol(representationBuilder, i, "\n");
        }
        return representationBuilder.toString();
    }

    private void appendSymbolToRepresentationButNotBeforeLastSymbol(StringBuilder representationBuilder, int i, String str) {
        if (i != board.length - 1) {
            representationBuilder.append(str);
        }
    }

    private int getRow(int position) {
        return position / dimension();
    }

    private int getCol(int position) {
        return position % dimension();
    }
}
