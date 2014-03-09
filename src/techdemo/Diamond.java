package techdemo;

import jgame.JGObject;
import gameframe.*;

public class Diamond extends Actor 
{
	public Diamond(double x, double y, Vec2D vel, DiamondManager manager)
	{
		super("diamond", true, x, y, 16, "diamond", vel, expire_off_view, GF_Tech_Demo.getCurrFrame(),
				5.0, 2.0, 1.0, 1.0, false, true, false, false, false, true, 
				manager.getID(), 1.0);
	}
	
	@Override
	public void routine()
	{
		if(GF_Tech_Demo.getCurrFrame()%450 == 0 && getState() == 0)
		{
			Vec2D randAccel = new Vec2D(random(0, 360), 1.0);
			selfAccel(randAccel);
		}
		
		if(getState() == 1 && stateTime() == 90)
			destruction();
	}
	
	@Override
	public void setState(int newState)
	{
		if(newState == 1 && getState() != 1)
		{
			setImage("diamond_dead");
			gravitate = true;
			mass = 3.0;
			new Explosion(x, y);
		}
		
		super.setState(newState);
	}
	
	@Override
	public void destruction()
	{
		new Debris(x + 7.0, y + 7.0, new Vec2D(0.0, 0.25));
		new Debris(x + 7.0, y + 7.0, new Vec2D(180.0, 0.25));
		
		GF_Tech_Demo.score += 50;
		
		super.destruction();
	}
	
	@Override
	public void hit(JGObject obj)
	{
		if(obj instanceof Ball)
			setState(1);
		
		super.hit(obj);
	}
}
