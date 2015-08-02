import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Solver {

    private Board initial;
    private short isSolvableCash = 0;
//    private ArrayList<Board> visitedBoards;
    private int numberOfMoves = -1;
    private ArrayList<Board> solution = new ArrayList<Board>();

    private Runnable initialBoard = new Runnable() {
        @Override
        public void run() {
            runAStarAlgorithm(initial);
        }
    };
    private Runnable twinBoard = new Runnable() {
        @Override
        public void run() {
            runAStarAlgorithm(initial.twin());
        }
    };

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

    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        this.initial = initial;
//        visitedBoards = new ArrayList<Board>();
    }

    public boolean isSolvable() {
        Thread initialBoardSearch = new Thread(initialBoard);
        Thread twinBoardSearch = new Thread(twinBoard);
        initialBoardSearch.start();
        twinBoardSearch.start();

        while (initialBoardSearch.isAlive() && twinBoardSearch.isAlive()) ;

        if (initialBoardSearch.isAlive()) {
            initialBoardSearch.interrupt();
            isSolvableCash = -1;
            return false;
        } else if (twinBoardSearch.isAlive()) {
            twinBoardSearch.interrupt();
            isSolvableCash = 1;
            return true;
        }

        return false;
    }

    private boolean runAStarAlgorithm(Board initial) {
        MinPQ<Board> queue = new MinPQ<Board>(comparator);
        int numberOfMoves = -1;
        ArrayList<Board> solution = new ArrayList<Board>();
        ArrayList<Board> visitedBoards = new ArrayList<Board>();


        queue.insert(initial);
        visitedBoards.add(initial);

        while(!queue.isEmpty()) {
            Board min = queue.delMin();
            if (!min.equals(initial))
                solution.add(min);
            numberOfMoves++;

            if (min.isGoal()) {
                this.numberOfMoves = numberOfMoves;
                this.solution = solution;
                return true;
            }

            for(Board board : min.neighbors()) {
                if (!visitedBoards.contains(board)) {
                    queue.insert(board);
                    visitedBoards.add(board);
                }
            }
        }

        this.numberOfMoves = numberOfMoves;
        this.solution = solution;
        return false;
    }

    public int moves() {
        if (isSolvableCash == 1) {
            return numberOfMoves;
        } else if(isSolvable()) {
            runAStarAlgorithm(initial);
            return numberOfMoves;
        }

        return -1;
    }

    public Iterable<Board> solution() {
        if (isSolvableCash == 1) {
            return solution;
        } else if(isSolvable()) {
            runAStarAlgorithm(initial);
            return solution;
        }

        return null;
    }
}
