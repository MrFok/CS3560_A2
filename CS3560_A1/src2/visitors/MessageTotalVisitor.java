package visitors;
import treeobj.*;
import gui.*;
public class MessageTotalVisitor implements Visitor 
{
	private int sum = 0;
	
	@Override
	public void visit(UserGroup group) 
	{
		//Just adding number of tweets from users so when visit usergroup returns zero
	}

	@Override
	public void visit(User user) 
	{
		TwitterUser tUser = (TwitterUser)user;
		for(String msgs: tUser.getFeed())
		{
			sum++;
		}

	}
	
	public int getSum()	//returns total amount of messages
	{
		return sum;
	}

}
