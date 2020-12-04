package visitors;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelListener implements ActionListener
{
	public PanelListener()
	{
		
	}
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(e.getActionCommand());	// "JButton here"
	}

}
