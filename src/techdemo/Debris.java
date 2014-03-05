package techdemo;

import gameframe.*;

public class Debris extends Particle 
{
	public Debris(double x, double y, Vec2D vel)
	{
		super("debris", true, x, y, 4, "debris", vel, expire_off_view, GF_Tech_Demo.getCurrFrame(),
				1, 0, 0.5, 0.7, true, false, false, false, false, true, 180);
	}
}
