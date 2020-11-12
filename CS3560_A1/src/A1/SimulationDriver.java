/***************************************************************
* file: CS3560_A1
* author: Ricky Fok
* class: Answer
*
* assignment: A1
* date last modified: 9/29/2020
*
* purpose: main. Sets up structure of program
****************************************************************/
package A1;


public class SimulationDriver 
{	
	public static void main(String[] args)
	{
		Question q;
		int rand = (int)(Math.floor(10 * Math.random()));
		//CHANGE QUESTION CONFIGURATION
		String scQuestion		= "What is the color of the sky?";
		String mcQuestion		= "Why did the chicken cross the road?";
		
		//CHANGE CANDIDATE CONFIGURATION
		String[] scCandidates	    = {"Blue", "Red", "White", "Black", "Grey"};
		String[] mcCandidates	    = {"Cause", "Why not", "Hangry", "To cross", "No reason"};
		
		//CUSTOM CONFIGURATION
//		scQuestion = {};
//		mcQuestion = {};
//		
//		scCandidates = {};
//		mcCandidates = {};

		//FORCE QUESTION TYPE
//		rand = 1;		// Single-Choice
//		rand = 2;		// Multiple-Choice

		//Question Selector
		if(rand % 2 == 1)
		{
			q = new Question(scQuestion, scCandidates, 0);	
			System.out.println("Single-Choice Question Selected");
		}
		else
		{
			q = new Question(mcQuestion, mcCandidates, 1);
			System.out.println("Multiple-Choice Question Selected");
		}
	
		VotingService vote = new VotingService(q);
		
		int randInt = (int)(Math.floor(10 * Math.random()) + 1);		//Generate from 1-10 students
		String partialName = "Student ";
		
		Student[] stus = new Student[randInt];
		for(int i = 0; i < randInt; i++)
		{
			//Answer tempA = q.generateRandomAns();
			Student tempS = new Student((partialName + i));
			stus[i] = tempS;
			
			//stus[i].print();	DEBUG
		}
		
		vote.submit(stus);
		
		vote.display();
		
		
	}
}
