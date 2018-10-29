package variable;

public class Result 
{

	private double probability;
	private String steps;
	
	public Result(String steps, double probability)
	{
		setSteps(steps);
		setProbability(probability);
	}
	
	public double getProbability() 
	{
		return probability;
	}
	
	public void setProbability(double probability) 
	{
		this.probability = probability;
	}
	
	public String getSteps() 
	{
		return steps;
	}
	
	public void setSteps(String steps) 
	{
		this.steps = steps;
	}

}