// Zakee Jabbar (zjabba2)
// CS 342
// Project 2


// Tuple class for the bfs
public class Tuple
{
    private String stringBoard;
    private Moves allMoves;

    // Constructor
    public Tuple(Board b, Moves m)
    {
        stringBoard = b.toString();
        if(m == null)
        {
            allMoves = new Moves();
        }
        else
        {
            allMoves = m;
        }
    }

    // Returns all the moves
    public Moves getMoves()
    {
        return allMoves;
    }

    // Returns the string representation of the board
    public String getBoard()
    {
        return stringBoard;
    }
}
