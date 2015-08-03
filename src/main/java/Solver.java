import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

public class Solver {

    private Comparator<SearchNode> comparator = new Comparator<SearchNode>() {
        @Override
        public int compare(SearchNode one, SearchNode two) {
            int result = (one.hammingPriority()) - (two.hammingPriority());
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
    private SearchNode  goal = null;
    private  MinPQ<SearchNode> initialPq =  new MinPQ<SearchNode>(comparator);
    private MinPQ<SearchNode> twinPq = new MinPQ<SearchNode>(comparator);
    private ArrayList<Board> visitedBoardsInitial = new ArrayList<Board>();
    private ArrayList<Board> visitedBoardsTwin = new ArrayList<Board>();
//    private ArrayList<Board> solution = new ArrayList<Board>();

    private int numberOfMoves = 0;



    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        this.initial = initial;

        initialPq.insert(new SearchNode(0, initial, null));
        visitedBoardsInitial.add(initial);
        twinPq.insert(new SearchNode(0, initial.twin(), null));
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
        SearchNode node = initialPq.delMin();
        Board min = node.board;

            if (min.isGoal()) {
                goal = node;
                solvable = true;
                return true;
            }

            for(Board board : min.neighbors()) {
                if (!visitedBoardsInitial.contains(board)) {
                    initialPq.insert(new SearchNode(node.movesAmount+1, board, node));
                    visitedBoardsInitial.add(board);
                }
            }

            SearchNode twinNode = twinPq.delMin();
            Board twinMin = twinNode.board;

            if (twinMin.isGoal()) {
                solvable = false;
                return true;
            }

            for(Board board : twinMin.neighbors()) {
                if (!visitedBoardsTwin.contains(board)) {
                    twinPq.insert(new SearchNode(0, board, twinNode));
                    visitedBoardsTwin.add(board);
                }
            }

        return false;
    }

    public int moves() {
        if( solvable ){
            return goal.movesAmount;
        }

        return -1;
    }

    public Iterable<Board> solution() {
        if(solvable) {
            SearchNode temp = goal;
            LinkedList<Board> solutionBoards = new LinkedList<>();
            while( temp.prev != null) {
                solutionBoards.addFirst(temp.board);
                temp = temp.prev;
            }
            solutionBoards.addFirst(temp.board);
            return solutionBoards;
        }
        return null;
    }

    private class SearchNode {
        private int movesAmount;
        private Board board;
        private SearchNode prev;

        public SearchNode(int movesAmount, Board board, SearchNode prev) {
            this.movesAmount = movesAmount;
            this.board = board;
            this.prev = prev;
        }

        private int hammingPriority() {
            return movesAmount + board.manhattan();
        }
    }
}
