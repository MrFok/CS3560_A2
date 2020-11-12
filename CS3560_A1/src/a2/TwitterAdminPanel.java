package a2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class TwitterAdminPanel extends AdminPanelGUI implements ActionListener
{
	private static TwitterAdminPanel instance = null;
	private List<TwitterUserGroup> groupHolder;
	
	private TwitterAdminPanel() 
	{
		TwitterUserGroup temp = new TwitterUserGroup("Twitter");
		root = new DefaultMutableTreeNode(temp);
		groupHolder = new ArrayList<TwitterUserGroup>();
		groupHolder.add(temp);
		initialize();
		addListener();
	}
	
	public static TwitterAdminPanel getInstance()
	{
		if(instance == null)
		{
			instance = new TwitterAdminPanel();
		}
		return instance;
	}
	
	public void addListener()
	{
		btnAddUser.addActionListener(this);
		btnAddGroup.addActionListener(this);
		btnOpenUserView.addActionListener(this);
	}

	public int retrieveUserGroupIndex(String name)
	{
		for(int i = 0; i < groupHolder.size(); i++)
		{
			if(groupHolder.get(i).toString().equals(name) == true)
			{
				return i;
			}
		}
		
		return 0;
	}
	@Override
	public void update(TreeEntry entry) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		String buttonPressed = e.getActionCommand();
		
		switch(buttonPressed)
		{
			case "Add User":
				addUserAction();
				break;
			case "Add Group":
				addGroupAction();
				break;
			case "Open User View":
				openUserPanelAction();
				break;
		}
	}
	
	public void addUserAction()
	{
		String temp = txtrTextareaUserid.getText();
		DefaultMutableTreeNode selected = getLastPath();
		
		try
		{
			if(temp.equals(USERID_TEXT) || temp.equals(""))
			{}
			else
			{
				DefaultMutableTreeNode groupNode = (DefaultMutableTreeNode)selected.getRoot();
				int groupIndex = retrieveUserGroupIndex(groupNode.getUserObject().toString());
				TwitterUserGroup groupObj = groupHolder.get(groupIndex);
				if(groupObj.checkUserExists(temp) == false)
				{
					TwitterUser newUser = new TwitterUser(temp);
					DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(newUser);
					groupObj.addUser(newUser);
					selected.add(userNode);
					model.reload(root);	//temporary
				}	
			}
		}
		catch(NullPointerException eE)
		{
			System.out.println("ERROR: You have not selected any item in the list. Please try again.");
		}
	}
	
	public void addGroupAction()
	{
		String temp = txtrTextareaGroupId.getText();
		DefaultMutableTreeNode selected = getLastPath();
		
		try 
		{
			if(temp.equals(GROUPID_TEXT) || temp.equals(""))
			{}
			else
			{
				if(selected.getUserObject() instanceof TwitterUserGroup)	//prohibits adding onto Users
				{
					TwitterUserGroup newGroup = new TwitterUserGroup(temp);
					groupHolder.add(newGroup);	
					DefaultMutableTreeNode newGroupNode = new DefaultMutableTreeNode(newGroup);
					selected.add(newGroupNode);
					model.reload(root);	//temporary
				}
			}
		}
		catch(NullPointerException eE)
		{
			System.out.println("ERROR: You have not selected any item in the list. Please try again.");
		}
	}
	
	public void openUserPanelAction()
	{
		DefaultMutableTreeNode selected = getLastPath();	
		try
		{
			DefaultMutableTreeNode groupNode = (DefaultMutableTreeNode)selected.getRoot();
			String name = groupNode.getUserObject().toString();
			int groupIndex = retrieveUserGroupIndex(name);
			//TwitterUserGroup groupObj = groupHolder.get(groupIndex);
			//TwitterUser selectedUser = (TwitterUser) groupObj.getUser(name);
			TwitterUser selectedUser = (TwitterUser) selected.getUserObject();
			System.out.println(selectedUser.toString());
			Object objType = selected.getUserObject();
			if(objType instanceof TwitterUser)
			{
				TwitterUserPanel userWindow = new TwitterUserPanel(selectedUser, groupHolder);
				userWindow.setFrmUserPanel(objType.toString() + " User Panel");
				userWindow.getFrmUserPanel().setVisible(true);
			}
		}
		catch(NullPointerException eE)
		{
			System.out.println("ERROR: You have not selected any item in the list. Please try again.");
		}
	}
}

