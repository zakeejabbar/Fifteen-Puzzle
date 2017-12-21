// Zakee Jabbar (zjabba2)
// CS 342
// Project 2

// Evaluates the board if puzzle is solved
public class Rules
{
    private int[][] board;
    boolean won = false;
    public Rules(Board board1)
    {
        board = board1.getBoard();
    }

    // Checks if the board is the solved board
    public boolean isGameComplete()
    {
        boolean gameComplete = true;
        int value = 1;
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(board[i][j] != value)
                {
                    gameComplete = false;
                }
                value++;
            }
        }
        return gameComplete;
    }
}

