package techdemo;

import gameframe.*;

public class Spark extends Particle 
{
	public Spark(double x, double y, Vec2D vel)
	{
		super("spark", true, x, y, 2, "spark", vel, expire_off_view, GF_Tech_Demo.getCurrFrame(),
				0.1, 1, 0.1, 0, true, true, true, false, true, true, 30);
	}
}
