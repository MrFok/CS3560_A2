package a2;

public interface TreeEntry 
{
	public String toString();				// converts entry to string
	public void notifyConsoles();			// notifies consoles that there is an update
	public void accept(Visitor visitor);	// Accepts Visitor
}
