package a2;

public interface TreeEntry 
{
	public String toString();
	public void notifyConsoles();
	public void accept(Visitor visitor);
}
