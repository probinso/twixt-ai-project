import javax.swing.*;

public class main extends JFrame
{
    
    public static void main(String[] args) 
    {

	globals.window.setTitle("Twixt");
	globals.window.setLocation(0, 0);
	globals.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	globals.window.setSize(920, 755);
        globals.window.setResizable(true);
	globals.window.setVisible(true);
	
        globals.window.add(globals.gp);
        globals.gp.begin();

	globals.gp.paintImmediately(globals.gp.getVisibleRect());
	globals.gp.revalidate();

    }
}