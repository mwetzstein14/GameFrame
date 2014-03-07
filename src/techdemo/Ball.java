package techdemo;

import gameframe.*;

public class Ball extends Projectile
{
	public Ball(double x, double y, Vec2D vel, double mass, double charge)
	{
		super("ball", true, x, y, 8, "ball", vel, 450, GF_Tech_Demo.getCurrFrame(),
				mass, charge, 1.0, 0.8, true, false, false, false, false, true);
	}
	
	@Override
	public void move()
	{
		if(beforeCollisions() && elapsed() == 150)
		{
			setAnim("fade_ball");
			startAnim();
		}
		
		super.move();
	}
}
