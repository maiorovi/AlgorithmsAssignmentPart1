import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

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

    @Test
    public void  twoBoardsIsEqualToItself() {
        int[][] boardPrototype = {{3,2,1}, {4,5,6}, {7,8,0}};

        Board board = new Board(boardPrototype);

        assertThat(board.equals(board)).isTrue();
    }

    @Test
    public void whenPassedNullToEqualsItReturnsFalse() {
        int[][] boardPrototype = {{3,2,1}, {4,5,6}, {7,8,0}};

        Board board = new Board(boardPrototype);

        assertThat(board.equals(null)).isFalse();
    }

    @Test
    public void whenObjectOfWrongInstancePassedToEqualsItReturnsFalse() {
        int[][] boardPrototype = {{3,2,1}, {4,5,6}, {7,8,0}};

        Board board = new Board(boardPrototype);

        assertThat(board.equals(new Object())).isFalse();
    }

    @Test
    public void whenBoardHaveDifferentDimensionsThenEqualsReturnsFalse() {
        int[][] boardPrototype = {{3,2,1}, {4,5,6}, {7,8,0}};
        int[][] boardPrototypeWithDifferenDimension = {{1,2}, {3,0}};

        Board board = new Board(boardPrototype);
        Board otherBoard = new Board(boardPrototypeWithDifferenDimension);

        assertThat(board.equals(otherBoard)).isFalse();
    }

    @Test
    public void twoBoardAreEqualWhenTheyHaveTheSamePositions() {
        int[][] boardPrototype = {{3,2,1}, {4,5,6}, {7,8,0}};

        Board board = new Board(boardPrototype);
        Board board1 = new Board(boardPrototype);

        assertThat(board.equals(board1)).isTrue();
    }

    @Test
    public void whenTwoBoardsHaveDifferentPositionsReturnsFalse() {
        int[][] boardPrototype = {{3,2,1}, {4,5,6}, {7,8,0}};
        int[][] boardPrototype1 = {{3,1,2}, {4,5,6}, {7,8,0}};


        Board board = new Board(boardPrototype);
        Board board1 = new Board(boardPrototype1);

        assertThat(board.equals(board1)).isFalse();
    }

    @Test
    public void returnsTwoNeighborsWhenInitialSearchNodeInTheCorner() {
        int[][] boardPrototype = {{0,2,1}, {4,5,6}, {7,8,3}};
        int[][] expectedBoardFirst = {{2,0,1}, {4,5,6}, {7,8,3}};
        int[][] expectedBoardSecond = {{4,2,1}, {0,5,6}, {7,8,3}};

        Board board = new Board(boardPrototype);

        Iterator<Board> it = board.neighbors().iterator();

        Board neighbor = it.next();

        assertThat(neighbor.equals(new Board(expectedBoardSecond))).isTrue();
        assertThat(it.next().equals(new Board(expectedBoardFirst))).isTrue();
    }

    @Test
    public void returnsCorrectNeighbordsOn4x4Board() {
        int[][] boardPrototype = {{1,2,3,4}, {5,6,7,8}, {9,10,12,0}, {13,14,11,15}};
        int[][] first = {{1,2,3,4}, {5,6,7,8}, {9,10,0,12}, {13,14,11,15}};
        int[][] second = {{1,2,3,4}, {5,6,7,0}, {9,10,12,8}, {13,14,11,15}};
        int[][] third = {{1,2,3,4}, {5,6,7,8}, {9,10,12,15}, {13,14,11,0}};

        Board board = new Board(boardPrototype);

        Iterator<Board> it = board.neighbors().iterator();

        Board neighbor = it.next();

        assertThat(neighbor).isEqualTo(new Board(first));
        assertThat(it.next()).isEqualTo(new Board(second));
        assertThat(it.next()).isEqualTo(new Board(third));
    }

    @Test
    public void returnsFourNeighborsWhenInitialSerachNodeInTheMiddle() {
        int[][] boardPrototype = {{5,2,1}, {4,0,6}, {7,8,3}};

        Board board = new Board(boardPrototype);

        ArrayList<Board> neighbors = (ArrayList) board.neighbors();

        assertThat(neighbors.size()).isEqualTo(4);
    }

    @Test
    public void returnsThreeNeighborsWhenInitialSarahNodeNearTheLeftSide() {
        int[][] boardPrototype = {{5,0,1}, {4,2,6}, {7,8,3}};

        Board board = new Board(boardPrototype);

        ArrayList<Board> neighbors = (ArrayList) board.neighbors();

        assertThat(neighbors.size()).isEqualTo(3);
    }

    @Test
    public void swapTwoAdjacentPositionsInTheFirstRowOfTheBoard() {
        int[][] boardPrototype = {{5,2,1}, {4,0,6}, {7,8,3}};
        int[][] expectedBoard = {{2,5,1}, {4,0,6}, {7,8,3}};

        Board board = new Board(boardPrototype);
        Board result = board.twin();

        assertThat(result.equals(new Board(expectedBoard))).isTrue();
    }

    @Test
    public void swapTwoAdjacentPositionsInTheSecondRow() {
        int[][] boardPrototype = {{5,0,1}, {4,2,6}, {7,8,3}};
        int[][] expectedBoard = {{5,0,1}, {2,4,6}, {7,8,3}};

        Board board = new Board(boardPrototype);
        Board result = board.twin();

        assertThat(result.equals(new Board(expectedBoard))).isTrue();
    }

    @Test
    public void printsTheBoardRepresentationAsAMatrix() {
        int[][] boardPrototype = {{5,0,1}, {4,2,6}, {7,8,3}};
        String expectedRepresentation = "3\n5 0 1\n4 2 6\n7 8 3";

        Board board = new Board(boardPrototype);

        assertThat(board.toString()).isEqualTo(expectedRepresentation);
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
