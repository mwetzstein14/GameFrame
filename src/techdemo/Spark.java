package techdemo;

/*
 * Particle used to create spark effects when the launcher object slides too fast on the rails.
 * Very similar to the Debris Particle. Look at the Debris Particle for information on Constructor
 * parameters.
 */

import gameframe.*;

public class Spark extends Particle 
{
	public Spark(double x, double y, Vec2D vel)
	{
		super("spark", true, x, y, 2, "spark", vel, expire_off_view, GF_Tech_Demo.getCurrFrame(),
				0.1, 1, 0.1, 0, true, true, true, false, true, true, 30);
	}
}
