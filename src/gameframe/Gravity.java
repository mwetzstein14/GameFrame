package gameframe;

/*
 * The Gravity class is a static class (from which objects cannot be instantiated) that is used to
 * control the acceleration due to gravity (a constant called 'g' in physics) that influences all
 * RBObjects created (as long as those RBObjects have gravitate set to true). RBObjects are 
 * responsible for actually adding g to their acceleration. The Gravity class is just a way to easily
 * control gravity settings for RBObjects. 
 */

public abstract class Gravity 
{
	public static boolean gravOn = false; // Set to true for RBObjects to be affected by gravity.
	                                      // Set to false to turn off gravity. 
	
	public static Vec2D g = new Vec2D(new Coord(0, -1)); // Vector representing magnitude and 
	                                                     // direction of g. 
	
	// Used to set g to another vector.
	public static void set_g(Vec2D vec)
	{
		g = vec;
	}
	
	// Used to get the acceleration vector g. 
	public static Vec2D get_g()
	{
		if(gravOn) // Return g if gravity is on.
			return g;
		else // Return a zero vector if gravity is off. 
			return new Vec2D(0.0, 0.0);
	}
}
