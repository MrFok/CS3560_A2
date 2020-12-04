package visitors;
import java.util.Date;

import treeobj.*;
import gui.*;

public class LastUpdatedVisitor implements Visitor 
{
	private TwitterUser latestUser = null;
	private long latestTime = Long.MIN_VALUE;
	private int numRead = 0;
	@Override
	public void visit(UserGroup group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(User user) 
	{
		TwitterUser tempUser = (TwitterUser)user;
		
		if(numRead == 0)
		{
			latestUser = tempUser;
			latestTime = tempUser.getUpdateTime();
			numRead++;
		}
		else 
		{
			if(tempUser.getUpdateTime() > latestTime) 
			{
				latestUser = tempUser;
				latestTime = tempUser.getUpdateTime();
				numRead++;
			}
			
		}
	}
	
	public String getLatestUser()	//returns latestUser
	{
		if(latestTime == Long.MIN_VALUE)
		{
			return "None Updated Yet";
		}
		else
		{
			return "Last Updated User: " + latestUser.getUserID();
		}
	}

}
