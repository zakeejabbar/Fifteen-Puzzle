// Zakee Jabbar (zjabba2)
// CS 342
// Project 2


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// BoardListener for the buttons on the board
public class BoardListener implements ActionListener {
    private Board board;
    private Rules rules;
    private GraphicsBoard graphicsBoard;
    private Moves allMoves;

    // Constructor
    public BoardListener(Board b, Rules r, GraphicsBoard gB, Moves m) {
        board = b;
        rules = r;
        graphicsBoard = gB;
        allMoves = m;
    }

    // Does the actions for a click
    public void actionPerformed(ActionEvent event) {
        JButton clicked = (JButton) event.getSource();
        int clickedVal = Integer.parseInt(clicked.getText());
        int[] pos = board.getPos(clickedVal);

        // If clicked on blank do nothing
        if (clickedVal == 16) {
            return;
        }

        // Check if the free spot is below
        if (pos[0] + 1 < 4 && board.getPosValue(pos[0] + 1, pos[1]) == 16) {
            board.swap(pos[0], pos[1], pos[0] + 1, pos[1]);
            graphicsBoard.swap(pos[0], pos[1], pos[0] + 1, pos[1]);
            graphicsBoard.incrementNumMoves();
            SingleMove move = new SingleMove(pos[0], pos[1], pos[0] + 1, pos[1]);
            allMoves.push(move);

        }
        // Check if the free spot is above
        else if (pos[0] - 1 >= 0 && board.getPosValue(pos[0] - 1, pos[1]) == 16) {
            board.swap(pos[0], pos[1], pos[0] - 1, pos[1]);
            graphicsBoard.swap(pos[0], pos[1], pos[0] - 1, pos[1]);
            graphicsBoard.incrementNumMoves();
            SingleMove move = new SingleMove(pos[0], pos[1], pos[0] - 1, pos[1]);
            allMoves.push(move);
        }
        // Check if the free spot is to the right
        else if (pos[1] + 1 < 4 && board.getPosValue(pos[0], pos[1] + 1) == 16) {
            board.swap(pos[0], pos[1], pos[0], pos[1] + 1);
            graphicsBoard.swap(pos[0], pos[1], pos[0], pos[1] + 1);
            graphicsBoard.incrementNumMoves();
            SingleMove move = new SingleMove(pos[0], pos[1], pos[0], pos[1] + 1);
            allMoves.push(move);
        }
        // Check if the free spot is to the left
        else if (pos[1] - 1 >= 0 && board.getPosValue(pos[0], pos[1] - 1) == 16) {
            board.swap(pos[0], pos[1], pos[0], pos[1] - 1);
            graphicsBoard.swap(pos[0], pos[1], pos[0], pos[1] - 1);
            graphicsBoard.incrementNumMoves();
            SingleMove move = new SingleMove(pos[0], pos[1], pos[0], pos[1] - 1);
            allMoves.push(move);
        }
        // No moves possible do nothing
        else
        {
            return;
        }

        // Update the number of moves
        graphicsBoard.setNumMovesLabel();
        // Check if game is complete and if it is display in popup window
        if (rules.isGameComplete() == true) {
            graphicsBoard.makeChanges(3,3,16);
            JOptionPane.showMessageDialog(null,
                    "Congratulations! You solved the puzzle!\n" + "Number of moves taken: " + graphicsBoard.getNumMoves()
                            + "\n" + "Puzzle Complexity: " + graphicsBoard.getComplexity() + "\n",
                    "Puzzle Solved", JOptionPane.PLAIN_MESSAGE);
            graphicsBoard.setNumMoves(0); // Reset number of moves
            graphicsBoard.setNumMovesLabel(); // Set the label
            allMoves.clear(); // Clear the moves we had stored
            return;
        }
    }
}