package a2;

import java.util.ArrayList;
import java.util.List;

abstract class UserGroup implements TreeEntry
{
	private String groupID; // user group id
	private List<TreeEntry> items;
	
	private List<GUI_Interface> consoles = new ArrayList<GUI_Interface>();
	
	public UserGroup(String groupID)
	{
		this.setGroupID(groupID);
		items = new ArrayList<TreeEntry>();
		notifyConsoles();
	}
	
	public void addUser(User temp)	//adds TreeEntry item to items list
	{
		items.add(temp);
		notifyConsoles();
	}
	
	public User getUser(String userName)	//returns user w/ userName
	{
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof User && (items.get(i).toString()).equals(userName))
			{
				return (User) items.get(i);
			}
		}
		
		return null;
	}
	
	public void addUserGroup(UserGroup temp)	//adds TreeEntry item to items list
	{
		items.add(temp);
		notifyConsoles();
	}
	
	public UserGroup getUserGroup(String name)	//returns user group w/ name
	{
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof UserGroup && (items.get(i).toString()).equals(name))
			{
				return (UserGroup) items.get(i);
			}
		}
		
		return null;
	}

	public boolean checkUserExists(String testUser)	//checks if testUser is inside list. Returns true if so and false if not
	{
		for(TreeEntry item: items)
		{
			if(item.toString().equals(testUser))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public String toString()	//returns groupID
	{
		return groupID;
	}

	public void setGroupID(String groupID) //sets groupID
	{
		this.groupID = groupID;
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
	
	public void accept(Visitor visitor)	
	{
		for(TreeEntry things: items)
		{
			things.accept(visitor);
		}
		visitor.visit(this);
	}
	
	public List<TreeEntry> getItems()	//returns list of items in user group
	{
		return items;
	}
	
	
	
}
