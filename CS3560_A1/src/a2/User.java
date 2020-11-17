package a2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

abstract class User implements TreeEntry
{
	protected String userID;
	protected DefaultMutableTreeNode usersNode;
	
	protected List<GUI_Interface> consoles = new ArrayList<GUI_Interface>();

	public User(String uID)
	{
		setUserID(uID);
		usersNode = new DefaultMutableTreeNode(this);
	}

	public String toString()	//returns userID
	{
		return userID;
	}

	public String getUserID() //sets userID
	{
		return userID;
	}
	
	public void setUserID(String userID) //sets userID
	{
		this.userID = userID;
	}
	
	public void notifyConsoles()	//notifies consoles that things need to be updated
	{
		for(GUI_Interface console: consoles)
		{
			console.update(this);
		}
	}
	
	public void attachConsole(GUI_Interface console)	//attach a console that will update when user changes
	{
		consoles.add(console);
	}
	
	public DefaultMutableTreeNode getUsersNode()	//returns user's node
	{
		return usersNode;
	}
	
	public void accept(Visitor visitor)
	{
		visitor.visit(this);
	}

	
}
