package variable;

public class Evidence 
{

	private Variable variable;
	private Boolean value = null;
	
	public Evidence(Variable variable, boolean value)
	{
		setVariable(variable);
		setValue(new Boolean(value));
	}
	
	public Evidence(Variable variable)
	{
		setVariable(variable);
	}
	
	public Variable getVariable() 
	{
		return variable;
	}
	
	public void setVariable(Variable variable) 
	{
		this.variable = variable;
	}
	
	public boolean getValue() 
	{
		return value;
	}
	
	public void setValue(boolean value) 
	{
		this.value = new Boolean(value);
	}
	
	public boolean hasEvidence()
	{
		if (value == null) return false;
		return true;
	}
	
}