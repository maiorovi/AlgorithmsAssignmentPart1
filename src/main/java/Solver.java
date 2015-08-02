import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Solver {

    private Comparator<Board> comparator = new Comparator<Board>() {
        @Override
        public int compare(Board one, Board two) {
            int result = one.manhattan() - two.manhattan();
            if (result > 0)
                return 1;
            else if (result == 0)
                return 0;
            else
                return -1;
        }
    };

    private Board initial;
    private boolean solvable;
    private short isSolvableCash = 0;
    private  MinPQ<Board> initialPq =  new MinPQ<Board>(comparator);
    private MinPQ<Board> twinPq = new MinPQ<Board>(comparator);
    private ArrayList<Board> visitedBoardsInitial = new ArrayList<Board>();
    private ArrayList<Board> visitedBoardsTwin = new ArrayList<Board>();
    private ArrayList<Board> solution = new ArrayList<Board>();

    private int numberOfMoves = -1;



    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        this.initial = initial;

        initialPq.insert(initial);
        visitedBoardsInitial.add(initial);
        twinPq.insert(initial.twin());
        visitedBoardsTwin.add(initial.twin());
        boolean status = false;

        while(!status) {
            status = runAStarAlgorithm();
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    private boolean runAStarAlgorithm() {
            Board min = initialPq.delMin();
            if (!min.equals(initial))
                solution.add(min);
            numberOfMoves++;

            if (min.isGoal()) {
                solvable = true;
                return true;
            }

            for(Board board : min.neighbors()) {
                if (!visitedBoardsInitial.contains(board)) {
                    initialPq.insert(board);
                    visitedBoardsInitial.add(board);
                }
            }

            Board twinMin = twinPq.delMin();

            if (twinMin.isGoal()) {
                solvable = false;
                return true;
            }

            for(Board board : twinMin.neighbors()) {
                if (!visitedBoardsTwin.contains(board)) {
                    twinPq.insert(board);
                    visitedBoardsTwin.add(board);
                }
            }

        return false;
    }

    public int moves() {
        if( solvable ){
            return numberOfMoves;
        }

        return -1;
    }

    public Iterable<Board> solution() {
        if(solvable) {
            return solution;
        }
        return null;
    }
}
