package peevedFowls;
import java.util.ArrayList;

public class Vector 
{
	ArrayList<AlgebraicEquation> vectorAlgebraicEquation= new ArrayList<>();
	protected double valueMultiplierOfVectorX=1.0;
	protected double valueMultiplierOfVectorY=1.0;
	public Vector(ArrayList<AlgebraicEquation> AlgebraicEquation)
	{
		vectorAlgebraicEquation=AlgebraicEquation;
	}
	public AlgebraicEquation getAt(int index)
	{
		return vectorAlgebraicEquation.get(index);
	}
	public double evaluateVelocity(double time)
	{
		double sum = 0;
		for(int i = 0; i <vectorAlgebraicEquation.size();i++)
		{
			sum+=vectorAlgebraicEquation.get(i).evaluate(time)*vectorAlgebraicEquation.get(i).evaluate(time);
		}
		return Math.sqrt(sum);
	}
	public String toString()
	{
		String s="";
		for(int i = 0; i < vectorAlgebraicEquation.size();i++)
		{
			s+=vectorAlgebraicEquation.get(i).toString()+" ";
		}
		return s;
	}
	
	public double evaluateVectorYAbsValue(double time)
	{
		double sum = 0;
		if(vectorAlgebraicEquation.get(1)==null)
		{
			return 0;
		}
		else
		{
			sum+=vectorAlgebraicEquation.get(1).evaluateAbsValue(time);
			return sum;
		}
	}
	public double evaluateVectorX(double time)
	{
		double sum = 0;
		if(vectorAlgebraicEquation.get(0)==null)
		{
			return 0;
		}
		
		else
		{
			sum+=vectorAlgebraicEquation.get(0).evaluate(time)*valueMultiplierOfVectorX;
			return sum;
		}
	}
	public double evaluateVectorY(double time)
	{
		double sum = 0;
		if(vectorAlgebraicEquation.get(1)==null)
		{
			return 0;
		}
		
		else
		{
			sum+=vectorAlgebraicEquation.get(1).evaluate(time)*valueMultiplierOfVectorY;
			return sum;
		}
	}
}
