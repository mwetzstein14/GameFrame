package techdemo;

/*
 * The ElectrodeManager class is very similar to the DiamondManager, except that instead of creating
 * Diamond objects, it creates Electrode objects. Look at the Diamond class for information about 
 * some methods or constructor arguments. 
 */

import jgame.JGPoint;
import gameframe.ActorManager;
import gameframe.rbs.Actor;
import gameframe.vecmath.Vec2D;

public class ElectrodeManager extends ActorManager 
{
	public ElectrodeManager(String id, int max, int wait, JGPoint loc)
	{
		super(id, max, SET_INTERVAL, new int[] {wait, loc.x, loc.y});
	}
	
	@Override
	public void spawn() 
	{
		if(spawnMode == SET_INTERVAL)
		{
			if(elapsed()%spawnInstruct[0] == 0 && list.size() < maxPop 
				&& Actor.getCount() < Actor.maxActors)
			{
				Vec2D randVel = new Vec2D(270.0 - Math.random()*90.0, 1.0);
				list.add(new Electrode(spawnInstruct[1], spawnInstruct[2], randVel, this));
			}
		}
	}
}
