import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Solver {

    private Board initial;
    private ArrayList<Board> visitedBoards;
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
        this.initial = initial;
        visitedBoards = new ArrayList<Board>();
    }

    public boolean isSolvable() {
        Thread initialBoardSearch = new Thread(initialBoard);
        Thread twinBoardSearch = new Thread(twinBoard);
        initialBoardSearch.start();
        twinBoardSearch.start();

        while (initialBoardSearch.isAlive() && twinBoardSearch.isAlive()) ;

        if (initialBoardSearch.isAlive()) {
            initialBoardSearch.interrupt();
            return false;
        } else if (twinBoardSearch.isAlive()) {
            twinBoardSearch.interrupt();
            return true;
        }

        return false;
    }

    private boolean runAStarAlgorithm(Board initial) {
        MinPQ<Board> queue = new MinPQ<Board>(comparator);
        queue.insert(initial);
        visitedBoards.add(initial);

        while(!queue.isEmpty()) {
            Board min = queue.delMin();
            if (min.isGoal()) {
                return true;
            }

            for(Board board : min.neighbors()) {
                if (!visitedBoards.contains(board)) {
                    queue.insert(board);
                    visitedBoards.add(board);
                }
            }
        }

        return false;
    }
}
