package techdemo;

/* 
 * The Debris object is a good example of a Particle because it is a temporary object used for 
 * special effect. Object is spawned when things fall apart or walls are hit by fast objects. 
 */

import gameframe.rbs.Particle;
import gameframe.vecmath.Vec2D;

public class Debris extends Particle 
{
	// Constructor sets initial position and velocity. 
	public Debris(double x, double y, Vec2D vel)
	{
		super("debris", // String ID used to find object through JGEngine.
				true, // Create unique instances of this class (do not replace last created instance).
				x, y, // Starting x and y position of debris.
				4, 	  // Collision ID.
				"debris", // String name of sprite to use for object. 
				vel, 	  // Initial velocity of debris.
				expire_off_view, // Object set to expire when it goes off screen. 
				GF_Tech_Demo.getCurrFrame(), // Frame which object was created.
				1, 	// Mass of debris.
				0,  // Electric charge of debris.
				0.5, // Partial coefficient of friction.
				0.7, // Partial elasticity of collision, loses speed in collision.
				true, // Should be affected by gravity.
				false, // Does not insulate charge.
				false, // Does not pass through other RBObjects in collisions unaffected.
				false, // Does not pass through ForceFields unaffected.
				false, // Does not pass through tiles unaffected. 
				true, // Is able to move. 
				180	  // Number of frames until particle must be removed from game. 
				);
	}
}
