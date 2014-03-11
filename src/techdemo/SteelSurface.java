package techdemo;

/*
 * SteelSurface is an example of a Surface object for tiles in the foreground as well as an example
 * of how to use a firstContact() method for effects.
 */

import jgame.JGRectangle;
import gameframe.*;

public class SteelSurface extends Surface 
{
	// Pass array of collision IDs associated with this Surface through the constructor.
	public SteelSurface(int[] cids)
	{
		super(cids,  // Collision IDs of associated tiles. 
				0.5, // Partial coefficient of kinetic friction.
				0.5, // Partial coefficient of static friction.
				0.9, // Partial elasticity, objects lose a little energy in collisions.
				false // Foreground tile.
				);
	}
	
	// This method is overridden to create Debris objects when RBObjects fly too fast into tiles
	// associated with a steel surface. 
	@Override
	public void firstContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		// If the RBObject is not another Debris object and is moving faster than 2.0
		if(!(rb instanceof Debris) && rb.velocity.getMag() > 2.0)
		{
			int side = findSide(rb, tx, ty, txsize, tysize); // Find the side of the tile that the
														
			JGRectangle rbSize = rb.getBBox(); // Get the dimensions of the RBObject.
			int angleAdjust = (int)(Math.random()*180); // Get a random angle to add to the intended
														// direction that a Debris will be launched in.
		
			// Depending on the side, create a Debris object between the middle of the RBObject and
			// the steel wall that has an initial velocity pointing away from the wall.
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
