package A1;

public class Answer 
{
	private static String[] candidates;
	private boolean[] tfTable;
	private static int type;

	public Answer(String[] setCandidates, int type)		//initial instantiation for Answer (modelAns)
	{
		this.type = type;
		this.setCandidates(setCandidates);
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
	
	public boolean[] getTFTable()
	{
		return tfTable;
	}

	public int getNumOfOptions() {
		return candidates.length;
	}

	public String getChoice(int i)
	{
		return candidates[i];
	}
	
	public void setCandidates(String[] temp)
	{
		candidates = new String[temp.length];
		for(int i = 0; i < temp.length; i++)
		{
			candidates[i] = temp[i];
		}
	}

	public String printAns()
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
