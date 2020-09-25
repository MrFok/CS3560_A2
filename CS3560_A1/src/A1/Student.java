package A1;
public class Student {

	private String ID;
	private Answer choice;
	
	public Student(String id)
	{
		ID = id;
		this.choice = new Answer();
	}
	
	public String getID()
	{
		return ID;
	}
	public void setID(String iD) 
	{
		ID = iD;
	}

	public Answer getAnswer() 		//getter for single choice
	{			
		return choice;
	}

	public void print()		//prints answer
	{
		System.out.println("Name: " + ID);
		System.out.println("Answer: " + choice.printAns());
	}

}
