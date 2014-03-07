package techdemo;

import jgame.JGPoint;
import gameframe.*;

public class DiamondManager extends ActorManager 
{
	public DiamondManager(String id, int max, int wait, JGPoint loc)
	{
		super(id, max, SET_INTERVAL, new int[] {wait, loc.x, loc.y});
	}
	
	@Override
	public void spawn() 
	{
		if(elapsed()%spawnInstruct[0] == 0 && list.size() < maxPop 
				&& Actor.getCount() < Actor.maxActors)
		{
			Vec2D randVel = new Vec2D(180.0 - Math.random()*90.0, 1.0);
			list.add(new Diamond(spawnInstruct[1], spawnInstruct[2], randVel, this));
		}
	}

}
