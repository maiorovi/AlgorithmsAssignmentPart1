import org.junit.Test;
import org.mockito.internal.matchers.Null;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class SolverTest {

    @Test
    public void returnTrueWhenGivenBoardIsSolvable() {
        int[][] boardPrototype = {{1,2,3}, {4,5,6}, {7,8,0}};
        Board board = new Board(boardPrototype);

        Solver solver = new Solver(board);

        assertThat(solver.isSolvable()).isTrue();
    }

    @Test
    public void returnsFalseWhenGivenBoardIsNotSolvable() {
        int[][] boardPrototype = {{1,2,3}, {4,5,6}, {8,7,0}};
        Board board = new Board(boardPrototype);
        Solver solver = new Solver(board);

        assertThat(solver.isSolvable()).isFalse();
    }

    @Test
    public void returnsFalseWhenBoardIsNotSolvable() {
        int[][] boardPrototype = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,15,14, 0}};
        Board board = new Board(boardPrototype);
        Solver solver = new Solver(board);

        assertThat(solver.isSolvable()).isFalse();
    }

    @Test
    public void returnsTrueWhenBoardIsSolvable() {
        int[][] boardPrototype = {{1,2,3,4}, {5,6,7,8}, {9,10,12,0}, {13,14,11,15}};
        Board board = new Board(boardPrototype);
        Solver solver = new Solver(board);

        assertThat(solver.isSolvable()).isTrue();
    }

    @Test
    public void calculateAmountOfMovesRequiredToBuildTheFinalBoard() {
        int[][] boardPrototype = {{0,1,3}, {4,2,5}, {7,8,6}};
        Board board = new Board(boardPrototype);
        Solver solver = new Solver(board);

        assertThat(solver.moves()).isEqualTo(4);
    }

    @Test
    public void whenBoardIsUnsolvableThenReturnsMinusOne() {
        int[][] boardPrototype = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,15,14, 0}};
        Board board = new Board(boardPrototype);
        Solver solver = new Solver(board);

        assertThat(solver.moves()).isEqualTo(-1);
    }

    @Test
    public void returnsNullWhenBoardIsUnSolvable() {
        int[][] boardPrototype = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,15,14, 0}};
        Board board = new Board(boardPrototype);
        Solver solver = new Solver(board);

        assertThat(solver.solution()).isNull();
    }

    @Test
    public void returnSolutionWhenBoardIsSolvable() {
        int[][] boardPrototype = {{0,1,3}, {4,2,5}, {7,8,6}};
        Board board = new Board(boardPrototype);
        Solver solver = new Solver(board);
        ArrayList<Board> solution = (ArrayList<Board>)solver.solution();

        assertThat(solution.size()).isEqualTo(5);
    }

    @Test(expected = NullPointerException.class)
    public void constructorsThrowsNullPointerExceptionWhenArgumentIsNull() {
        Solver solver = new Solver(null);
    }
}
