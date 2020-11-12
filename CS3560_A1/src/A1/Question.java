/***************************************************************
* file: CS3560_A1
* author: Ricky Fok
* class: Question
*
* assignment: A1
* date last modified: 9/29/2020
*
* purpose: Encapsulates all information regarding question. 
****************************************************************/
package A1;
public class Question
{
	private String question;
	private Answer modelAns;
	private int[] stats;		//Index 0 - x(num of choices - 1) are stats of each choice respectively. Index x+1 is correct, x+2 is incorrect
	private int numOfChoices;
//	private int type;
	
	public Question(String question, String[] setCandidates, int type)
	{
		this.question = question;							//instantiation of question variable in Question
		//this.type = type;
		modelAns = new Answer(setCandidates, type);			//instantiation of modelAns, which will generate an Answer for the question
		setNumOfChoices(modelAns);							//instantiation of the number of choices via Answer
		stats = new int[getNumOfChoices() + 2];				//instantiation of stats array				
	}
	
	public Answer generateRandomAns()				//creates random answers to question
	{
		Answer temp = new Answer();		//creates answer based on num of choices in modelAns
		return temp;
	}
	
	public int getNumOfChoices()					//getter for numOfChoices
	{
		return numOfChoices;
	}
	
	public void setNumOfChoices(Answer temp)		//sets numOfChoices to number of options in Answer variable "temp"
	{
		numOfChoices = temp.getNumOfOptions();
	}
	
	public void displayStats()						//displays the stats of submission results
	{
		int count = 0;		// counts when prints 3 per column
		System.out.println("\nSubmission Results");
		
		for(int i = 0; i < stats.length - 2; i++)
		{
			if(count < 3)
			{
				System.out.print(modelAns.getChoice(i) + ":" + stats[i] + " ");	//prints 1st and 2nd item in columns
				count++;
			}
			else
			{
				System.out.print("\n" + modelAns.getChoice(i) + ":" + stats[i] + " ");	//prints 3rd item in column and indents
				count = 0;
			}
		}
		
		System.out.println("\n" + "Correct: " + stats[numOfChoices + 1]);	//prints out stats[x+1]
		System.out.println("Incorrect: " + stats[numOfChoices]);	//prints out stats[x+2]
	}
	
	public boolean checkAns(Answer checkAns)		//returns if the answer in parameter is correct (matches with modelAns)
	{
		boolean[] stuAnswer = checkAns.getTFTable();
		boolean[] modelAnswer = modelAns.getTFTable();
		boolean wrong = false;						//if missed that means something in stu answer is wrong
		
		for(int i = 0; i < numOfChoices; i++)
		{
			if(stuAnswer[i] != modelAnswer[i])
			{	
				if(stuAnswer[i] == true)				//checks if inputted value		
				{
					stats[i] = stats[i] + 1;			//increments answer used

				}
				wrong = true;
				
			}
			else
			{
				if(stuAnswer[i] == true)
				{
					stats[i] = stats[i] + 1;			//increments answer used
				}
			}
		}
		
		if(wrong == false)
		{
			stats[numOfChoices + 1]++;			//increments correct column
			return true;
		}
		else
		{
			stats[numOfChoices]++;				//increments incorrect column
			return false;
		}
	}
	
	public Answer getModelAnswer()					//getter for modelAns
	{
		return modelAns;
	}
	
	public void setModelAnswer(Answer ans)			//setter for modelAns
	{
		modelAns = ans;			//MAY HAVE PROBLEMS
	}
	
	public String getQuestion()						//getter for question itself
	{
		return question;
	}
	
	public void setQuestion(String question)		//setter for question itself
	{
		this.question = question;
	}
	
	public int[] getStats()							//getter for stats
	{
		return stats;
	}
	
	public void setStats(int[] temp)				//will be instantiated in subclasses
	{
		for(int i = 0; i < temp.length; i++)
		{
			stats[i] = temp[i];
		}
	}

}
