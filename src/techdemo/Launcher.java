package techdemo;

import gameframe.*;

public class Launcher extends Actor 
{
	// Values used to initialize ball projectiles launched by the Launcher. 
	public double ballMass = 1.0;
	public double ballCharge = 0.0;
	public double ballSpeed = 2.0;
	
	public Launcher ()
	{
		super("launcher", false, 32.0, 32.0, 1, "player", new Vec2D(0.0, 0.0), expire_never, 
				GF_Tech_Demo.getCurrFrame(), false, true, true, true, false, true, null, 1.5);
	}
	
	@Override
	public void routine()
	{
		if(GF_Tech_Demo.checkButton('W', GF_Tech_Demo.HOLD))
			selfAccel(new Vec2D(new Coord(0.0, -0.01)));
		
		if(GF_Tech_Demo.checkButton('S', GF_Tech_Demo.HOLD))
			selfAccel(new Vec2D(new Coord(0.0, 0.01)));
		
		if(GF_Tech_Demo.checkButton(GF_Tech_Demo.KeyShift, GF_Tech_Demo.HOLD))
			velocity = new Vec2D(0.0, 0.0);
	}
}
