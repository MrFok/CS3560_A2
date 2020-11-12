/***************************************************************
* file: CS3560_A1
* author: Ricky Fok
* class: Answer
*
* assignment: A1
* date last modified: 9/29/2020
*
* purpose: Answer object that is the answer to a question. Contains all possible candidates
* and a true/false table that represents the answer 
****************************************************************/
package A1;

public class Answer 
{
	private static String[] candidates;
	private boolean[] tfTable;				//true-false table that make up answer
	private static int type;				//stores question type 

	public Answer(String[] candidates, int type)		//initial instantiation for Answer (modelAns)
	{
		Answer.type = type;
		this.setCandidates(candidates);
		tfTable = new boolean[candidates.length];
		int numOfAns = 0;
		if(type == 0)		//type 0 is single choice, type 1 is multiple-choice
		{
			numOfAns = 1;
		}
		else
			numOfAns = (int)(Math.floor(((candidates.length - 1) * Math.random() + 2)));	//+2 is to make sure the answer needs more than 1 choice to be correct
			
		
		for(int i = 0; i < candidates.length; i++)		//set all answers as wrong
		{
			tfTable[i] = false;
		}
		
		for(int i = 0; i < numOfAns; i++)				//set numOfAns correct answers
		{
			int temp = (int)Math.floor((candidates.length - 1) * (Math.random()));	//-1 accounts for zero being a viable index
			tfTable[temp] = true;
		}
	}
	
	public Answer()		//student answer instances
	{
		tfTable = new boolean[candidates.length];
		int numOfAns = 0;
		if(type == 0)		//type 0 is single choice, type 1 is multiple-choice
		{
			numOfAns = 1;
		}
		else
			numOfAns = (int)(Math.floor(((candidates.length - 1) * Math.random() + 2)));	//+-2 is to make sure the answer needs more than 1 choice to be correct
		
		for(int i = 0; i < candidates.length; i++)		//set all answers as wrong
		{
			tfTable[i] = false;
		}
		
		for(int i = 0; i < numOfAns; i++)				//set numOfAns correct answers
		{
			int temp = (int)Math.floor((candidates.length - 1) * (Math.random()));	//-1 accounts for zero being a viable index
			tfTable[temp] = true;
		}
	}
	
	public boolean[] getTFTable()			//returns true-false table
	{
		return tfTable;
	}

	public int getNumOfOptions() {			//returns the number of candidates
		return candidates.length;
	}

	public String getChoice(int i)			//returns a specified candidate in the array "candidates"
	{
		return candidates[i];
	}
	
	public void setCandidates(String[] temp)	//sets the array "candidates"
	{
		candidates = new String[temp.length];
		for(int i = 0; i < temp.length; i++)
		{
			candidates[i] = temp[i];
		}
	}

	public String printAns()				//returns a string that represets the true-false table
	{
		int instance = 0;		//accommodate for the comma
		String temp = "{";
		for(int i = 0; i < candidates.length; i++)
		{
			if(tfTable[i] == true)
			{
				instance++;
				if(instance == 1)
					temp = temp + " " + candidates[i];
				else
					temp = temp + ", " + candidates[i];
			}
		}
		temp = temp + " }";
		return temp;
	}
	
	public String printChoices()		//prints all possible choices
	{
		String temp = "{";
		for(int i = 0; i < candidates.length; i++)
		{
			temp = temp + " " + candidates[i] + ",";
		}
		temp = temp + " }";
		return temp;
	}

}
