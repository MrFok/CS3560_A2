package a2;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

public class TwitterUserPanel extends UserPanelGUI implements ActionListener
{
	private TwitterUser user;
	private List<TwitterUserGroup> groupHolder;
	
	public TwitterUserPanel(TwitterUser user, List<TwitterUserGroup> groups)
	{
		super();
		
		// sets icon image
		URL url = getClass().getResource("/twitter_main.png");
		ImageIcon userIcon = new ImageIcon(url);
		Image image = userIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		frmUserPanel.setIconImage(image);
		
		this.user = user;
		this.update(user);
		user.attachConsole(this);
		groupHolder = groups;
		addListener();
	}
	
	public void addListener()	//adds all button listeners
	{
		btnFollowUser.addActionListener(this);
		btnButtonPost.addActionListener(this);
		btnClosePanel.addActionListener(this);
	}
	
	public void followUserAction()	//if follow button is pressed
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
	
	public void postAction()	//if post button is pressed
	{
		String tweet = txtrTweetMsg.getText();
		user.tweet(user.toString() + ": " + tweet);
	}
	
	public void closePanelAction()	//if close button is pressed
	{
		frmUserPanel.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)	//catches and performs corresponding button pressed actions
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
	
	public boolean checkInFollowing(String name)	//checks if name is in the following list of user
	{
		DefaultListModel<String> following = followingModel;
		for(int i = 0; i < following.size(); i++)
		{
			if(following.getElementAt(i).equals(name))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void update(TreeEntry entry)	//updates panel (Observer)
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
	
	public TwitterUser getUser()	//returns user in this panel
	{
		return user;
	}



}
