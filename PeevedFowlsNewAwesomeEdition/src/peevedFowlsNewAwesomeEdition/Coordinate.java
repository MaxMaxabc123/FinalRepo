package peevedFowlsNewAwesomeEdition;

public class Coordinate 
{
	// a coordinate needs a x coordinate componenet
	int xCoordinate;
	// a coordinate needs a y coordinate componenet
	int yCoordinate;
	// a coordinate has a occupying physics object
	PhysicalObject occupyingObject=null;
	// a coordinate is made with 2 coordinates;
	public Coordinate(int x,int y)
	{
		xCoordinate=x;
		yCoordinate=y;
	}
	// returns the current x coord
	public int getXCoord() {
		// TODO Auto-generated method stub
		return xCoordinate;
	}
	// returns the current y cord
	public int getYCoord()
	{
		return yCoordinate;
	}
}
