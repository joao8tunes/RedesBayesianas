package variable;

import java.util.ArrayList;

public class ConditionalProbability 
{

	private ArrayList<Evidence> evidences;
	private double probability;
	
	public ConditionalProbability(ArrayList<Evidence> evidences, double probability)
	{
		setEvidences(evidences);    //index = 0: target variable.
		setProbability(probability);
	}
	
	public ArrayList<Evidence> getEvidences() 
	{
		return evidences;
	}
	
	public void setEvidences(ArrayList<Evidence> evidences) 
	{
		this.evidences = evidences;
	}
	
	public double getProbability() 
	{
		return probability;
	}
	
	public void setProbability(double probability) 
	{
		this.probability = probability;
	}

}