// Zakee Jabbar (zjabba2)
// CS 342
// Project 2


import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


// Makes the menus for the game
public class Menus extends JMenuBar
{
    private GraphicsBoard graphicsBoard;
    private Board internalBoard;
    private Moves allMoves;
    private Moves autoSolveMoves;
    private Timer undoTimer;
    private Timer solveTimer;

    // Constructor
    public Menus(GraphicsBoard gB, Board b, Moves m)
    {
        super();
        graphicsBoard = gB;
        internalBoard = b;
        allMoves = m;
        JMenu fileMenu = new JMenu( "File" );
        fileMenu.setMnemonic( 'F' );

        // Timer for undoAll
        undoTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                SingleMove move = allMoves.pop();
                if (move == null) {
                    undoTimer.stop();
                } else {
                    int[] coord1 = move.getCoord1();
                    int[] coord2 = move.getCoord2();
                    internalBoard.swap(coord1[0], coord1[1], coord2[0], coord2[1]);
                    graphicsBoard.swap(coord1[0], coord1[1], coord2[0], coord2[1]);
                    graphicsBoard.decrementNumMoves();
                    graphicsBoard.setNumMovesLabel();

                }
            }
        });

        // Timer for moves of solve
        solveTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                SingleMove move = autoSolveMoves.pop();
                if (move == null) {
                    solveTimer.stop();
                    graphicsBoard.makeChanges(3,3,16);
                    JOptionPane.showMessageDialog(null,
                            "Puzzle Solver has solved the puzzle\n" + "Number of moves taken: " + graphicsBoard.getNumMoves()
                                    + "\n" + "Puzzle Complexity: " + graphicsBoard.getComplexity() + "\n",
                            "Puzzle Solved", JOptionPane.PLAIN_MESSAGE);
                    graphicsBoard.setNumMoves(0);
                    graphicsBoard.setNumMovesLabel();
                    allMoves.clear();
                    autoSolveMoves.clear();
                } else {
                    int[] coord1 = move.getCoord1();
                    int[] coord2 = move.getCoord2();
                    internalBoard.swap(coord1[0], coord1[1], coord2[0], coord2[1]);
                    graphicsBoard.swap(coord1[0], coord1[1], coord2[0], coord2[1]);
                    graphicsBoard.incrementNumMoves();
                    graphicsBoard.setNumMovesLabel();

                }
            }
        });

        // set up About... menu item
        JMenuItem aboutItem = new JMenuItem( "About..." );
        aboutItem.setMnemonic( 'A' );
        fileMenu.add( aboutItem );
        aboutItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects About...
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( Menus.this,
                                "Author: Zakee Jabbar (zjabba2)\n" +
                                        "When: 9/25/17\n" + "Why: CS 342 Project 2\n"
                                        + "Extra Credit Attempted: Using pictures as numbers\n",
                                "About", JOptionPane.PLAIN_MESSAGE );
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        // set up Exit menu item
        JMenuItem exitItem = new JMenuItem( "Exit" );
        exitItem.setMnemonic( 'x' );
        fileMenu.add( exitItem );
        exitItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // terminate application when user clicks Exit
                    public void actionPerformed( ActionEvent event )
                    {
                        System.exit( 0 );
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        // How to play menu item
        JMenuItem howItem = new JMenuItem( "How to play..." );
        aboutItem.setMnemonic( 'H' );
        fileMenu.add( howItem );
        howItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects how to play
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( Menus.this,
                                "File -> Quit: To Quit Game\n" + "File -> About: Some quick information\n" +
                                        "File -> How to play: To reach this\n" + "Actions -> Shuffle: To shuffle the puzzle\n" +
                                        "Actions -> Undo Once: Undos one move\n" + "Actions -> Undo All: Undo all moves\n" +
                                        "Actions -> Solve: To solve the puzzle and see how to solve\n" +
                                        "Goal: Get the numbers back in order after the puzzle is randomized",
                                "How to play", JOptionPane.PLAIN_MESSAGE );
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        add( fileMenu );

        // Menu for the actions
        JMenu actionsMenu = new JMenu( "Actions" );
        fileMenu.setMnemonic( 'C' );

        // set up Shuffle menu item
        JMenuItem shuffleItem = new JMenuItem( "Shuffle" );
        shuffleItem.setMnemonic( 'S' );
        actionsMenu.add( shuffleItem );
        shuffleItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // shuffles the board with a solvable puzzle
                    public void actionPerformed( ActionEvent event )
                    {
                        Integer[] a = new Integer[] { 1, 2, 3, 4, 5, 6, 7,8,9,10,11,12,13,14,15};
                        Integer[] shuffled;
                        int inversionCount;

                        while(true)
                        {
                            ArrayList<Integer> myList = new ArrayList<Integer>(Arrays.asList(a));
                            Collections.shuffle(myList);
                            shuffled = myList.toArray(new Integer[myList.size()]);
                            inversionCount = isPossible(shuffled);
                            if(inversionCount % 2 == 0)
                            {
                                break;
                            }
                            else
                            {
                                continue;
                            }
                        }

                        int shuffleCount = 0;
                        for(int i = 0; i < 4; i++)
                        {

                            for(int j = 0; j < 4; j++)
                            {
                                if(i == 3 && j == 3)
                                {
                                    graphicsBoard.makeBlank(i, j);
                                    internalBoard.setPosValue(i, j, 16);
                                }
                                else
                                {
                                    internalBoard.setPosValue(i, j, shuffled[shuffleCount]);
                                    graphicsBoard.makeChanges(i, j, shuffled[shuffleCount]);
                                }
                                shuffleCount++;

                            }
                        }

                        graphicsBoard.setComplexityLabel(inversionCount);
                        graphicsBoard.setNumMoves(0);
                        graphicsBoard.setNumMovesLabel();
                        allMoves.clear();

                    }

                }  // end anonymous inner class

        ); // end call to addActionListener



        // set up Undo menu item
        JMenuItem undoItem = new JMenuItem( "Undo" );
        undoItem.setMnemonic( 'U' );
        actionsMenu.add( undoItem );
        undoItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // Undos a single move
                    public void actionPerformed( ActionEvent event )
                    {
                        SingleMove move = allMoves.pop();
                        if(move == null)
                        {
                            System.out.println("No moves to undo!");
                        }
                        else
                        {
                            int [] coord1 = move.getCoord1();
                            int [] coord2 = move.getCoord2();
                            internalBoard.swap(coord1[0], coord1[1], coord2[0], coord2[1]);
                            graphicsBoard.swap(coord1[0], coord1[1], coord2[0], coord2[1]);
                            graphicsBoard.decrementNumMoves();
                            graphicsBoard.setNumMovesLabel();

                        }
                    }

                }  // end anonymous inner class

        ); // end call to

        // set up Undo All menu item
        JMenuItem undoAllItem = new JMenuItem( "Undo All" );
        undoAllItem.setMnemonic( 'L' );
        actionsMenu.add( undoAllItem );
        undoAllItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // Undos all moves when clicked
                    public void actionPerformed( ActionEvent event ) {
                        undoTimer.start();

                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        // set up Solve menu item
        JMenuItem solveItem = new JMenuItem( "Solve" );
        solveItem.setMnemonic( 'V' );
        actionsMenu.add( solveItem );
        solveItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // Solves the puzzle when clicked
                    public void actionPerformed( ActionEvent event )
                    {
                        if(internalBoard.isWinnerString(internalBoard.toString())) {
                            System.out.println("Puzzle is already solved!");
                            return;
                        }
                        Moves m = autoSolve(internalBoard);
                        autoSolveMoves = m;
                        if(m == null)
                        {
                            return;
                        }
                        graphicsBoard.setNumMoves(0);
                        graphicsBoard.setNumMovesLabel();
                        solveTimer.start();
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        add(actionsMenu);
    }

    // Returns the inversion count
    public int isPossible(Integer[] input)
    {
        int inversions = 0;
        for(int i = 0; i < 15; i++)
        {
            for(int j = i + 1; j < 15; j++)
            {
                if(input[i] > input[j])
                {
                    inversions++;
                }
            }
        }
        return inversions;

    }

    // Runs the auto solve bfs
    public Moves autoSolve(Board b)
    {
        long startTime = System.currentTimeMillis();
        long waitTime = 600000;
        long endTime = startTime + waitTime;
        boolean stopped = false;
        Map visited = new HashMap<String, Integer>();
        Queue<Tuple> visitQueue  = new ArrayDeque<>();
        Tuple first = new Tuple(b, null);
        visitQueue.add(first);
        visited.put(first.getBoard(), 1);

        while(visitQueue.size() != 0)
        {
            // If its been running for more than 10 minutes
            if(System.currentTimeMillis() > endTime)
            {
                stopped = true;
                break;
            }
            Tuple curr = visitQueue.remove();
            if(b.isWinnerString(curr.getBoard()))
            {
                return curr.getMoves();
            }
            Board boardTemp = new Board(curr.getBoard());
            int[] blankCoord = boardTemp.getBlankCoord();
            Moves movesTemp = curr.getMoves();
            // Check if below the blank space is in range
            if(blankCoord[0] + 1 < 4)
            {
                Board downTemp = new Board(curr.getBoard());
                downTemp.swap(blankCoord[0], blankCoord[1], blankCoord[0] + 1, blankCoord[1]);
                if(visited.containsKey(downTemp.toString()))
                {

                }
                else
                {
                    Moves downMoves = movesTemp.duplicate(movesTemp);
                    SingleMove curMove = new SingleMove(blankCoord[0], blankCoord[1], blankCoord[0] + 1, blankCoord[1]);
                    downMoves.addLast(curMove);
                    Tuple upTup = new Tuple(downTemp, downMoves);
                    visitQueue.add(upTup);
                    visited.put(downTemp.toString(), 1);
                }

            }
            // Check if above the blank space is in range
            if(blankCoord[0] - 1 >= 0)
            {
                Board upTemp = new Board(curr.getBoard());
                upTemp.swap(blankCoord[0], blankCoord[1], blankCoord[0] - 1, blankCoord[1]);
                if(visited.containsKey(upTemp.toString()))
                {

                }
                else
                {
                    Moves upMoves = movesTemp.duplicate(movesTemp);
                    SingleMove curMove = new SingleMove(blankCoord[0], blankCoord[1], blankCoord[0] - 1, blankCoord[1]);
                    upMoves.addLast(curMove);
                    Tuple upTup = new Tuple(upTemp, upMoves);
                    visitQueue.add(upTup);
                    visited.put(upTemp.toString(), 1);
                }

            }
            // Check if to the right of the blank space is in range
            if(blankCoord[1] + 1 < 4)
            {
                Board rightTemp = new Board(curr.getBoard());
                rightTemp.swap(blankCoord[0], blankCoord[1], blankCoord[0], blankCoord[1] + 1);
                if(visited.containsKey(rightTemp.toString()))
                {

                }
                else
                {
                    Moves rightMoves = movesTemp.duplicate(movesTemp);
                    SingleMove curMove = new SingleMove(blankCoord[0], blankCoord[1], blankCoord[0], blankCoord[1] + 1);
                    rightMoves.addLast(curMove);
                    Tuple upTup = new Tuple(rightTemp, rightMoves);
                    visitQueue.add(upTup);
                    visited.put(rightTemp.toString(), 1);
                }


            }
            // Check if to the left of the blank space is in range
            if(blankCoord[1] - 1 >= 0)
            {
                Board leftTemp = new Board(curr.getBoard());
                leftTemp.swap(blankCoord[0], blankCoord[1], blankCoord[0], blankCoord[1] - 1);
                if(visited.containsKey(leftTemp.toString()))
                {

                }
                else
                {
                    Moves leftMoves = movesTemp.duplicate(movesTemp);
                    SingleMove curMove = new SingleMove(blankCoord[0], blankCoord[1], blankCoord[0], blankCoord[1] - 1);
                    leftMoves.addLast(curMove);
                    Tuple upTup = new Tuple(leftTemp, leftMoves);
                    visitQueue.add(upTup);
                    visited.put(leftTemp.toString(), 1);
                }

            }


        }
        // If it has been 10 minutes
        if(stopped == true)
        {
            JOptionPane.showMessageDialog(null,
                    "Took too long to solve! Greater than 10 minutes\n",
                    "Too long timeout!", JOptionPane.PLAIN_MESSAGE);

        }
        return null;

    }

}