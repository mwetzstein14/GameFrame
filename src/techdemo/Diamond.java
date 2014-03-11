package techdemo;

/*
 * The diamond is an enemy that the player interacts with and must destroy. Diamonds respawn 
 * periodically. It is a good example of an object that would extend the Actor class. 
 */

import jgame.JGObject;
import gameframe.*;

public class Diamond extends Actor 
{
	// Constructor sets initial position, velocity, and requires a reference to the ActorManager that
	// created it. 
	public Diamond(double x, double y, Vec2D vel, DiamondManager manager)
	{
		super("diamond", // String ID used to find object through JGEngine.
				true, // Create unique instances of this class (do not replace last created instance).
				x, y, // Starting x and y position of debris.
				16,   // Collision ID.
				"diamond", // String name of sprite to use for object.
				vel, 	   // Initial velocity of Diamond.
				expire_off_view, // Object set to expire when it goes off screen. 
				GF_Tech_Demo.getCurrFrame(), // Frame which object was created.
				5.0, 	// Mass of Diamond.
				2.0, 	// Electric charge of Diamond.
				1.0, 	// Partial coefficient of friction.
				1.0, 	// Partial elasticity of collision, maintains speed in collision.
				false, 	// Should not be affected by gravity.
				true, 	// Insulates charge.
				false,  // Does not pass through other RBObjects in collisions unaffected.
				false,  // Does not pass through ForceFields unaffected.
				false,  // Does not pass through tiles unaffected.
				true,   // Is able to move. 
				manager.getID(), // String ID of ActorManager that created this object.
				1.0  			 // Maximum speed object may induce on itself.
				);
	}
	
	// Routine method is overridden so that the Diamond periodically accelerates itself in a random
	// direction. 
	@Override
	public void routine()
	{
		if(GF_Tech_Demo.getCurrFrame()%450 == 0 && getState() == 0) // Every ten seconds and if not in
																    // destroyed state. 
		{
			// Randomly accelerate in any direction. 
			Vec2D randAccel = new Vec2D(random(0, 360), 1.0);
			selfAccel(randAccel);
		}
		
		// If it has been in the destroyed state for two seconds, call destruction method. 
		if(getState() == 1 && stateTime() == 90)
			destruction();
	}
	
	// Overridden to update properties of the Diamond when changing to destroyed state.
	@Override
	public void setState(int newState)
	{
		// If switching to destroyed state.
		if(newState == 1 && getState() != 1)
		{
			setImage("diamond_dead"); // Change to wreckage sprite.
			gravitate = true; // Become affected by gravity.e
			mass = 3.0;		  // Lose mass.
			new Explosion(x, y); // Create explosion particle effect. 
		}
		
		super.setState(newState); // Don't forget to call the super at the end, as is convention. 
	}
	
	// Destruction method is overridden to create two debris objects upon the object's removal.
	// Also increments the player's score. 
	@Override
	public void destruction()
	{
		// Create two new debris obejcts.
		new Debris(x + 7.0, y + 7.0, new Vec2D(0.0, 0.25));
		new Debris(x + 7.0, y + 7.0, new Vec2D(180.0, 0.25));
		
		GF_Tech_Demo.score += 50; // Add to player's score. 
		
		super.destruction(); // Don't forget to call the super at the end, as is convention. 
	}
	
	// hit method is overridden so that when the Diamond collides with a ball, it enters the 
	// destoryed state (state 1). 
	@Override
	public void hit(JGObject obj)
	{
		if(obj instanceof Ball)
			setState(1);
		
		super.hit(obj); // Don't forget to call the super at the end, as is convention. 
	}
}
