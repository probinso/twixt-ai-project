import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BoardPanel extends JPanel implements MouseListener
{
    private GameBoard Board = new GameBoard(24);
    
    public BoardPanel()
    {
	addMouseListener(this);
	this.repaint();
    }
    
    public synchronized void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	this.setBackground(Color.WHITE);
	
	Graphics2D g2 = (Graphics2D) g;
	g2.setStroke(new BasicStroke(2));
	
	g.setColor(Color.BLUE);
	g.drawLine(33, 30, 693, 30);
	g.drawLine(33, 691, 693, 691);
	
	g.setColor(Color.RED);
	g.drawLine(33, 30, 33, 691);
	g.drawLine(693, 30, 693, 691);
	
	g2.setStroke(new BasicStroke(1));
	
	
	Color[] C = {Color.RED, Color.BLUE, Color.WHITE };
	
	for (int i = 0; i < Board.Size+1; i++){
	    for (int j = 0; j < Board.Size+1; j++){
		
		Point P = new Point(i,j);
		if (!Board.outOfBounds(P,Peg.Status.Zero)){
		    g.setColor(Color.BLACK);
	            g.fillOval(i*30+10, j*30+10, 12, 12);
		    
		    /*
		      This selects the drawing color
		     */
		    g.setColor(C[Board.Pegs[i][j].Color().ordinal()]);
		    for (Point Q: Board.Pegs[i][j].fenceDests)
                       g.drawLine(i*30+16, j*30+16, Q.x*30+16, Q.y*30+16);

		    g.fillOval(i*30+11, j*30+11, 10, 10);
		    
		}
	    }
	}
    }

    public void begin(){
	this.repaint();
    }
    
    public void mouseClicked(MouseEvent e)
    {
	int x = (e.getX()-10)/30;
	int errx = (e.getX()-10)%30;
	
	int y = (e.getY()-10)/30;
	int erry = (e.getY()-10)%30;
	
	if (errx < 13 && erry < 13){
	    boolean B = Board.placePeg(new Point(x,y));
	    this.paintImmediately(this.getVisibleRect());
            this.revalidate();
	    
	}

	
    }
    
    public void mouseExited(MouseEvent e)
    {}
    
    public void mouseEntered(MouseEvent e)
    {}
    
    public void mouseReleased(MouseEvent e)
    {}
    
    public void mousePressed(MouseEvent e)
    {}
    
}
