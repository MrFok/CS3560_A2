package visitors;
import java.util.ArrayList;
import java.util.List;

import treeobj.*;
import gui.*;

public class VerifyVisitor implements Visitor 
{
	private List<String> nameList = new ArrayList<String>();
	@Override
	public void visit(UserGroup group) 
	{
		nameList.add(group.toString());
	}

	@Override
	public void visit(User user) 
	{
		nameList.add(user.getUserID());
	}

	public String verify()	//checks if all the names do not repeat
	{
		for(String name: nameList)
		{
			if(name.contains(" "))	//if there are spaces
			{
				return "Verification: FAIL (Has space)";
			}
			if(nameList.indexOf(name) != nameList.lastIndexOf(name))	//if there is more than one
			{
				return "Verification: FAIL (Has duplicate)";
			}
		}
		
		return "Verification: PASS";
	}
}
