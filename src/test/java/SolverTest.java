import org.junit.Test;

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
    public void returnsTrueWhenBoardIsNotSolvable() {
        int[][] boardPrototype = {{1,2,3,4}, {5,6,7,8}, {9,10,12,0}, {13,14,11,15}};
        Board board = new Board(boardPrototype);
        Solver solver = new Solver(board);

        assertThat(solver.isSolvable()).isTrue();
    }
}
