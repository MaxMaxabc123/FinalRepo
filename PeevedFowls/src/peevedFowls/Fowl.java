package peevedFowls;

import java.util.ArrayList;

public class Fowl extends PhysicsObject
{
	protected boolean slatedToBeDestroyed=false;
	
	
	public Fowl(int xCord,int yCord,int size,int mass)
	{
		xCoordinate = xCord;
		yCoordinate = yCord;
		objectSize = size;
		objectMass = mass;
		biggestHeight=yCord;
		
	}
}
