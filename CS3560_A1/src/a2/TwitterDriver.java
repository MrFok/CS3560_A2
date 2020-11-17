package a2;

import java.awt.EventQueue;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TwitterDriver 
{
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					TwitterAdminPanel adminWindow = TwitterAdminPanel.getInstance();
					adminWindow.getAdmnConsole().setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
