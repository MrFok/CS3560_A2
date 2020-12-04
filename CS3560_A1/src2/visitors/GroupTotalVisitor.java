package visitors;

import treeobj.*;
import gui.*;

public class GroupTotalVisitor implements Visitor {

	private int sum = 0;
	@Override
	public void visit(UserGroup group) 
	{
		sum++;
	}

	@Override
	public void visit(User user) 
	{
		//Just adding number of groups so when visit user returns zero
	}
	
	public int getSum()	//returns total number of groups
	{
		return sum - 1; //to disregard master root node
	}


}
