// Zakee Jabbar (zjabba2)
// CS 342
// Project 2



import javax.swing.*;
import java.awt.*;

// Makes the 4x4 Game Board and deals with the clicks of the user
public class GraphicsBoard
{
    private JFrame window;
    private JButton button[][];
    private JPanel board;
    private int numMoves;
    private int complexity;
    private JLabel countLabel;
    private JLabel complexityLabel;
    private Board internalBoard;
    private Rules myRules;
    private Menus menu;
    private Moves allMoves;
    private Icon pics[]; // Array that stores the images

    public GraphicsBoard()
    {
        window = new JFrame("15-puzzle game by Zakee Jabbar"); // JFrame that holds everything
        button = new JButton[4][4]; // JButtons that store the numbers
        board = new JPanel(); // Panel for the buttons
        numMoves = 0;
        complexity = 0;
        pics = new Icon[17];

        internalBoard = new Board(); // Makes the internal board
        myRules = new Rules(internalBoard); // Makes the rules object
        allMoves = new Moves(); // Stores the moves
        menu = new Menus(this, internalBoard, allMoves); // Makes the menus

        // Change the visual layout of the frame/panel
        window.setSize(500, 600);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        board.setLayout(new GridLayout(5, 4));
        board.setBackground(Color.black);
        window.setJMenuBar(menu);

        int value = 1;
        for(int i = 0; i < 16; i++)
        {
            pics[value] = new ImageIcon("src/" +value +".jpg");
            value++;
        }
        // Initialize the board with 1-15
        value = 1;
        for (int x = 0; x < 4; x++)
        {
            for(int y = 0; y < 4; y++) {
                if(x == 3 && y == 3)
                {
                    continue;
                }
                button[x][y] = new JButton(value  + "");
                window.add(button[x][y]);
                button[x][y].setBackground(Color.white);
                button[x][y].setForeground(Color.white);
                button[x][y].setIcon(pics[value]); // Set icon
                board.add(button[x][y]);
                internalBoard.setPosValue(x, y, value);
                value++;
            }


        }

        // Make the 16th(3,3) element blank
        button[3][3] = new JButton( 16 + "");
        window.add(button[3][3]);
        button[3][3].setBackground(Color.white);
        button[3][3].setForeground(Color.white);
        board.add(button[3][3]);
        internalBoard.setPosValue(3, 3, 16);

        // Add buttons to the board
        window.add(board, BorderLayout.CENTER);


        // Add the listeners for the buttons
        BoardListener bl = new BoardListener(internalBoard, myRules, this, allMoves);
        for (int x = 0; x < 4; x++)
        {
            for(int y = 0; y < 4; y++)
            {
                button[x][y].addActionListener(bl);
            }
        }

        // Set up the labels for count and complexity
        countLabel = new JLabel("# of moves: " + numMoves);
        complexityLabel = new JLabel("Complexity: " + complexity);
        countLabel.setBackground(Color.black);
        countLabel.setForeground(Color.white);
        complexityLabel.setBackground(Color.black);
        complexityLabel.setForeground(Color.white);
        JLabel blankLabel = new JLabel(" ");
        blankLabel.setBackground(Color.black);
        blankLabel.setForeground(Color.black);
        board.add(countLabel);
        board.add(blankLabel);
        board.add(complexityLabel);

        // Make frame visible
        window.setVisible(true);

    }

    // Swaps to buttons on the board
    public void swap(int xPos1, int yPos1, int xPos2, int yPos2)
    {
        int pos1 = Integer.parseInt(button[xPos1][yPos1].getText());
        int pos2 = Integer.parseInt(button[xPos2][yPos2].getText());
        if(pos1 == 16)
        {
            makeBlank(xPos2, yPos2);
            makeChanges(xPos1, yPos1, pos2);
        }
        if(pos2 == 16)
        {
            makeBlank(xPos1, yPos1);
            makeChanges(xPos2, yPos2, pos1);
        }
    }

    // Makes a position blank
    public void makeBlank(int xPos, int yPos)
    {
        button[xPos][yPos].setBackground(Color.white);
        button[xPos][yPos].setForeground(Color.white);
        button[xPos][yPos].setText(16 + "");
        button[xPos][yPos].setIcon(null);
    }

    // Updates a specific position with a specific value
    public void makeChanges(int xPos, int yPos, int value)
    {
        button[xPos][yPos].setBackground(Color.white);
        button[xPos][yPos].setForeground(Color.white);
        button[xPos][yPos].setText(value + "");
        button[xPos][yPos].setIcon(pics[value]);
    }

    // Sets the numMoves
    public void setNumMoves(int numMoves)
    {
        this.numMoves = numMoves;
    }

    // Increments the number of moves
    public void incrementNumMoves()
    {
        numMoves++;
    }

    // Decrements the number of moves
    public void decrementNumMoves()
    {
        numMoves--;
    }

    // Sets the complexity label on the board
    public void setComplexityLabel(int complexity)
    {
        complexityLabel.setText("Complexity: " + complexity);
    }

    // Sets the numMoves label on the board
    public void setNumMovesLabel()
    {
        countLabel.setText("# of moves: " + numMoves);
    }

    // Returns the number of moves
    public int getNumMoves()
    {
        return numMoves;
    }

    // Returns the complexity
    public int getComplexity()
    {
        return complexity;
    }

}
