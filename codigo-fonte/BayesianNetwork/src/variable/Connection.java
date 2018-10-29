package variable;

public class Connection 
{
	
	private Variable sourceVariable;
	private Variable targetVariable;
	
	public Connection(Variable sourceVariable, Variable targetVariable)
	{
		setSourceVariable(sourceVariable);
		setTargetVariable(targetVariable);
	}

	public Variable getSourceVariable() 
	{
		return sourceVariable;
	}

	public void setSourceVariable(Variable sourceVariable) 
	{
		this.sourceVariable = sourceVariable;
	}

	public Variable getTargetVariable() 
	{
		return targetVariable;
	}

	public void setTargetVariable(Variable targetVariable) 
	{
		this.targetVariable = targetVariable;
	}
	
}