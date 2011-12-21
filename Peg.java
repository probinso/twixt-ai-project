import java.util.Vector;
import java.awt.*;

public class Peg
{
    public enum Status {Red, Blue, Zero};

    private Status Color;
    
    public Vector<Point> fenceDests;
    /*
      fenceDests is public because there is never a fence
      that is manipulated without effecting another.

      It should also be public for ease of graph traversal 
      algorithms
     */
    
    public Peg()
    {
	this.Color = Status.Zero;
	this.fenceDests = new Vector<Point>();
    }
    
    public boolean empty(){
	if (this.Color == Status.Zero) return true;
	return false;
    }
    
    public Status Color(){
	return this.Color;
    }
    
    public boolean setColor(Status S){
	boolean ret = false;
	if (this.Color() == Status.Zero){
	    this.Color = S;
	    ret = true;
	}
	return ret;
    }
    
    public void reset(){
	this.Color = Status.Zero;
	this.fenceDests = new Vector<Point>();
    }

    public void addFence(Point P){
	this.fenceDests.add(P);
    }
    
    
}
