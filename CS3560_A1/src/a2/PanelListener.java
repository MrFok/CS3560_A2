package a2;

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
		//System.out.println(e.getSource().toString());
		//System.exit(0);
	}

//	private class myClass()
//	{
//		
//	}
}
