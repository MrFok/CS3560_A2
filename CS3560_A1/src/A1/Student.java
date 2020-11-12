/***************************************************************
* file: CS3560_A1
* author: Ricky Fok
* class: CS 3560
*
* assignment: A1
* date last modified: 9/29/2020
*
* purpose: holds ID and answer. Represents student.
****************************************************************/
package A1;
public class Student {

	private String ID;
	private Answer choice;
	
	public Student(String id)		//constructor
	{
		ID = id;
		this.choice = new Answer();
	}
	
	public String getID()			//returns ID
	{
		return ID;
	}
	public void setID(String iD) 	//sets ID 
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
