package gameframe;

import java.util.ArrayList;

import jgame.JGObject;
import jgame.JGPoint;
import jgame.JGRectangle;

/*
 * The Surface class is a class used to give tiles in your game the properties of a physical surface
 * (either the ground if the game is from a top-down view or a wall, floor, or ceiling if the game is
 * from a side view). Tiles can gain the properties of a physical surface defined using a Surface
 * object if that tile's collision ID is contained in the tileIDs array. Tiles whose collision IDs 
 * are contained in a Surface object's tileIDs array are considered associated with that Surface 
 * RBObjects will experience frictional forces when moving across or against tiles associated with a
 * Surface. They may also land on or bounce off of a tile associated with a Surface if the Surface is
 * not considered a background Surface. Collisions with corners (two tiles positioned so that an RBObject
 * may collide with both of them in the same frame but are not part of the same wall, floor, or
 * ceiling) need two Surface object to work properly. If two tiles use the same Surface, then an 
 * RBObject should not have a collision with both tiles in the same frame unless they form a corner. 
 * The Surface class does not allow collisions with multiple tiles associate with the same Surface
 * object, so a different Surface object should be used for tiles that seem to use the same Surface 
 * but are part of a different wall, floor, or ceiling so that corners may be implemented properly. 
 * Corner tiles also both need to have different collision IDs. 
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
	// collision or frictional force. Checks if the tile is associated with the Surface. Returns
	// true if there should be a physical interaction and false if there should not. 
	public boolean checkTileID(int tilecid, RBObject rb)
	{		
		// If the test above is passed (false is never returned), then check if the collision ID of
		// the tile that the RBObject has just collided with matches any of the tile IDs that are
		// associated with this Surface, then return true.
		for(int i=0; i < tileIDs.length; i++)
		{
			if(JGObject.and(tileIDs[i], tilecid))
				return true;
		}
		
		return false; // In case none of the above if-statements are triggered, return false. 
	}
	
	// Method that takes an RBObject and information about a tile and then returns either TOP, RIGHT,
	// LEFT, BOTTOM depending upon whether the RBObject is making contact above, to the right of, 
	// to the left of, or below the tile.
	@SuppressWarnings("unused")
	protected int findSide(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		// This code assumes that the origin is in the top left of the screen.  
		
		JGPoint[][] tiles = new JGPoint[txsize][tysize]; // Array that will contain the indicies of 
														 // all the tiles that the RBObject is 
														 // overlapping. 
		ArrayList<JGPoint> choices = new ArrayList<JGPoint>(); // Will hold all the coordinates of 
															   // the tiles that ended up being 
															   // associated with this Surface.
		JGPoint tileHit = new JGPoint(0, 0); // This will contain the coordinate of the top left 
											 // corner of the tile that this RBObject collided with
											 // to trigger this Surface.
		
		// The following process determines the coordinate that will be stored in tileHit.
		
		// First, find all the indicies of the tiles that the RBObject is currently overlapping
		// using  the top left tile's indicies passed through tx and ty and the number of tiles 
		// overlapped in the x and y directions passed through txsize and tysize.
		for(int i = 0; i < txsize; i++)
		{
			for(int j = 0; j < tysize; j++)
			{
				tiles[i][j] = new JGPoint(tx+i, ty+j);
			}
		}
		
		// Next, for each tile that the RBObject is overlapping, check if the collision ID of that
		// tile matches a collision ID of a tile that this Surface is associated with. 
		for(int i = 0; i < txsize; i++)
		{
			for(int j = 0; j < tysize; j++)
			{
				for(int k = 0; k < tileIDs.length; k++)
				{
					// If it's a match...
					if(rb.eng.getTileCid(tiles[i][j].x, tiles[i][j].y) == tileIDs[k])
					{
						// Get the coordinate of the top left of that tile and set tileHit equal to it.
						choices.add(rb.eng.getTileCoord(tiles[i][j]));
					}
				}
			}
		}
		
		tileHit = choices.get(0);
		
		// It's easy to boil the choice of which side the RBObject is colliding with the tile down to
		// two choices depending on the relative positions of the two. These variables keep track of
		// which two choices are the possible correct choices.
		
		boolean t_left = false; // Is either colliding with top or left side.
		boolean t_right = false; // Is either colliding with top or right side.
		boolean b_left = false; // Is either colliding with bottom or left side.
		boolean b_right = false; // Is either colliding with bottom or right side. 
		
		// Find which combination of sides is possible:
		if(rb.y < (double)tileHit.y && rb.x < (double)tileHit.x) // If RBObject is above to the left.
			t_left = true;
		else if(rb.y < (double)tileHit.y && rb.x >= (double)tileHit.x) // If RBObject is above to the right. 
			t_right = true;
		else if(rb.y >= (double)tileHit.y && rb.x < (double)tileHit.x) // If RBObject is below to the left.
			b_left = true;
		else // If no other cases were true, then RBObject must be below to the right. 
			b_right = true;
		
		// After boiling down to two choices, one of them must be picked. To figure out which side
		// the RBObject is really colliding with, I take advantage of the fact that in a collision 
		// there is an overlap between the bounding boxes of the tile and the RBObject. By seeing 
		// which is longer, the height or width, of the overlap between the two bounding boxes, I can
		// determine which of the two possible choices is correct.
		
		// Get the default bounding box of the RBObject with the same dimensions as its sprite.
		JGRectangle rbDimensions = rb.getBBox();
		
		// These coordinates will hold one of the top and one of the bottom corners of the rectangle
		// that is the intersection between the bounding box of the RBObject and of the tile. 
		Coord topIntersect;
		Coord bottomIntersect;
		
		if(t_left) // If the two possible choices were top and left:
		{
			// Find coordinates of a top and a bottom corner of bounding box intersection.
			topIntersect = new Coord((double)tileHit.x, (double)tileHit.y);
			bottomIntersect = new Coord(rb.x + (double)rbDimensions.width, 
					rb.y + (double)rbDimensions.height);
			
			// Calculate the height and width of the intersection.
			double intHeight = Math.abs(topIntersect.y - bottomIntersect.y);
			double intWidth = Math.abs(topIntersect.x - bottomIntersect.x);
			
			if(intHeight > intWidth) // If the height is greater, then the RBObject must be to the
				return LEFT;         // left. Return LEFT.
			else // Otherwise it's above. Return TOP.
				return TOP;
		}
		else if(t_right) // If the two possible choices were top and right:
		{
			// Find coordinates of a top and a bottom corner of bounding box intersection.
			topIntersect = new Coord((double)tileHit.x + (double)(JGObject.tilewidth-1), 
					(double)tileHit.y);
			bottomIntersect = new Coord(rb.x, rb.y + (double)rbDimensions.height);
			
			// Calculate the height and width of the intersection.
			double intHeight = Math.abs(topIntersect.y - bottomIntersect.y);
			double intWidth = Math.abs(topIntersect.x - bottomIntersect.x);

			if(intHeight > intWidth) // If the height is greater, then the RBObject must be to the
				return RIGHT;		 // right. Return RIGHT.
			else // Otherwise it's above. Return TOP.
				return TOP;
		}
		else if(b_left) // If the two possible choices were bottom and left:
		{
			// Find coordinates of a top and a bottom corner of bounding box intersection.
			topIntersect = new Coord(rb.x + (double)rbDimensions.width, rb.y);
			bottomIntersect = new Coord((double)tileHit.x, 
					(double)tileHit.y + (double)(JGObject.tileheight-1));
			
			// Calculate the height and width of the intersection.
			double intHeight = Math.abs(topIntersect.y - bottomIntersect.y);
			double intWidth = Math.abs(topIntersect.x - bottomIntersect.x);
			
			if(intHeight > intWidth) // If the height is greater, then the RBObject must be to the
				return LEFT;         // left. Return LEFT.
			else // Otherwise it's below. Return BOTTOM.
				return BOTTOM;
		}
		else // If the two possible choices were bottom and right:
		{
			// Find coordinates of a top and a bottom corner of bounding box intersection.
			topIntersect = new Coord(rb.x, rb.y);
			bottomIntersect = new Coord((double)tileHit.x + (double)(JGObject.tilewidth-1),
					(double)tileHit.y + (double)(JGObject.tileheight-1));
			
			// Calculate the height and width of the intersection.
			double intHeight = Math.abs(topIntersect.y - bottomIntersect.y);
			double intWidth = Math.abs(topIntersect.x - bottomIntersect.x);
			
			if(intHeight > intWidth) // If the height is greater, then the RBObject must be to the
				return RIGHT;        // right. Return RIGHT.
			else // Otherwise it's below. Return BOTTOM.
				return BOTTOM;
		}
	}
	
	// Returns true if this is the first frame the RBObject has made contact with a tile associated
	// with this Surface. 
	protected boolean firstTime(RBObject rb)
	{
		for(int i=0; i < tileIDs.length; i++)
		{
			if(rb.matchSurface(tileIDs[i]))
				return false;
		}
		
		return true;
	}
	
	// The hit() method returns the impulse exerted on an RBObject that collides with a tile 
	// associated with this Surface. 
	public Vec2D hit(RBObject rb, int tilecid, int tx, int ty, int txsize, int tysize)
	{	
		// No need to calculate an impulse if the Surface is in the background. 
		if(!background)
		{
			// Note: The collision between an RBObject and a tile is calculated similarly to the 
			// collision between two RBObjects. The comments in the RBObject class's hitRB() method 
			// explains how this calculation works. The difference in how it is calculated here is that
			// the tile doesn't move, so instead of looking how the relative velocity of both objects in
			// the collision changes due to the elasticity of the collision, one only needs to look at
			// how the velocity of the RBObject hitting the tile changes due to the elasticity of the
			// collision. 
		
			// Determine what side of the tile the RBObject is colliding with, necessary to find the
			// direction of the resulting impulse.
			int side = findSide(rb, tx, ty, txsize, tysize);
			
			// Next push RBObject so that it is no longer overlapping with the tile.
			if(rb.getLastX() != 0.0 && rb.getLastY() != 0.0)
			{
				rb.x = rb.getLastX();
				rb.y = rb.getLastY();
			}
		
			double e = elastic*rb.getElastic(); // The elasticity of the collision with the tile. 
		
			// A copy of the RBObject's initial velocity, updated by adding acceleration of the RBObject 
			// to take into account any collisions the RBObject has had with other RBObjects and any 
			// forces exerted by ForceFields before calculating impulse. 
			Vec2D rbVel_i = rb.velocity.copy();
			rbVel_i.addVec(rb.accel);
		
			// The final velocity of the RBObject after the collision with the tile, calculated using the
			// elasticity of the collision.
			Vec2D rbVel_f = rbVel_i.copy();
			rbVel_f.scaleMag(e);
		
			// The direction of the final velocity must be changed depending upon which side of the
			// tile the RBObject collided with. The direction must be changed so that the RBObject 
			// bounces off of the side of the tile at an angle.
		
			if(side == TOP) // If it struck the top side of the tile.
			{	
				if(rbVel_i.getYComp() <= 0.0)   // If the RBObject is already moving away from the tile,
					return new Vec2D(0.0, 0.0); // then the collision already happened and an impulse of
												// zero should be returned.
			
				// If the RBObject was moving toward the tile, then change the final velocity to bounce
				// away.
				rbVel_f.setVec(new Coord(rbVel_f.getXComp(), -1.0*rbVel_f.getYComp()));
			}
			
			// Same as the code above, except edited to work if the RBObject is colliding with the right
			// side of the tile. 
			if(side == RIGHT)
			{
				if(rbVel_i.getXComp() >= 0.0)
					return new Vec2D(0.0, 0.0);
				
				rbVel_f.setVec(new Coord(-1.0*rbVel_f.getXComp(), rbVel_f.getYComp()));
			}
			
			// Same as the code above, except edited to work if the RBObject is colliding with the left
			// side of the tile. 
			if(side == LEFT)
			{
				if(rbVel_i.getXComp() <= 0.0)
					return new Vec2D(0.0, 0.0);
				
				rbVel_f.setVec(new Coord(-1.0*rbVel_f.getXComp(), rbVel_f.getYComp()));
			}
		
			// Same as the code above, except edited to work if the RBObject is colliding with the bottom
			// side of the tile. 
			if(side == BOTTOM)
			{
				if(rbVel_i.getYComp() >= 0.0)
					return new Vec2D(0.0, 0.0);
				
				rbVel_f.setVec(new Coord(rbVel_f.getXComp(), -1.0*rbVel_f.getYComp()));
			}
		
			// Use the difference between the initial and final velocity of the RBObject to calculate the
			// impulse on the RBObject resulting from the collision
			rbVel_f.subtractVec(rbVel_i);
			Vec2D impulse = rbVel_f;
			impulse.scaleMag(rb.getMass());
			
			return impulse; // Return the impulse. 
		}
		else
			return new Vec2D(0.0, 0.0); // Return zero vector if Surface is in the background. 
	}
	
	// The slide() method returns the frictional force exerted on an RBObject that makes contact with
	// a tile associated with this Surface. It only returns a force if it is not the first frame that
	// the RBObject has made contact with the associated tile. This method is also responsbile for
	// calling either the firstContact() or onContact() method, depending on whether this is the
	// first frame the RBObject made contact with the associated tile. Frictional forces are only 
	// exerted by background tiles.
	public Vec2D slide(RBObject rb, int tilecid, int tx, int ty, int txsize, int tysize)
	{
		// If this is the first frame the RBObject has mad contact with the tile, then a fricitonal 
		// force need not be exerted and the firstContact method should be called. 
		if(firstTime(rb))
		{
			firstContact(rb, tx, ty, txsize, tysize); // Perform actions upon first making contact
													  // with the tile.
			return new Vec2D(0.0, 0.0); // Return a frictional force of zero. 
		}
		
		// If this is not the first frame the RBObject has made contact with the tile, then find a
		// frictional force to exert and call the onContact() method. 
		
			
			// How to calculate the frictional force when the Surface is in the background.
			if(background)
			{
				onContact(rb, tx, ty, txsize, tysize); // Perform actions upon continually making contact
				   // with the tile.

				// The magnitude of the frictional force exerted on an object is calculated by 
				// multiplying the coefficient of friction by the normal force an object is exerting on
				// the surface it's making contact with. 

				double fricCoeff; // Will hold the coefficient of friction.
				boolean moving; // A different frictional force is exerted depending on whether the
				// object is moving (kinetic) or still (static). This will be used to 
				// tell whether the RBObject is moving or not. 

				// If the RBObject hasn't moved from its position last frame, then use static friction.
				if(rb.x == rb.getLastX() && rb.y == rb.getLastY())
				{
					fricCoeff = rb.getCoeff()*coeffS; // Calculate the coefficient of friction using
													  // the RBObject's partial coefficient and the
			   	  									  // Surface's static partial coefficient.

					moving = false; // Record that the RBObject is stationary. 
				}

				// If the RBObject is moving, then use kinetic friction.
				else
				{
					fricCoeff = rb.getCoeff()*coeffK; // Calculate the coefficient of friction using
													  // the RBObject's partial coefficient and the
			  	  									  // Surface's kinetic partial coefficient.

					moving = true; // Record that the RBObject is moving. 
				}
				
				// Use the magnitude of the weight force (mass * g) as a normal force. 
				// (Note: This is only realistic if the tile represents the floor.)
				double weight = rb.getMass()*Gravity.get_g().getMag();
				
				// Finding either the kinetic friction or the static friction in this case is very
				// similar to before, except the magnitude of the normal force is always the weight 
				// force of the RBObject (above) and the direction of the frictional force is not 
				// limited to up or down, but is in whatever direction is opposite motion.
				
				// Finding a kinetic frictional force if RBObject is moving.
				if(moving)
				{
					Vec2D rbVel = rb.velocity.copy();
					rbVel.addVec(rb.accel);
					
					Vec2D friction = new Vec2D(rbVel.getAngle(), weight*fricCoeff);
					friction = friction.getInverse();
					
					Vec2D fricAccel = friction.copy();
					fricAccel.scaleMag(1.0/rb.getMass());
					
					if(Math.abs(rbVel.getMag()) < Math.abs(fricAccel.getMag()))
					{
						Vec2D stopFric = rbVel.getInverse();
						stopFric.scaleMag(rb.getMass());
						
						return stopFric;
					}
					
					return friction;
				}
				// Finding a static frictional force if RBObject is still.
				else
				{
					Vec2D rbVel = rb.velocity.copy();
					rbVel.addVec(rb.accel);
					
					Vec2D maxFricAccel = new Vec2D(rbVel.getAngle(), weight*fricCoeff);
					maxFricAccel = maxFricAccel.getInverse();
					maxFricAccel.scaleMag(1.0/rb.getMass());
					
					if(rbVel.getMag() < maxFricAccel.getMag())
					{
						Vec2D friction = rbVel.getInverse();
						friction.scaleMag(rb.getMass());
						
						return friction;
					}
				
					Vec2D friction = maxFricAccel;
					friction.scaleMag(rb.getMass());
					
					return friction;
				}
			}
			else // Return a zero force if tile is not in the background.
				return new Vec2D(0.0, 0.0);
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
