package a2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class TwitterUserPanel extends UserPanelGUI implements ActionListener
{
	private TwitterUser user;
	private List<TwitterUserGroup> groupHolder;
	//private static boolean started = false;
	
	public TwitterUserPanel(TwitterUser user, List<TwitterUserGroup> groups)
	{
		super();
		this.user = user;
		this.update(user);
		user.attachConsole(this);
		groupHolder = groups;
		addListener();
	}
	
	public void addListener()
	{
		btnFollowUser.addActionListener(this);
		btnButtonPost.addActionListener(this);
		btnClosePanel.addActionListener(this);
	}
	
	public void followUserAction()
	{
		String userName = txtrUserFollow.getText();
		if(user.checkFollowing(userName) == false && userName.equals(user.getUserID()) == false)
		{
			try
			{
				TwitterUser theUser = findAUser(userName);
				user.followUser(theUser);
				theUser.attachConsole(this);
				theUser.addFollower(user);
			}
			catch(NullPointerException eE)
			{
				System.out.println("ERROR: User Not Valid");
			}
		}
	}
	
	public void importFeed()	//get feeds from followers PART OF UPDATE
	{
		for(TwitterUser updateUser: user.getFollowing())
		{
			List<String> tweetsToImport = updateUser.getFeed();
			for(String tweets: tweetsToImport)
			{
				if(checkInFeed(tweets) == false)
				{
					feedModel.addElement(tweets);
				}
			}
		}
	}

	public void postAction()
	{
		String tweet = txtrTweetMsg.getText();
		user.tweet(user.toString() + ": " + tweet);
		//feedModel.addElement(user.toString() + ": " + tweet);	//updates GUI
	}

	public boolean checkInFeed(String check)	//returns true if string is already in feed
	{
		DefaultListModel<String> feed = feedModel;
		for(int i = 0; i < feed.size(); i++)
		{
			if(feed.getElementAt(i).equals(check))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean checkInFollowing(String check)
	{
		DefaultListModel<String> following = followingModel;
		for(int i = 0; i < following.size(); i++)
		{
			if(following.getElementAt(i).equals(check))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void closePanelAction()
	{
		frmUserPanel.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String buttonPressed = e.getActionCommand();
		
		switch(buttonPressed)
		{
			case "Follow User":
				followUserAction();
				break;
			case "Post Tweet":
				postAction();
				break;
			case "Close Panel":
				closePanelAction();
				break;
		}
		
	}
	
	public void update(TreeEntry entry) 
	{
		if(entry instanceof TwitterUser)
		{
			for(TwitterUser following: ((TwitterUser) entry).getFollowing())
			{
				if(checkInFollowing(following.toString()) == false && entry.equals(user))
				{
					followingModel.addElement(following.toString());
				}
			}

			for(String userTweets: ((TwitterUser) entry).getFeed())
			{
				if(checkInFeed(userTweets) == false)
				{
					feedModel.addElement(userTweets);
				}
			}
			importFeed();
		}
		//listFollowing.set = new JList(user.getFollowingDLM());
		//listFeed = ;
	}
	
	public TwitterUser findAUser(String name)	//finds user with name in list of our UserGroups
	{
		for(int i = 0; i < groupHolder.size(); i++)
		{
			TwitterUserGroup temp = groupHolder.get(i);
			for(int j = 0; j < temp.getItems().size(); j++)
			{
				TwitterUser foundUser = (TwitterUser) temp.getUser(name);
				if(foundUser != null)
				{
					return foundUser;
				}
			}
		}
		
		return null;
	}
	
	public TwitterUser getUser()
	{
		return user;
	}



}
