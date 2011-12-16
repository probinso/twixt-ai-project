import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel
{


    public GamePanel()
    {
	/*
	  This displays the gameboard
	 */
	globals.bp.setMinimumSize(new Dimension(725,755));
        globals.bp.setMaximumSize(new Dimension(725,755));
        globals.bp.setPreferredSize(new Dimension(725,755));
	
	setLayout(new BorderLayout(5, 10));
	add(globals.bp, BorderLayout.WEST);
	
	
    }
    
    public synchronized void paintComponent(Graphics g){
	super.paintComponent(g);
	this.setBackground(Color.LIGHT_GRAY);
    }
    
    public void begin(){}
    
    public static void startGame(){
	globals.bp.begin();
    }
}