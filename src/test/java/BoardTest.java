import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    private int[][] boardPrototype;

    @Test
    public void returnDimensionOfBoard() {
        boardPrototype = createPrototypeBoard(3, 3);
        Board board = new Board(boardPrototype);

        assertThat(board.dimension()).isEqualTo(3);
    }

    @Test
    public void returnDimensionTwoWhenDimensionIsTwo() {
        boardPrototype = createPrototypeBoard(2, 2);
        Board board = new Board(boardPrototype);

        assertThat(board.dimension()).isEqualTo(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenLengthAndHightOfArrayIsNotEqual() {
        boardPrototype = createPrototypeBoard(3, 2);
        Board board = new Board(boardPrototype);
    }

    @Test
    public void returnsZeroWhenAllBlockInPlace() {
        int[][] boardPrototype = {{1,2,3}, {4,5,6}, {7,8,0}};
        Board board = new Board(boardPrototype);

        assertThat(board.hamming()).isEqualTo(0);
    }

    @Test
    public void returnsThatTwoBlocksIsOutOfPlace() {
        int[][] boardPrototype = {{1,2,3}, {4,5,0}, {7,8,6}};
        Board board = new Board(boardPrototype);

        assertThat(board.hamming()).isEqualTo(1);
    }

    @Test
    public void returnsThatAllBlocksIsOutOfPlace() {
        int[][] boardPrototype = {{0,1,2}, {3,7,4}, {8,5,6}};
        Board board = new Board(boardPrototype);

        assertThat(board.hamming()).isEqualTo(8);
    }

    @Test
    public void returnsThatFourBlocksIsOutOfPlace() {
        int[][] boardPrototype = {{1,3,2}, {5,4,6}, {7,8,0}};

        Board board = new Board(boardPrototype);

        assertThat(board.hamming()).isEqualTo(4);
    }

    @Test
    public void returnsManhattanDistancesZeroWhenAllBlocksInPosition() {
        int[][] boardPrototype = {{1,2,3}, {4,5,6}, {7,8,9}};

        Board board = new Board(boardPrototype);

        assertThat(board.manhattan()).isEqualTo(0);
    }

    @Test
    public void returnsOneWhenOneBlocksIsOutOfPlaceAndNeedsOneMoveToPutThemInPlace() {
        int[][] boardPrototype = {{1,2,3}, {4,5,6}, {7,0,8}};

        Board board = new Board(boardPrototype);

        assertThat(board.manhattan()).isEqualTo(1);
    }

    @Test
    public void returnsThreeWhenFewBlocksIsOutOfPlace() {
        int [][] boardPrototype = {{0, 1, 3}, {4, 2, 5}, {7,8,6}};

        Board board = new Board(boardPrototype);

        assertThat(board.manhattan()).isEqualTo(4);
    }

    @Test
    public void computesManhattanDistanceOnRandomBoard() {
        int[][] boardPrototype = {{8,1,3}, {4, 0, 2}, {7,6,5}};

        Board board = new Board(boardPrototype);

        assertThat(board.manhattan()).isEqualTo(10);
    }

    @Test
    public void isGoalReturnsTrueWhenBoardInCorrectPosition() {
        int[][] boardPrototype = {{1,2,3}, {4,5,6}, {7,8,0}};

        Board board = new Board(boardPrototype);

        assertThat(board.isGoal()).isTrue();
    }

    @Test
    public void isGoalReturnsFalseWhenBoardInWrongPosition() {
        int[][] boardPrototype = {{3,2,1}, {4,5,6}, {7,8,0}};

        Board board = new Board(boardPrototype);

        assertThat(board.isGoal()).isFalse();
    }

    private int[][] createPrototypeBoard(int width, int height) {
        int[][] board = new int[height][width];

        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                board[i][j] = i + j;
            }
        }

        return board;
    }
}
