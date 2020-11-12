package a2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TwitterUserPanel extends UserPanelGUI implements ActionListener
{
	private TwitterUser user;
	private List<TwitterUserGroup> groupHolder;
	
	public TwitterUserPanel(TwitterUser user, List<TwitterUserGroup> groups)
	{
		super();
		this.user = user;
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
		if(user.checkFollowing(userName) == false)
		{
			try
			{
				TwitterUser theUser = findAUser(userName);
				System.out.println(theUser.getUserID());
				user.followUser(theUser);
				theUser.addFollower(user);
				followingModel.addElement(theUser.toString()); //updates GUI
			}
			catch(NullPointerException eE)
			{
				System.out.println("ERROR: User Not Valid");
			}
		}
	}

	public void tweetToFollowers(TwitterUser user, String sharingTweet)	//tweets sharingTweet to list of followers
	{
		List<TwitterUser> followersUsers = user.getFollowing();
		for(int i = 0; i < followersUsers.size(); i++)
		{
			TwitterUser tempUser = followersUsers.get(i);
			tempUser.tweet(sharingTweet);	//updated GUI
		}
	}
	
	public void followingFeedUpdate()	//
	{
		
	}
	public void postAction()
	{
		String tweet = txtrTweetMsg.getText();
		user.tweet(tweet);
		feedModel.addElement(tweet);	//updates GUI
		tweetToFollowers(user, tweet);
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
	@Override
	public void update(TreeEntry entry) 
	{
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
