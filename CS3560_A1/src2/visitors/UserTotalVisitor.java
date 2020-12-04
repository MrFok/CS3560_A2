package visitors;
import treeobj.*;
import gui.*;

public class UserTotalVisitor implements Visitor
{
	private int sum = 0;
	
	@Override
	public void visit(UserGroup group) 
	{
		//Just adding number of Users so when visit group returns zero
	}

	@Override
	public void visit(User user) 
	{
		sum++;
	}
	
	public int getSum()	//returns total Users
	{
		return sum;
	}


}
