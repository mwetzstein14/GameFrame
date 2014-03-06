package techdemo;

import jgame.JGRectangle;
import gameframe.*;

public class SteelSurface extends Surface 
{
	public SteelSurface(int[] cids)
	{
		super(cids, 0.5, 0.5, 0.7, false);
	}
	
	@Override
	public void firstContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		if(!(rb instanceof Debris) && rb.velocity.getMag() > 2.0)
		{
			int side = findSide(rb, tx, ty, txsize, tysize);
			JGRectangle rbSize = rb.getBBox();
			int angleAdjust = (int)Math.random()*180;
		
			if(side == TOP)
			{
				Vec2D pVel = new Vec2D((180 + angleAdjust), 0.5);
				new Debris(rb.x + (double)(rbSize.width/2), ty*16 + 11, pVel);
			}
			if(side == BOTTOM)
			{
				Vec2D pVel = new Vec2D((0 + angleAdjust), 0.5);
				new Debris(rb.x + (double)(rbSize.width/2), ty*16 + 16, pVel);
			}
			if(side == RIGHT)
			{
				Vec2D pVel = new Vec2D((270 + angleAdjust), 0.5);
				new Debris(tx*16 + 16, rb.y + (double)(rbSize.height/2), pVel);
			}
			if(side == LEFT)
			{
				Vec2D pVel = new Vec2D((0 + angleAdjust), 0.5);
				new Debris(tx*16 + 11, rb.y + (double)(rbSize.height/2), pVel);
			}
		}
	}
}
