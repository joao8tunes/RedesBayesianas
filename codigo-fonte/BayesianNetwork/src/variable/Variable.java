package variable;

import java.util.ArrayList;

public class Variable
{
	
	private String name;
	private String indexName;
	private ArrayList<Connection> inputConections = new ArrayList<Connection>();    //Conexões que entram nesse estado.
	private ArrayList<Connection> outputConections = new ArrayList<Connection>();    //Conexões que saem desse estado.
	private ArrayList<ConditionalProbability> conditionalProbabilities = new ArrayList<ConditionalProbability>();
	
	public Variable(String name, String indexName)
	{
    	setName(name);
    	setIndexName(indexName);
	}

	public ArrayList<Connection> getInputConections() 
	{
		return inputConections;
	}

	public void setInputConections(ArrayList<Connection> inputConections) 
	{
		this.inputConections = inputConections;
	}

	public ArrayList<Connection> getOutputConections() 
	{
		return outputConections;
	}

	public void setOutputConections(ArrayList<Connection> outputConections) 
	{
		this.outputConections = outputConections;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getIndexName() 
	{
		return indexName;
	}

	public void setIndexName(String indexName) 
	{
		this.indexName = indexName;
	}

	public ArrayList<ConditionalProbability> getConditionalProbabilities() 
	{
		return conditionalProbabilities;
	}

	public void setConditionalProbabilities(ArrayList<ConditionalProbability> conditionalProbabilities) 
	{
		this.conditionalProbabilities = conditionalProbabilities;
	}

}