package peevedFowls;

public class Variable 
{
	// algebra has a char
	protected char algebraVariable;
	// algebra has a number
	protected double algebraNumber;
	// algebra has a exponent
	protected int algebraExponent;
	public Variable(char variable,double number)
	{
		algebraVariable=variable;
		algebraNumber=number;
		algebraExponent = 1;
	}
	public Variable(char variable, double number,int exponent)
	{
		algebraVariable=variable;
		algebraNumber=number;
		algebraExponent = exponent;
	}
	public double evaluate(double time)
	{
		for(int i = 1; i < algebraExponent;i++)
		{
			time*=time;
		}
		return algebraNumber*time;
		
	}
	
	public String toString()
	{
		String s = "";
		if(algebraExponent>1)
		{
			s="^"+algebraExponent;
		}
		return (int)algebraNumber+""+algebraVariable+s;
	}
}
