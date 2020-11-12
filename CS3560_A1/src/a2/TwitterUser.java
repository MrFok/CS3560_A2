package a2;

import java.util.ArrayList;
import java.util.List;

public class TwitterUser extends User
{
	private List<String> feed;
	protected List<TwitterUser> followers;
	protected List<TwitterUser> following;
	
	public TwitterUser(String uID)	//main constructor
	{
		super(uID);
		followers = new ArrayList<TwitterUser>();
		following = new ArrayList<TwitterUser>();
		feed = new ArrayList<String>();
	}
	
	public void followUser(TwitterUser fUser)	//adds fUser to following list
	{
		following.add(fUser);
		notifyConsoles();
	}
	
	public List<TwitterUser> getFollowing()
	{
		return following;
	}
	
	public void addFollower(TwitterUser ferUser)	//adds fUser to follower list
	{
		followers.add(ferUser);
		notifyConsoles();
	}
	
	public List<TwitterUser> getFollowers()
	{
		return followers;
	}
	
	
	public void tweet(String message)	//adds message into feed list
	{
		
		feed.add(message);
		notifyConsoles();
	}
	
	public List<String> getFeed()	//returns feed
	{
		return feed;
	}

	public boolean checkFollowing(String fUser)	//checks if we the user are following fUser
	{
		for(int i = 0; i < following.size(); i++)
		{
			if(following.get(i).toString().equals(fUser))
			{
				return true;
			}
		}
		
		return false;
	}

	
	

}
