package techdemo;

import gameframe.*;

public class RailingSurface extends Surface 
{
	public RailingSurface(int[] cids)
	{
		super(cids, 0.2, 0.1, 1.0, true);
	}
	
	/*
	@Override
	public void onContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		if(rb instanceof Launcher && Particle.getCount() <= Particle.maxParticles 
				&& GF_Tech_Demo.getCurrFrame()%3 == 0)
		{
			if(Math.abs(rb.yspeed) > 0.3)
			{
				int corner = (int)rb.random(1, 5);
				int angleAdjust = (int)Math.random()*30;
			
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
	*/
}
