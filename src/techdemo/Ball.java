package techdemo;

// Example of how the Projectile class is used. The ball is a good candidate for a Projectile 
// because it is an object created by the Launcher class as a means to interact with other Actors.

import gameframe.*;

public class Ball extends Projectile
{
	// Constructor allows one to set the projectile's initial position, velocity, mass, and charge.
	public Ball(double x, double y, Vec2D vel, double mass, double charge)
	{
		super("ball", // String ID used to find object through JGEngine.
				true, // Create unique instances of this class (do not replace last created instance).
				x, y, // Starting x and y position of ball.
				8, 	  // Collision ID.
				"ball", // String name of sprite to use for object. 
				vel, 	// Initial velocity of ball.
				450, 	// Number of frames until object expires. 
				GF_Tech_Demo.getCurrFrame(), // Frame which object was created.
				mass, 	// Mass of ball.
				charge, // Electric charge of ball.
				1.0, 	// Partial coefficient of friction.
				0.8, 	// Partial elasticity of collision, loses speed in collision.
				true, 	// Should be affected by gravity.
				false,  // Does not insulate charge.
				false,  // Does not pass through other RBObjects in collisions unaffected.
				false,  // Does not pass through ForceFields unaffected.
				false,  // Does not pass through tiles unaffected. 
				true    // Is able to move. 
				);
	}
	
	// The move method is overridden to trigger an animation when the ball is about to expire. 
	@Override
	public void move()
	{
		// If ball is about to expire soon.
		if(beforeCollisions() && elapsed() == 150)
		{
			// Start a flashing animation, as if ball is fading away. 
			setAnim("fade_ball");
			startAnim();
		}
		
		super.move(); // As convention, call super.move() at the end. 
	}
}
