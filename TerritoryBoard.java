public class TerritoryBoard extends GameBoard
{
    /*
      The purpose of this class is to provide 
      the nessicary tools for identifying implied
      territories. 

      The main facility will be apparent by 
      placing numerical values wrt. unclaimed pegs

      Until greater description is valued, Negative
      numbers will denote Red, Positive numbers 
      will denote Blue.
     */

    public int weights[][];

    public TerritoryBoard(int size){	
	super(size);
	this.weights = new int[size][size];
	for (int i = 0; i < size; i++)
	    for (int j = 0; j < size; j++)
		this.weights[i][j]=0;
	
    }
}