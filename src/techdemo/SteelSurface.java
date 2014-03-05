package techdemo;

import jgame.JGRectangle;
import gameframe.*;

public class SteelSurface extends Surface 
{
	public SteelSurface(int[] cids)
	{
		super(cids, 0.5, 0.5, 0.7, false);
	}
	
	/*
	@Override
	public void firstContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		if(!(rb instanceof Debris))
		{
			int side = findSide(rb, tx, ty, txsize, tysize);
			JGRectangle rbSize = rb.getBBox();
			int angleAdjust = (int)Math.random()*180;
		
			if(side == TOP)
			{
				Vec2D pVel = new Vec2D(180 + angleAdjust, 0.5);
				new Debris(rb.y + rbSize.height, rb.x + rbSize.width/2, pVel);
			}
			if(side == BOTTOM)
			{
				Vec2D pVel = new Vec2D(0 + angleAdjust, 0.5);
				new Debris(rb.y, rb.x + rbSize.width/2, pVel);
			}
			if(side == RIGHT)
			{
				Vec2D pVel = new Vec2D(270 + angleAdjust, 0.5);
				new Debris(rb.y + rbSize.height/2, rb.x + rbSize.width, pVel);
			}
			if(side == LEFT)
			{
				Vec2D pVel = new Vec2D(0 + angleAdjust, 0.5);
				new Debris(rb.y + rbSize.height/2, rb.x, pVel);
			}
		}
	}
	*/
}
