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
//					DefaultMutableTreeNode root = new DefaultMutableTreeNode("CS3560");
//					DefaultMutableTreeNode RF = new DefaultMutableTreeNode("Ricky Fok");
//					DefaultMutableTreeNode YS = new DefaultMutableTreeNode("Dr. Yu Sun (Professor)");
//					root.add(RF);
//					root.add(YS);
//					JTree tempTree = new JTree(root);
//					adminWindow.setTree(tempTree);
					
//					UserPanelGUI userWindow = new UserPanelGUI();
//					userWindow.getFrmUserPanel().setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
