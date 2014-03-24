package techdemo;

/*
 * The RailingSurface class is like the SteelSurface class, except it is a background surface and
 * implements the onContact() method to create a continual effect while an object is in contact with
 * it. Look at the SteelSurface class for explanations to some of the constructor parameters. 
 */

import gameframe.rbs.Particle;
import gameframe.rbs.RBObject;
import gameframe.surface.Surface;
import gameframe.vecmath.Vec2D;

public class RailingSurface extends Surface 
{
	public RailingSurface(int[] cids)
	{
		super(cids, 0.2, 0.1, 1.0, 
				true // background Surface.
				);
	}
	
	// This method is overridden to create sparks when the launcher is moving too fast on the
	// railing. 
	@Override
	public void onContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		// Every three frames, if the RBObject is the launcher and the number of Particles created
		// is less than maxParticles. 
		if(rb instanceof Launcher && Particle.getCount() <= Particle.maxParticles 
				&& GF_Tech_Demo.getCurrFrame()%3 == 0)
		{
			// If the speed of the launcher is greater than 0.3.
			if(Math.abs(rb.yspeed) > 0.3)
			{
				// Select a random corner to generate the spark at.
				int corner = (int)rb.random(1, 5);
				// Pick a random angle of velocity adjustment. 
				int angleAdjust = (int)(Math.random()*30);
				if(Math.random() > 0.5)
					angleAdjust *= -1.0;
			
				// Create a new Spark with an initial velocity away from the railing at the 
				// randomly selected corner of the launcher.
				if(corner == 1)
				{
					Vec2D pVel = new Vec2D(225 + angleAdjust, 0.5);
					new Spark(rb.x + 2, rb.y, pVel);
				}
				else if(corner == 2)
				{
					Vec2D pVel = new Vec2D(315 + angleAdjust, 0.5);
					new Spark(rb.x + 13, rb.y, pVel);
				}
				else if(corner == 3)
				{
					Vec2D pVel = new Vec2D(135 + angleAdjust, 0.5);
					new Spark(rb.x + 2, rb.y + 15, pVel);
				}
				else
				{
					Vec2D pVel = new Vec2D(45 + angleAdjust, 0.5);
					new Spark(rb.x + 13, rb.y + 15, pVel);
				}
			}
		}
	}
}
