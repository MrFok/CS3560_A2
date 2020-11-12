/***************************************************************
* file: CS3560_A1
* author: Ricky Fok
* class: CS 3560
*
* assignment: A1
* date last modified: 9/29/2020
*
* purpose: hub where question is answered by students. Submits answers to question data
****************************************************************/
package A1;
public class VotingService
{
	Question vQ;					//question
	private boolean[] stuAnsResults;		//list of which student's answers are correct
	private Student[] stus;				//list of students
	private int numOfStudents;			//number of students
	
	public VotingService(Question q) //constructor
	{
		vQ = q;
		numOfStudents = 0;
	}
	
	public void submit(Student[] inputStus)		//submits the data from students and compares them to model answer
	{
		copyToStudentArr(inputStus);		//initialize inputStus into stus
		numOfStudents = stus.length;		//set numOfStudents
		stuAnsResults = new boolean[numOfStudents];
		Student tempStu = stus[0];				//rotating student
		Answer tempAns = tempStu.getAnswer();	//rotating answer
		boolean ansResults = false;				//state if current rotate student's answer is true/false
		for(int i = 0; i < numOfStudents; i++)
		{
			ansResults = vQ.checkAns(tempAns);	//check if ans is correct & updates quesiton stats	
			stuAnsResults[i] = ansResults;		//places student answer true/false in bool array
			
			if(i + 1 < numOfStudents)
			{
				tempStu = stus[i + 1];			//rotates student
				tempAns = tempStu.getAnswer();	//rotates answer
			}
		}
		

	}
	
	public void display()
	{
		System.out.println(vQ.getQuestion() + "\nChoices: " + vQ.getModelAnswer().printChoices());
		System.out.println("Correct Answer: " + vQ.getModelAnswer().printAns() + "\n");

		for(int i = 0; i < numOfStudents; i++)
		{
			System.out.print(stus[i].getID() + ": " + stus[i].getAnswer().printAns());
			if(stuAnsResults[i] == false)
				System.out.println(" is INCORRECT.");
			else
				System.out.println(" is CORRECT.");
		}
		System.out.print("\n");
		
		vQ.displayStats();
		
	}
	
	public void copyToStudentArr(Student[] arr)	//copies param array into local array "stus"
	{
		stus = new Student[arr.length];		//initialization
		for(int i = 0; i < arr.length; i++)
		{
			this.stus[i] = arr[i];
		}
	}

}
