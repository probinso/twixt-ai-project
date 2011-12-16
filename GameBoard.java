import java.util.Vector;
import java.util.EnumMap;
import java.awt.*;

public class GameBoard
{
    public Peg[][] Pegs;

    
    public Vector<Vector<Point>> playerPegs;

    private Peg.Status Turn;
    public final int Size;
    
    public GameBoard(int size)
    {
	this.Turn = Peg.Status.Red;
	this.Size = size-1;
	this.Pegs = new Peg[size][size];
	
	for (int i=0; i<size; i++)
            for (int j=0; j<size; j++)
                this.Pegs[i][j] = new Peg();


	this.playerPegs = new Vector<Vector<Point>>();
	this.playerPegs.add(new Vector<Point>());
	this.playerPegs.add(new Vector<Point>());

    }
    public boolean outOfBounds(Point P){return outOfBounds(P,this.Turn);}
    public boolean outOfBounds(Point P,Peg.Status Turn){
	/*
	  This returns true if the Point proposed is out of bounds
	 */
	Vector<Point> BadPegs = new Vector<Point>();
	BadPegs.add(new Point(0,0));
	BadPegs.add(new Point(0,this.Size));
	BadPegs.add(new Point(this.Size,0));
	BadPegs.add(new Point(this.Size,this.Size));
	
	/*
	  Bad pegs are points that are never valid
	 */
	

	if (BadPegs.contains(P) || 
	    this.Turn == Peg.Status.Zero ||
	    P.x < 0 ||
	    P.x > this.Size ||
	    P.y < 0 ||
	    P.y > this.Size
	    ) return true;
	
	if (Turn == Peg.Status.Blue) {
	    /*
	      Blue player's walls are -/
	      (1,0)->(size-1,0)
	      (1,size)->(size-1,size)
	     */
	    
	    if (P.x == 0 ||
		P.x == this.Size
		) return true;
	}
	
	if (Turn == Peg.Status.Red) {
	    /*
	      Red player's walls are |/|
	      (0,1)->(0,size-1)
	      (size,1)->(size,size-1)
	     */
	    
	    if (P.y == 0 ||
		P.y == this.Size
		) return true;
	}
	
	return false;
    }
        
    /*  Checks if this player has a piece that can be fenced up with it.
	DOES NOT check if it is crossing an enemy fence. This must be added. */
    private Point delta(Point U,Point V){return new Point(U.x-V.x, U.y-V.y);}
    private int cross(Point U, Point V){return U.x*V.y-U.y*V.x;}
    private int min(int x, int y) {return (x<y)?x:y;}
    private int max(int x, int y) {return (x<y)?y:x;}
    
    
    public boolean fenceCollision(Point start, Point end){	
	/*
	  If there is a Colision, then return True
	 */
	Point dPoint = delta(end,start);
	
	for (int i = min(start.x,end.x); i <= max(end.x,start.x); i++)
	    for (int j = min(end.y,start.y); j <= max(end.y,start.y); j++)
		for (int k = 0; k < this.Pegs[i][j].fenceDests.size(); k++)
	            {
			Point loc = new Point(i,j);
			Point fenc = this.Pegs[i][j].fenceDests.elementAt(k);
			int num = cross(delta(start,loc),delta(end,start));
			int den = cross(delta(fenc,loc),delta(end,start));
			if (den !=0){
			    float test = (float)num/(float)den;
			    if ((test < 2.0/3.0) && (0 < test)) return true;
			    /*
			      originally checked (test<1) didn't behave as intended
			     */
			}
		    }
	return false;
    }
    
    private boolean validPair(Point A, Point B){
	/*
	  Identifies if a peg pair will make a fence
	 */
	if (outOfBounds(A) ||
	    outOfBounds(B) ||
	    this.Pegs[A.x][A.y].empty() ||
	    this.Pegs[B.x][B.y].empty() ||
	    this.Pegs[A.x][A.y].Color() != this.Pegs[B.x][B.y].Color()
	    ) return false;
	return true;
    }
    
    private Vector<Point> uncheckedPairs(Point P){
	/*
	  Produces all points that would make fences if valid
	 */
	Vector<Point> pairs = new Vector<Point>();
	pairs.add(new Point(P.x-1,P.y-2));
	pairs.add(new Point(P.x+1,P.y-2));
	pairs.add(new Point(P.x+2,P.y-1));
	pairs.add(new Point(P.x+2,P.y+1));
	pairs.add(new Point(P.x+1,P.y+2));
	pairs.add(new Point(P.x-1,P.y+2));
	pairs.add(new Point(P.x-2,P.y+1));
	pairs.add(new Point(P.x-2,P.y-1));
	return pairs;
    }

    public Vector<Point> pairedPegs(Point P){
	/*
	  Identifies active pegs that can form fences with P
	 */
	Vector<Point> pairs = uncheckedPairs(P);
	Vector<Point> validPairs = new Vector<Point>();
	for (Point Q : pairs){
	    if (validPair(P,Q))
		if (!fenceCollision(P,Q))
		    validPairs.add(Q);
	}
	return validPairs;
    }
    
    public boolean placePeg(Point P){
	/*
	  places peg and connects fences
	 */
	if (outOfBounds(P)) return false;
	
	if (Pegs[P.x][P.y].setColor(this.Turn)){
	    this.playerPegs.elementAt(this.Turn.ordinal()).add(P);
	
	    Vector<Point> validPairs = pairedPegs(P);
	    
	    for (Point Q : validPairs){
		this.Pegs[Q.x][Q.y].addFence(P);
		this.Pegs[P.x][P.y].addFence(Q);
	    }
	    this.Turn=(this.Turn==Peg.Status.Red)?Peg.Status.Blue:Peg.Status.Red;
	    return true;
	}
	return false;
    }
    
}