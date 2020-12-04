package treeobj;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TwitterUserGroup extends UserGroup
{
	private long time;
	public TwitterUserGroup(String groupID) 
	{
		super(groupID);
		time = System.currentTimeMillis();
	}
	
	public String getStrTime()	//returns time Group was made
	{
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		return sdf.format(new Date(time));
	}
	
}
