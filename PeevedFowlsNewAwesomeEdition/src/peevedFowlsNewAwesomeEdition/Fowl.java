package peevedFowlsNewAwesomeEdition;

public class Fowl extends PhysicalObject
{
	// a bird has a set speed;
	int speedOfBird=5;
	// making a fowl with a x and ycoordinate, size and a speed
	public Fowl(int x,int y, int size)
	{
		xCoordinate=x;
		yCoordinate=y;
		sizeOfObject=size;

	}
	// moves the fowl around the world in the positive x direction
	public void moveForwardX()
	{
		xCoordinate+=speedOfBird;
	}
	// moves the fowl around the world in the positive y direction
	public void moveForwardY()
	{
		yCoordinate+=speedOfBird;
	}
	// moves the fowl around the world in the negative x direction
	public void moveBackwardX()
	{
		xCoordinate-=speedOfBird;
	}
	// moves the fowl around the world in the negative y direction
	public void moveBackwardY()
	{
		yCoordinate-=speedOfBird;
	}
	
}
