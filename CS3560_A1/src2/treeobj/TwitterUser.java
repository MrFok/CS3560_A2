package treeobj;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TwitterUser extends User
{
	private List<String> feed;
	protected List<TwitterUser> followers;
	protected List<TwitterUser> following;
	private long time;
	private long updateTime;
	
	public TwitterUser(String uID)	//main constructor
	{
		super(uID);
		followers = new ArrayList<TwitterUser>();
		following = new ArrayList<TwitterUser>();
		feed = new ArrayList<String>();
		time = System.currentTimeMillis();
		updateTime = Long.MIN_VALUE;
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
		long tempTime = System.currentTimeMillis();
		updateTime(tempTime);
		for(TwitterUser flwer: followers)
		{
			flwer.updateTime(tempTime);
		}
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
	
	public String getStrTime()	//returns time user made 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		return sdf.format(new Date(time));
	}
	
	public long getTime() //returns time that user was made
	{
		return time;
	}
	
	public void updateTime(long time)	//updates the most recent time updated to you and your followers
	{
		updateTime = time;
	}
	
	public long getUpdateTime()	//returns time of most recent update
	{
		return updateTime;
	}

	
	

}
