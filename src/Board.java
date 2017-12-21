// Zakee Jabbar (zjabba2)
// CS 342
// Project 2


// Internal board for the game
// Correlates to the graphics board
public class Board {
    private int[][] board;
    private int[] blankCoord;

    // Constructor
    public Board()
    {
        board = new int[4][4];
    }

    // Constructor 2
    // Takes in string represent of a board and makes a 2d array
    public Board(String s)
    {
        board = new int[4][4];
        blankCoord = new int[2];
        String[] splitString = s.split(" ");
        int[] numbers = new int[splitString.length];
        int index = 0;
        for(int i = 0; i < 16; i++)
        {
            numbers[i] = Integer.parseInt(splitString[i]);
        }
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(numbers[index] == 16)
                {
                    blankCoord[0] = i;
                    blankCoord[1] = j;
                }
                board[i][j] = numbers[index];
                index++;
            }
        }
    }

    // Sets a value at a specific position
    public void setPosValue(int xPos, int yPos, int value) {
        board[xPos][yPos] = value;

    }

    // Returns the value of a position
    public int getPosValue(int xPos, int yPos) {
        return board[xPos][yPos];

    }

    // Return the position of a specific value
    public int[] getPos(int value)
    {
        int coord[] = new int[2];
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            if (board[i][j] == value)
            {
                coord[0] = i;
                coord[1] = j;
                return coord;
            }
        }
        return coord;
    }

    // Returns the whole board
    public int[][] getBoard()
    {
        return board;
    }

    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++)
            {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println(" ");

        }
        System.out.println(" ");

    }

    public void swap(int xPos1, int yPos1, int xPos2, int yPos2)
    {
        int temp = board[xPos1][yPos1];
        board[xPos1][yPos1] = board[xPos2][yPos2];
        board[xPos2][yPos2] = temp;
    }

    public String toString()
    {
        String boardString = "";
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(i == 0 && j == 0)
                {
                    boardString += board[i][j];
                }
                else
                {
                    boardString += " " +  board[i][j];
                }
            }
        }
        return boardString;
    }

    public boolean isWinnerString(String s)
    {
        String winnerString = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16";
        return s.equals(winnerString);
    }

    public int[] getBlankCoord()
    {
        return blankCoord;
    }


}
