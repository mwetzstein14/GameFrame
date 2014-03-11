package techdemo;

import gameframe.*;

public class Launcher extends Actor 
{
	// Values used to initialize ball projectiles launched by the Launcher. 
	private double ballMass = 1.0;
	private double ballCharge = 0.0;
	private double ballSpeed = 1.0;
	
	private final int MASS = 1;
	private final int CHARGE = 2;
	private final int SPEED = 3;
	private int select = MASS;
	
	// Sight for aiming balls.
	private Aim sight;
	
	public Launcher ()
	{
		super("launcher", false, 32.0, 32.0, 1, "player", new Vec2D(0.0, 0.0), expire_never, 
				GF_Tech_Demo.getCurrFrame(), false, true, true, true, false, true, null, 1.5);
		
		sight = new Aim(this);
	}
	
	public double getBallMass()
	{
		return ballMass;
	}
	
	public double getBallCharge()
	{
		return ballCharge;
	}
	
	public double getBallSpeed()
	{
		return ballSpeed;
	}
	
	private void launchBall()
	{
		if(GF_Tech_Demo.mouseY > 16)
		{
			Vec2D launchVel = new Vec2D(new Coord(x + 7.0, y + 7.0), 
					new Coord(GF_Tech_Demo.mouseX, GF_Tech_Demo.mouseY));
			launchVel.changeMag(ballSpeed);
		
			new Ball(x + 2.0, y + 2.0, launchVel, ballMass, ballCharge);
		}
	}
	
	private void setBall()
	{
		if(GF_Tech_Demo.checkButton('M', GF_Tech_Demo.PRESS))
			select = MASS;
		if(GF_Tech_Demo.checkButton('C', GF_Tech_Demo.PRESS))
			select = CHARGE;
		if(GF_Tech_Demo.checkButton('V', GF_Tech_Demo.PRESS))
			select = SPEED;
		
		if(GF_Tech_Demo.checkButton(GF_Tech_Demo.KeyUp, GF_Tech_Demo.HOLD))
		{
			if(select == MASS && ballMass + 0.05 < 10.05)
				ballMass += 0.05;
			if(select == CHARGE && ballCharge + 0.05 < 10.05)
				ballCharge += 0.05;
			if(select == SPEED && ballSpeed + 0.05 < 10.5)
				ballSpeed += 0.05;
		}
		
		if(GF_Tech_Demo.checkButton(GF_Tech_Demo.KeyDown, GF_Tech_Demo.HOLD))
		{
			if(select == MASS && ballMass - 0.05 > 0)
				ballMass -= 0.05;
			if(select == CHARGE && ballCharge - 0.05 > -10.05)
				ballCharge -= 0.05;
			if(select == SPEED && ballSpeed - 0.05 > 0)
				ballSpeed -= 0.05;
		}
	}
	
	@Override
	public void routine()
	{
		setBall();
		
		if(GF_Tech_Demo.checkButton(GF_Tech_Demo.KeyMouse1, GF_Tech_Demo.PRESS))
			launchBall();
		
		if(GF_Tech_Demo.checkButton('W', GF_Tech_Demo.HOLD))
			selfAccel(new Vec2D(new Coord(0.0, -0.01)));
		
		if(GF_Tech_Demo.checkButton('S', GF_Tech_Demo.HOLD))
			selfAccel(new Vec2D(new Coord(0.0, 0.01)));
		
		if(GF_Tech_Demo.checkButton(GF_Tech_Demo.KeyShift, GF_Tech_Demo.HOLD))
			velocity = new Vec2D(0.0, 0.0);
	}
	
	@Override 
	public void destroy()
	{
		sight.remove();
		
		super.destroy();
	}
	
	class Aim extends Extension
	{
		double lastMouseX = 0.0;
		double lastMouseY = 0.0;
		
		public Aim(Launcher player)
		{
			super("aim", false, 8.0, 20.0, 0, "null", expire_never, player);
		}
		
		@Override
		protected void updateOffset()
		{
			Vec2D loc = new Vec2D(new Coord(master.x + 7.0, master.y + 7.0), 
					new Coord(GF_Tech_Demo.mouseX, GF_Tech_Demo.mouseY));
			loc.changeMag(20.0);
			
			xOffset = loc.getXComp() + 7.0;
			yOffset = loc.getYComp() + 7.0;
			
			if(lastMouseX == GF_Tech_Demo.mouseX && lastMouseY == GF_Tech_Demo.mouseY)
				setState(0);
			else
				setState(1);
			
			lastMouseX = GF_Tech_Demo.mouseX;
			lastMouseY = GF_Tech_Demo.mouseY;
		}
		
		@Override
		public void setState(int newState)
		{
			if(state == 0)
				setImage("null");
			else
				setImage("sight");
			
			super.setState(newState);
		}
	}
}
