package techdemo;

/*
 * The DiamondManager class is an ActorManager that creates Diamond objects. 
 */

import jgame.JGPoint;
import gameframe.*;

public class DiamondManager extends ActorManager 
{
	// ActorManager will use SET_INTERVAl as its spawn mode. Instructions for this spawn mode are
	// set through the contructor. 
	public DiamondManager(String id, int max, int wait, JGPoint loc)
	{
		super(id, // String ID (non unique) of ActorManager.
				max, // Maximum number of Diamond objects it may have created at once. 
				SET_INTERVAL, // Spawn mode is set to spawn Diamonds at set intervals of time. 
				new int[] {wait, // Time to wait between spawning Diamonds.
					loc.x, loc.y // Coordinate to spawn Diamond objects at. 
				});
	}
	
	// Spawn method of an ActorManager must be overridden to determine how the ActorManager spawns
	// objects.
	@Override
	public void spawn() 
	{
		if(spawnMode == SET_INTERVAL) // If the spawn mode is SET_INTERVAL
		{
			// If the amount of wait time between spawning objects has passed and the number of 
			// objects created is lower than the maximum population and the number of Actor objects
			// is lower than max actors, then create a new Diamond object.
			if(elapsed()%spawnInstruct[0] == 0 && list.size() < maxPop 
				&& Actor.getCount() < Actor.maxActors)
			{
				// Create a new Diamond object with a random initial velocity somewhere between
				// facing down and facing left. 
				Vec2D randVel = new Vec2D(180.0 - Math.random()*90.0, 1.0);
				list.add(new Diamond(spawnInstruct[1], spawnInstruct[2], randVel, this));
			}
		}
	}
}
