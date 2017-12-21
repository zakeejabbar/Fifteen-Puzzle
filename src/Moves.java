// Zakee Jabbar (zjabba2)
// CS 342
// Project 2


import java.util.ArrayDeque;
import java.util.Deque;

// Class that stores the moves
// Used for undo and solve
public class Moves
{
    private Deque<SingleMove> allMoves;

    // Constructor
    public Moves()
    {
        allMoves = new ArrayDeque<>();
    }

    // Returns and removes the first element from the stack
    public SingleMove pop()
    {
        if(allMoves.size() == 0)
        {
            return null;
        }
        else
        {
            return allMoves.pop();
        }
    }

    // Adds the move to the start of the stack
    public void push(SingleMove move)
    {
        allMoves.push(move);
    }

    // Clears all moves
    public void clear()
    {
        allMoves.clear();
    }

    // Add a move to the end(Used for solve)
    public void addLast(SingleMove move)
    {
        allMoves.addLast(move);
    }

    // Returns a duplicate of all the current moves(Used for bfs)
    public Moves duplicate(Moves toDup)
    {
        Moves nMoves = new Moves();
        if(toDup == null)
        {
            return nMoves;
        }
        for(SingleMove m : toDup.allMoves)
        {
            nMoves.addLast(m);
        }
        return nMoves;
    }



}

