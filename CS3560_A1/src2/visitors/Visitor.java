package visitors;
import treeobj.*;
import gui.*;
public interface Visitor
{
	public void visit(UserGroup group);	//for UserGroups
	public void visit(User user);		//for Users
}
