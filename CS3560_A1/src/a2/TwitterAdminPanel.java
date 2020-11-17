package a2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class TwitterAdminPanel extends AdminPanelGUI implements ActionListener
{
	private static TwitterAdminPanel instance = null;
	TwitterUserGroup rootGroup;
	private List<TwitterUserGroup> groupHolder;

	private int numOfUsers;
	
	private TwitterAdminPanel() 
	{
		rootGroup = new TwitterUserGroup("Twitter");
		root = new DefaultMutableTreeNode(rootGroup);
		groupHolder = new ArrayList<TwitterUserGroup>();
		groupHolder.add(rootGroup);
		initialize();
		addListener();
	}
	
	public static TwitterAdminPanel getInstance()	//returns instance of TwitterAdminPanel (Singleton)
	{
		if(instance == null)
		{
			instance = new TwitterAdminPanel();
		}
		return instance;
	}
	
	public void addListener()	//add listeners
	{
		btnAddUser.addActionListener(this);
		btnAddGroup.addActionListener(this);
		btnOpenUserView.addActionListener(this);
		btnShowUserTotal.addActionListener(this);
		btnShowGroupTotal.addActionListener(this);
		btnShowMessagesTotal.addActionListener(this);
		btnShowPositivePercentage.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) //catches and performs corresponding button pressed actions
	{
		String buttonPressed = e.getActionCommand();
		
		switch(buttonPressed)
		{
			case ADDUSER_TEXT:
				addUserAction();
				break;
			case ADDGROUP_TEXT :
				addGroupAction();
				break;
			case OPENUSERVIEW_TEXT:
				openUserPanelAction();
				break;
			case USERTOTAL_TEXT:
				showUserTotalAction();
				break;
			case GROUPTOTAL_TEXT:
				showGroupTotalAction();
				break;
			case MESSAGESTOTAL_TEXT:
				showMessageTotalAction();
				break;
			case POSITVEPERC_TEXT:
				showPositivePercAction();
				break;
		}
	}
	
	public void addUserAction()	//adds User
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
				TwitterUserGroup groupObj = (TwitterUserGroup)groupNode.getUserObject();
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
	
	public void addGroupAction()	//adds group
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
					DefaultMutableTreeNode groupNode = (DefaultMutableTreeNode)selected.getRoot();
					TwitterUserGroup parentGroup = (TwitterUserGroup)groupNode.getUserObject();
					TwitterUserGroup newGroup = new TwitterUserGroup(temp);
					newGroup.attachConsole(this);
					groupHolder.add(newGroup);	
					parentGroup.addUserGroup(newGroup);
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
	
	public void openUserPanelAction()	//opens user panel for selected user
	{
		DefaultMutableTreeNode selected = getLastPath();	
		try
		{
			DefaultMutableTreeNode groupNode = (DefaultMutableTreeNode)selected.getRoot();
			String name = groupNode.getUserObject().toString();
			int groupIndex = retrieveUserGroupIndex(name);
			TwitterUser selectedUser = (TwitterUser) selected.getUserObject();
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

	public void showUserTotalAction()	//prints user total into text box
	{
		UserTotalVisitor temp = new UserTotalVisitor();
		rootGroup.accept(temp);
		int num = temp.getSum();
		txtrTextareaButton.setText("User Total: " + num + " users");
		//System.out.println("Number of Users: " + num);
	}
	
	public void showGroupTotalAction()	//prints group total into text box
	{
		GroupTotalVisitor temp = new GroupTotalVisitor();
		rootGroup.accept(temp);
		int num = temp.getSum();
		txtrTextareaButton.setText("Group Total: " + num + " groups");
		//System.out.println("Number of Groups: " + num);
	}
	
	public void showMessageTotalAction()	//prints message total into text box
	{
		MessageTotalVisitor temp = new MessageTotalVisitor();
		rootGroup.accept(temp);
		int num = temp.getSum();
		txtrTextareaButton.setText("Messages Total: " + num + " tweets");
		//System.out.println("Number of Groups: " + num);
	}
	
	public void showPositivePercAction()	//prints positive percentage messages into text box
	{
		PositivePercVisitor temp = new PositivePercVisitor();
		rootGroup.accept(temp);
		int num = temp.getPercentage();
		txtrTextareaButton.setText("Positive Message %: " + num + "%");
		//System.out.println("Number of Groups: " + num);
	}
	
	public int retrieveUserGroupIndex(String name)	//returns user group index specified with name
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

	



}

