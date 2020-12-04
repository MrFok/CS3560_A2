package visitors;


import java.util.Arrays;

import treeobj.*;
import gui.*;

public class PositivePercVisitor implements Visitor 
{
	private float positiveNum = 0;;
	private float numOfMessages = 0;
	private String[] positiveWords = {"nice", "good", "great", "excellent", "amazing", "wonderful"};
	
	@Override
	public void visit(UserGroup group) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void visit(User user) 
	{
		TwitterUser tUser = (TwitterUser) user;
		for(String msgs: tUser.getFeed())
		{
			numOfMessages++;
			if(Arrays.stream(positiveWords).anyMatch(msgs.toLowerCase()::contains))
			{
				positiveNum++;
			}
		}
	}
	
	public int getPercentage()	//returns percentage
	{
		return (int) ((positiveNum / numOfMessages) * 100);
	}
	
}
