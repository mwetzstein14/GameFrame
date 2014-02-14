package gameframe;

/* 
 * The Vec2D class is a class used to represent a vector quantity (a quantity with both a magnitude
 * and direction). The class supports many methods for modifying the vector and performing vector
 * operations.
 * Zero degrees and zero radians both point to the right.
 */

public class Vec2D 
{
	/* Set to false to use degrees when passing angles as parameters for Vec2D methods.
	 * Set to true to use radians when passing angles as parameters for Vec2D methods.
	 * This setting is static and applies to all Vec2D objects. The default setting is degrees. 
	 */ 
	public static boolean useRadians = false;
	
	private double x_component; // Magnitude of the x-component of the vector.
	private double y_component; // Magnitude of the y-component of the vector.
	
	// Constructor that takes a single Coord. Creates a vector pointing from the origin to the point
	// represented by the Coord. The distance from the origin to point is the magnitude. 
	public Vec2D(Coord point)
	{
		setVec(point);
	}
	
	// Constructor that takes two Coord objects. Creates a vector pointing from the point represented
	// by the start Coord to the point represented by the end Coord. The distance between the two
	// points is the magnitude.
	public Vec2D(Coord start, Coord end)
	{
		setVec(start, end);
	}
	
	// Constructor that takes an angle and a magnitude. Creates a vector pointing in the direction
	// represented by the angle (angle) and with magnitude mag. 
	public Vec2D(double angle, double mag)
	{
		setVec(angle, mag);
	}
	
	// Sets the vector to be pointing from the origin to the point represented by the Coord.
	// The distance from the origin to point is the magnitude.
	public void setVec(Coord point)
	{
		x_component = point.x;
		y_component = point.y;
	}
	
	// Sets the vector to be pointing from the point represented by the start Coord to the point
	// represented by the end Coord. The distance between the two points is the magnitude. 
	public void setVec(Coord start, Coord end)
	{
		x_component = end.x - start.x;
		y_component = end.y - start.y;
	}
	
	// Sets the vector to be pointing in the direction represented by the angle (angle) and with 
	// magnitude mag. 
	public void setVec(double angle, double mag)
	{
		if (useRadians) // If using radians:
		{
			x_component = Math.cos(angle);
			y_component = Math.sin(angle);
		}
		else // If using degrees:
		{
			x_component = Math.cos(Math.toRadians(angle));
			y_component = Math.sin(Math.toRadians(angle));
		}
		
		scaleMag(mag); // Sets magnitude. 
	}
	
	// Changes the magnitude of the vector to mag. Keeps the same direction.
	public void changeMag(double mag)
	{
		normalize();
		scaleMag(mag);
	}
	
	// Changes the vector to point in the direction represented by angle. Keeps the same magnitude.
	public void changeDir(double angle)
	{
		setVec(angle, getMag());
	}
	
	// Changes the vector to point from the origin to the point represented by Coord.
	// Keeps the same magnitude.
	public void changeDir(Coord point)
	{
		Vec2D temp = new Vec2D(point);
		
		changeDir(temp.getAngle());
	}
	
	// Changes the vector to point in the direction from the point represented by start to the point
	// represented by end. Keeps the same magnitude. 
	public void changeDir(Coord loc, Coord target)
	{
		Vec2D temp = new Vec2D(loc, target);
		
		changeDir(temp.getAngle());
	}
	
	// Multiply the current magnitude by scalar. 
	public void scaleMag(double scalar)
	{
		x_component *= scalar;
		y_component *= scalar;
	}
	
	// Add angle (argument) to the current angle of the vector's direction. 
	public void addDir(double angle)
	{
		changeDir(getAngle() + angle);
	}
	
	// Performs vector addition. Adds vec to this vector. 
	public void addVec(Vec2D vec)
	{
		x_component += vec.getXComp();
		y_component += vec.getYComp();
	}
	
	// Performs vector subtracting. Subtracts vec from this vector.
	public void subtractVec(Vec2D vec)
	{
		x_component -= vec.getXComp();
		y_component -= vec.getYComp();
	}
	
	// Sets the magnitude of the vector to one. Keeps same direction.
	public void normalize()
	{
		x_component /= getMag();
		y_component /= getMag();
	}
	
	// Returns a Vec2D vector pointing in the same direction as the vector with magnitude one. 
	public Vec2D getNormal()
	{
		Vec2D temp = new Vec2D(new Coord(x_component, y_component));
		temp.normalize();
		
		return temp;
	}
	
	// Returns a Vec2D vector pointing in the opposite direction as the vector with the same magnitude.
	public Vec2D getInverse()
	{
		return new Vec2D(new Coord(-x_component, -y_component));
	}
	
	// Returns a Vec2D vector with the same magnitude and direction as the x_component of the vector.
	public Vec2D getXVec()
	{
		return new Vec2D(new Coord(x_component, 0.0));
	}
	
	// Returns a Vec2D vector with the same magnitude and direction as the y_component of the vector.
	public Vec2D getYVec()
	{
		return new Vec2D(new Coord(0.0, y_component));
	}
	
	// Returns the magnitude of the vector.
	public double getMag()
	{
		return Math.sqrt(Math.pow(x_component, 2.0) + Math.pow(y_component, 2.0));
	}
	
	// Returns the x-component of the vector.
	public double getXComp()
	{
		return x_component;
	}
	
	// Returns the y-component of the vector.
	public double getYComp()
	{
		return y_component;
	}
	
	// Returns the angle that the vector is pointing in.
	public double getAngle()
	{
		// Angle shifted to first quadrant of unit circle. 
		double angle = Math.abs(Math.atan(y_component/x_component));  
			
		if(x_component >= 0.0 && y_component >= 0.0) // If angle really is in first quadrant.
		{
			if(useRadians) // If using radians:
				return angle;
			else
				return Math.toDegrees(angle);
		}
		else if(x_component <= 0.0 && y_component >= 0.0) // If angle really is in second quadrant. 
		{
			if(useRadians) // If using radians:
				return angle + 2.0*(Math.PI/2.0 - angle);
			else
				return Math.toDegrees(angle + 2.0*(Math.PI/2.0 - angle));
		}
		else if(x_component <= 0.0 && y_component <= 0.0) // If angle really is in third quadrant.
		{
			if(useRadians) // If using radians:
				return angle + Math.PI;
			else
				return Math.toDegrees(angle + Math.PI);
		}
		else // If angle really is in fourth quadrant.
		{
			if(useRadians) // If using radians:
				return 2.0*Math.PI - angle;
			else
				return Math.toDegrees(2.0*Math.PI - angle);
		}
	}
	
	// Returns the angle between the vector and vec.
	public double angleWithVec(Vec2D vec)
	{
		if(useRadians) // If using radians:
			return Math.acos(dot(vec)/(getMag()*vec.getMag()));
		else // If using degrees:
			return Math.toDegrees(Math.acos(dot(vec)/(getMag()*vec.getMag())));
	}
	
	// Returns the size of the acute angle between the vector and a horizontal line passing through
	// its tail.
	public double angleWithHor()
	{
		double angle = angleWithVec(new Vec2D(new Coord(1.0, 0.0))); // Angle between this vector and
		// another vector pointing right.
		
		if(useRadians) // If using radians:
		{
			if(angle > Math.PI/2.0) // Make angle the acute angle with horizontal if it is not. 
				return Math.PI - angle;
			else
				return angle;
		}
		else // If using degrees:
		{
			if(angle > 90.0) // Make angle the acute angle with horizontal if it is not.
				return 180.0 - angle;
			else
				return angle;
		}
	}
	
	// Returns the size of the acute angle between the vector and a vertical line passing through
	// its tail.
	public double angleWithVert()
	{
		double angle = angleWithVec(new Vec2D(new Coord(0.0, 1.0))); // Angle between this vector and
		// another vector pointing up.
		
		if(useRadians) // If using radians:
		{
			if(angle > Math.PI/2.0) // Make angle the acute angle with vertical if it is not. 
				return Math.PI - angle;
			else
				return angle;
		}
		else // If using degrees:
		{
			if(angle > 90.0) // Make angle the acute angle with vertical if it is not.
				return 180.0 - angle;
			else
				return angle;
		}
	}
	
	// Returns the dot product between the vector and vec.
	public double dot(Vec2D vec)
	{
		return x_component * vec.getXComp() + y_component * vec.getYComp();
	}
	
	// Returns the magnitude of the cross product between the vector and vec. (Note: cross product
	// only makes sense with 3D vectors, so this function assumes that the z-component of both
	// vectors is zero. This function cannot return the vector result of the cross product because it
	// would point out of or into the screen). 
	public double crossMag(Vec2D vec)
	{
		return x_component * vec.getYComp() - y_component * vec.getXComp();
	}
	
	// Returns a vector that is an exact copy of this vector. 
	public Vec2D copy()
	{
		return new Vec2D(new Coord(x_component, y_component));
	}
}
