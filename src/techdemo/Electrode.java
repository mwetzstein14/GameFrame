package techdemo;

import gameframe.Actor;
import gameframe.Vec2D;

public class Electrode extends Actor 
{
	ElectricField eField;
	
	public Electrode(double x, double y, Vec2D vel, ElectrodeManager manager)
	{
		super("electrode", true, x, y, 32, "magnet", vel, expire_off_view, GF_Tech_Demo.getCurrFrame(),
				5.0, -3.0, 1.0, 1.0, false, true, false, false, false, true, 
				manager.getID(), 1.0);
		
		eField = new ElectricField(this);
	}
	
	@Override
	public void routine()
	{
		if(GF_Tech_Demo.getCurrFrame()%450 == 0)
		{
			Vec2D randAccel = new Vec2D(random(0, 360), 1.0);
			selfAccel(randAccel);
		}
	}
}
