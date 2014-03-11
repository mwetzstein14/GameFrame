package techdemo;

/*
 * Particle used to create explosion effects when a ball object hits and destroys a Diamond.
 * Very similar to the Debris Particle. Look at the Debris Particle for information on Constructor
 * parameters.
 */

import gameframe.Particle;

public class Explosion extends Particle 
{
	public Explosion(double x, double y)
	{
		super("explosion", true, x, y, 0, "explosion", expire_off_view, GF_Tech_Demo.getCurrFrame(),
				1.0, 0.0, 0.0, 1.0, false, true, true, true, true, false, 8);
	}
}
