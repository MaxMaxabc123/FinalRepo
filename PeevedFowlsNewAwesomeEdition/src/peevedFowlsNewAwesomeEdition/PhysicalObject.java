package peevedFowlsNewAwesomeEdition;



public abstract class PhysicalObject 
{
	// a physical object has a x Coordinate 
	int xCoordinate;
	// a physical object has a y Coordinate 
	int yCoordinate;
	// a physical object has a size
	int sizeOfObject;
	// a physical object has a coordinate
	Coordinate coordinatePhysics;
	// returns the coordinate made from the x and y coordinates of the object
	public Coordinate getCoordinate()
	{
		coordinatePhysics = new Coordinate(xCoordinate,yCoordinate);
		return coordinatePhysics;
	}
}
