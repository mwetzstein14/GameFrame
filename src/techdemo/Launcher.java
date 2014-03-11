package techdemo;

/*
 * The Launcher is an example of an Actor which uses an extension. The extension is a green dot that
 * appears when the mouse is moving to indicate the direction that a ball will launch. The extension
 * does not affect the collisions of the Launcher. 
 */

import gameframe.*;

public class Launcher extends Actor 
{
	// Values used to initialize properties of ball projectiles launched by the Launcher. 
	private double ballMass = 1.0;
	private double ballCharge = 0.0;
	private double ballSpeed = 1.0;
	
	// Used to select which ball property is being adjusted by arrow keys. 
	private final int MASS = 1;
	private final int CHARGE = 2;
	private final int SPEED = 3;
	private int select = MASS;
	
	// The Extension object, a sight for aiming balls.
	private Aim sight;
	
	public Launcher ()
	{
		// See Diamond for an explanation of constructor arguments. 
		super("launcher", false, 32.0, 32.0, 1, "player", new Vec2D(0.0, 0.0), expire_never, // Game object never expires.
				GF_Tech_Demo.getCurrFrame(), false, true, true, true, false, true, null, 1.5);
		
		sight = new Aim(this); // Initialize the Extension object.
	}
	
	// Getter methods for ball properties. 
	
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
	
	// Method spawns and launches a ball towards the coordinate of the mouse. 
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
	
	// Method called to adjust values of ball properties based on user input. Maximum values are
	// 10, minimum are 0 (or -10 for charge). 
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
	
	// Override the routine method to launch balls and accelerate object based on user input. 
	@Override
	public void routine()
	{
		setBall(); // Adjust ball properties if necessary. 
		
		// If left mouse button is pressed, then launch a ball. 
		if(GF_Tech_Demo.checkButton(GF_Tech_Demo.KeyMouse1, GF_Tech_Demo.PRESS))
			launchBall();
		
		// Below are examples of checking whether a button is being held down in GameFrame. 
		
		// Accelerate up if 'W' is held down. 
		if(GF_Tech_Demo.checkButton('W', GF_Tech_Demo.HOLD))
			selfAccel(new Vec2D(new Coord(0.0, -0.01)));
		
		// Accelerate down if 'S' is held down. 
		if(GF_Tech_Demo.checkButton('S', GF_Tech_Demo.HOLD))
			selfAccel(new Vec2D(new Coord(0.0, 0.01)));
		
		// Brake (stop in place) if shift key is held down. 
		if(GF_Tech_Demo.checkButton(GF_Tech_Demo.KeyShift, GF_Tech_Demo.HOLD))
			velocity = new Vec2D(0.0, 0.0);
	}
	
	// Destroy method is called when JGEngine removes a game object from the game. Method is 
	// overridden so that the object's Extension is removed along with it. 
	@Override 
	public void destroy()
	{
		sight.remove();
		
		super.destroy(); // As is convention, don't forget to call the super at the end. 
	}
	
	// This subclass is the Extension class used for the green sight. 
	class Aim extends Extension
	{
		// Keep track of the mouse's position last frame. 
		double lastMouseX = 0.0;
		double lastMouseY = 0.0;
		
		public Aim(Launcher player)
		{
			super("aim", // String ID used to find object through JGEngine.
					false, // Do not create unique object (replace last sight object created). 
					8.0, // Initial x offset from the Extension's master (the launcher).
					20.0, // Initial y offset from the Extension's master (the launcher).
					0,    // Collision ID.
					"null", // String name of sprite to use for object (happens to be an invisible image).
					expire_never, // Game object never expires.
					player	// Master object to follow on screen at given offset. 
					);
		}
		
		// Override this method to control how the offset from the master object of the Extension 
		// changes. 
		@Override
		protected void updateOffset()
		{
			// Create a position vector pointing in the direction from the master object to the 
			// current mouse coordinates. 
			Vec2D loc = new Vec2D(new Coord(master.x + 7.0, master.y + 7.0), 
					new Coord(GF_Tech_Demo.mouseX, GF_Tech_Demo.mouseY));
			loc.changeMag(20.0); // Change the magnitude to 20. 
			
			// Use the components of the position vector to position offset from center of master
			// object (the launcher).
			xOffset = loc.getXComp() + 7.0;
			yOffset = loc.getYComp() + 7.0;
			
			// If the mouse hasn't moved since last frame, set the Extension to the invisible state.
			if(lastMouseX == GF_Tech_Demo.mouseX && lastMouseY == GF_Tech_Demo.mouseY)
				setState(0);
			else // If the mouse is moving, set it to the visible state. 
				setState(1);
			
			// Update the coordinates of the mouse last frame. 
			lastMouseX = GF_Tech_Demo.mouseX;
			lastMouseY = GF_Tech_Demo.mouseY;
		}
		
		// This method is overridden to update the image of the extension to either be visible or
		// invisible depending on its current state. 
		@Override
		public void setState(int newState)
		{
			if(state == 0)
				setImage("null");
			else
				setImage("sight");
			
			super.setState(newState); // As is convention, don't forget to call the super at the end. 
		}
	}
}
