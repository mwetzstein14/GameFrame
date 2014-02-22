package gameframe;

import java.util.ArrayList;

/*
 * The Surface class is a class used to give tiles in your game the properties of a physical surface
 * (either the ground if the game is from a top-down view or a wall, floor, or ceiling if the game is
 * from a side view). Tiles can gain the properties of a physical surface defined using a Surface
 * object if that tile's collision ID is contained in the tileIDs array. Tiles whose collision IDs 
 * are contained in a Surface object's tileIDs array are considered associated with that Surface 
 * RBObjects will experience frictional forces when moving across or against tiles associated with a
 * Surface. They may also land on or bounce off of a tile associated with a Surface if the Surface is
 * not considered a background Surface. The reason a Surface object may be associated with more than
 * one tile collision ID is so that collisions with corners (two tiles positioned so that an RBObject
 * may collide with both of them in the same frame but are not part of the same wall, floor, or
 * ceiling) may work properly. If two tiles use the same Surface, then an RBObject should not have a 
 * collision with both tiles in the same frame unless they form a corner. The Surface class does not
 * allow collisions with multiple tiles of the same collision ID, so a different collision ID should
 * be used for tiles that use the same Surface but are part of a different wall, floor, or ceiling so
 * that corners may be implemented properly. 
 */

public class Surface 
{
	/*
	 * This ArrayList should store all Surface objects that are created. Surface objects need to be
	 * in this ArrayList in order for GameFrame to use them in tile collisions. The constructor for
	 * a Surface object automatically adds the Surface object to list, so it is not necessary to
	 * add the Surface to list yourself or hold onto the reference for the Surface object at all
	 * (unless you plan to remove the Surface from list later without removing any other Surfaces,
	 * then a reference to the Surface object should be kept somewhere where it will be persistent).
	 */
	public static ArrayList<Surface> list = new ArrayList<Surface>(); 
	
	// Static method for clearing all Surface objects out of Surface.list.
	public static void removeAll()
	{
		// Iterate through list backwards and remove all objects.
		for(int i = list.size()-1; i >= 0; i--)
		{
			list.remove(i);
		}
	}
	
	// Static method for clearing a specific Surface object from Surface.list. Must pass a reference
	// to the Surface which you want to be removed. 
	public static void removeThis(Surface surface)
	{
		list.remove(surface);
	}
	
	// Constants used for representing what side of an associated tile an RBObject is colliding with.
	protected static final int TOP = 100;
	protected static final int RIGHT = 200;
	protected static final int LEFT = 300;
	protected static final int BOTTOM = 400;
	
	protected int[] tileIDs; // Collision IDs of the tiles that are associated with the Surface.
	protected double coeffK; // Partial kinetic coefficient of friction. Used in calculating the
							 // frictional force that should be exerted on an RBObject that is sliding
							 // against a tile associated with this Surface.
	protected double coeffS; // Partial static coefficient of friction. Used in calculating the
	 						 // frictional force that should be exerted on an RBObject that is 
	 						 // stationary and in contact with a tile associated with this Surface.
	protected double elastic; // Partial elasticity of a collision between an RBObject and a tile
							  // associated with the Surface. Used in calculating collisions. 
	protected boolean background; // Setting used to control whether the tile is considered to be in
								  // the background (behind the RBObjects) or in the foreground 
								  // (alongside the RBObjects). If background is set to true, then
								  // the Surface will only exert a frictional force on RBObjects that
								  // collide tiles associated with it and ignore collisions, allowing
								  // the RBObjects to pass through the tiles.
	
	// Constructor for a Surface object. It is required that you pass an array containing the 
	// collision IDs of the tiles to be associated with the Surface. You must also pass arguments to
	// set the coefficients of kinetic and static friction, the elasticity of the Surface, and the
	// setting for whether the Surface is in the background or not. 
	// Constructor also automatically adds the Surface to Surface.list. 
	public Surface(int[] ids, double k, double s, double elast, boolean back)
	{
		tileIDs = ids;
		coeffK = k;
		coeffS = s;
		elastic = elast;
		background = back;
		
		list.add(this); // Add the Surface to list. 
	}
	
	// Method that checks if the collision between a given tile and an RBObject should result in a
	// collision or frictional force. Checks both if the tile is associated with the Surface and that
	// the RBObject hasn't already collided with a tile of the same collision ID this frame. Returns
	// true if there should be a physical interaction and false if there should not. 
	public boolean checkTileID(int tilecid, RBObject rb)
	{
		// If the RBObject this frame has already collided with a tile that has the same collision ID 
		// as the tile it has just collided with, then return false.
		if(rb.matchTile(tilecid))
			return false;
		
		// If the test above is passed (false is never returned), then check if the collision ID of
		// the tile that the RBObject has just collided with matches any of the tile IDs that are
		// associated with this Surface, then return true.
		for(int i=0; i < tileIDs.length; i++)
		{
			if(tileIDs[i] == tilecid)
				return true;
		}
		
		return false; // In case none of the above if-statements are triggered, return false. 
	}
	
	// Method that takes an RBObject and information about a tile and then returns either TOP, RIGHT,
	// LEFT, BOTTOM depending upon whether the RBObject is making contact above, to the right of, 
	// to the left of, or below the tile.
	protected int findSide(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		
	}
	
	// Returns true if this is the first frame the RBObject has made contact with a tile associated
	// with this Surface. 
	protected boolean firstTime(RBObject rb)
	{
		
	}
	
	// The hit() method returns the impulse exerted on an RBObject that collides with a tile 
	// associated with this Surface. 
	public Vec2D hit(RBObject rb, int tilecid, int tx, int ty, int txsize, int tysize)
	{
		int side = findSide(rb, tx, ty, txsize, tysize);
		
		double e = elastic*rb.getElastic();
		
		Vec2D rbVel_i = rb.velocity.copy();
		rbVel_i.addVec(rb.accel);
		
		Vec2D rbVel_f = rbVel_i.copy();
		rbVel_f.scaleMag(e);
		
		if(side == TOP)
		{
			if(rbVel_i.getYComp() >= 0.0)
				return new Vec2D(0.0, 0.0);
			
			rbVel_f.setVec(rbVel_f.getXComp(), -1.0*rbVel_f.getYComp());
		}
		
		if(side == RIGHT)
		{
			if(rbVel_i.getXComp() >= 0.0)
				return new Vec2D(0.0, 0.0);
			
			rbVel_f.setVec(-1.0*rbVel_f.getXComp(), rbVel_f.getYComp());
		}
		
		if(side == LEFT)
		{
			if(rbVel_i.getXComp() <= 0.0)
				return new Vec2D(0.0, 0.0);
			
			rbVel_f.setVec(-1.0*rbVel_f.getXComp(), rbVel_f.getYComp());
		}
		
		if(side == BOTTOM)
		{
			if(rbVel_i.getYComp() <= 0.0)
				return new Vec2D(0.0, 0.0);
			
			rbVel_f.setVec(rbVel_f.getXComp(), -1.0*rbVel_f.getYComp());
		}
		
		rbVel_f.subtractVec(rbVel_i);
		Vec2D impulse = rbVel_f;
		impulse.scaleMag(rb.getMass());
		
		return impulse;
	}
	
	// The slide() method returns the frictional force exerted on an RBObject that makes contact with
	// a tile associated with this Surface. It only returns a force if it is not the first frame that
	// the RBObject has made contact with the associated tile. This method is also responsbile for
	// calling either the firstContact() or onContact() method, depending on whether this is the
	// first frame the RBObject made contact with the associated tile. 
	public Vec2D slide(RBObject rb, int tilecid, int tx, int ty, int txsize, int tysize)
	{
		if(firstTime(rb))
		{
			firstContact(rb, tx, ty, txsize, tysize);
			return new Vec2D(0.0, 0.0);
		}
		else
		{
			onContact(rb, tx, ty, txsize, tysize);
			
			int side = findSide(rb, tx, ty, txsize, tysize); 
			
			double fricCoeff;
			boolean moving;
			
			if(rb.x == rb.getLastX() && rb.y == rb.getLastY())
			{
				fricCoeff = rb.getCoeff()*coeffS;
				moving = false;
			}
			else
			{
				fricCoeff = rb.getCoeff()*coeffK;
				moving = true;
			}
			
			if(side == TOP || side == BOTTOM)
			{
				Vec2D normForce = rb.accel.getYVec();
				normForce.scaleMag(rb.getMass());
				
				if((side == TOP && normForce.getYComp() >= 0.0))
					return new Vec2D(0.0, 0.0);
				if((side == BOTTOM && normForce.getYComp() <= 0.0))
					return new Vec2D(0.0, 0.0);
				
				if(moving)
				{
					Vec2D friction = normForce;
					friction.scaleMag(fricCoeff);
					
					Vec2D rbVel = rb.velocity.copy();
					rbVel.addVec(rb.accel);
					
					if(rbVel.getXComp() < 0.0)
						friction.changeDir(0.0);
					else
					{
						if(Vec2D.useRadians)
							friction.changeDir(Math.PI);
						else
							friction.changeDir(180.0);
					}
					
					Vec2D fricAccel = friction.copy();
					fricAccel.scaleMag(1.0/rb.getMass());
					
					if(Math.abs(rbVel.getXComp()) < Math.abs(fricAccel.getXComp()))
					{
						Vec2D stopFric = rbVel.getXVec().getInverse();
						stopFric.scaleMag(rb.getMass());
						
						return stopFric;
					}
					
					return friction;
				}
				else
				{
					Vec2D maxFricAccel = normForce;
					maxFricAccel.scaleMag(fricCoeff);
					maxFricAccel.scaleMag(1.0/rb.getMass());
					
					Vec2D rbVel = rb.velocity.copy();
					rbVel.addVec(rb.accel);
					
					if(rbVel.getXVec().getMag() < maxFricAccel.getMag())
					{
						Vec2D friction = rbVel.getXVec().getInverse();
						friction.scaleMag(rb.getMass());
						
						return friction;
					}
					
					Vec2D friction = maxFricAccel;
					friction.scaleMag(rb.getMass());
					
					if(rbVel.getXComp() < 0.0)
						friction.changeDir(0.0);
					else
					{
						if(Vec2D.useRadians)
							friction.changeDir(Math.PI);
						else
							friction.changeDir(180.0);
					}
					
					return friction;
				}
			}
			else
			{
				Vec2D normForce = rb.accel.getXVec();
				normForce.scaleMag(rb.getMass());
				
				if((side == RIGHT && normForce.getXComp() >= 0.0))
					return new Vec2D(0.0, 0.0);
				if((side == LEFT && normForce.getXComp() <= 0.0))
					return new Vec2D(0.0, 0.0);
				
				if(moving)
				{
					Vec2D friction = normForce;
					friction.scaleMag(fricCoeff);
					
					Vec2D rbVel = rb.velocity.copy();
					rbVel.addVec(rb.accel);
					
					if(rbVel.getYComp() < 0.0)
					{
						if(Vec2D.useRadians)
							friction.changeDir(Math.PI/2.0);
						else
							friction.changeDir(90.0);
					}
					else
					{
						if(Vec2D.useRadians)
							friction.changeDir(1.5*Math.PI);
						else
							friction.changeDir(270.0);
					}
					
					Vec2D fricAccel = friction.copy();
					fricAccel.scaleMag(1.0/rb.getMass());
					
					if(Math.abs(rbVel.getYComp()) < Math.abs(fricAccel.getYComp()))
					{
						Vec2D stopFric = rbVel.getYVec().getInverse();
						stopFric.scaleMag(rb.getMass());
						
						return stopFric;
					}
					
					return friction;
				}
				else
				{
					Vec2D maxFricAccel = normForce;
					maxFricAccel.scaleMag(fricCoeff);
					maxFricAccel.scaleMag(1.0/rb.getMass());
					
					Vec2D rbVel = rb.velocity.copy();
					rbVel.addVec(rb.accel);
					
					if(rbVel.getYVec().getMag() < maxFricAccel.getMag())
					{
						Vec2D friction = rbVel.getYVec().getInverse();
						friction.scaleMag(rb.getMass());
						
						return friction;
					}
					
					Vec2D friction = maxFricAccel;
					friction.scaleMag(rb.getMass());
					
					if(rbVel.getYComp() < 0.0)
					{
						if(Vec2D.useRadians)
							friction.changeDir(Math.PI/2.0);
						else
							friction.changeDir(90.0);
					}
					else
					{
						if(Vec2D.useRadians)
							friction.changeDir(1.5*Math.PI);
						else
							friction.changeDir(270.0);
					}
					
					return friction;
				}
			}
		}
	}
	
	// This is a reserved method for creating a class which extends Surface. Override this method to
	// have events happen upon an RBObject continually making contact with a tile associated with the
	// Surface.
	protected void onContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		
	}
	
	// This is a reserved method for creating a class which extends Surface. Override this method to
	// have events happen upon an RBObject first making contact with a tile associated with the
	// Surface.
	protected void firstContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		
	}
}
