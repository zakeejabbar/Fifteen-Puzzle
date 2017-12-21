// Zakee Jabbar (zjabba2)
// CS 342
// Project 2



// Class for storing information for one move
public class SingleMove
{
    private int coord1[] = new int[2];
    private int coord2[] = new int[2];

    // Constructor
    public SingleMove(int xPos1, int yPos1, int xPos2, int yPos2)
    {
        coord1[0] = xPos1;
        coord1[1] = yPos1;
        coord2[0] = xPos2;
        coord2[1] = yPos2;
    }

    // Returns the Coordinate of the first position
    public int[] getCoord1()
    {
        return coord1;
    }

    // Returns the Coordinate of the second position
    public int[] getCoord2()
    {
        return coord2;
    }
}