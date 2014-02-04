package gameframe;

/*
 *  Coord is a class used to represent a point whose coordinates are decimals (type double).
 *  This class is useful for temporarily storing the location of a JGObject in Coord and
 *  for creating Vec2D objects using coordinates. 
 */

public class Coord 
{
	public double x; // X-coordinate, may access directly to change value.
	public double y; // Y-coordinate, may access directly to change value.
	
	// Constructor that sets both the x and y coordinates.
	public Coord(double xCoord, double yCoord)
	{
		x = xCoord;
		y = yCoord;
	}
	
	// Allows you to reset the values of both the x and y coordinates at the same time. 
	public void setXY(double xCoord, double yCoord)
	{
		x = xCoord;
		y = yCoord;
	}
}
