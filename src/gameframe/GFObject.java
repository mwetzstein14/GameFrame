package gameframe;

/*
 * GFObject is a replacement class for JGObject. Because GameFrame calls the moveObjects() method
 * twice, it does not make sense to use JGObjects just as they are because their move() method will 
 * be called twice per a frame. GFObject has two methods to replace the move() method and remedy this
 * problem, move1() and move2().
 */

import gameframe.vecmath.Vec2D;
import jgame.JGObject;

public class GFObject extends JGObject 
{
	private boolean beforeCollisions = true; // Move method is called twice in order to implement 
	 					// force fields before collisions for RBObjects and then called again after  
	 					// collisions to update velocity. This variable is used to track
    					// when move is being called, before or after collisions.
	
	// Constructors are the same as those for a JGObject, except a velocity vector is used instead of
	// setting the speeds individually in the x and y direction. 

	public GFObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel,
			int expiry) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXComp(), vel.getYComp(), expiry);
		
		xdir = 1;
		ydir = 1;
	}

	public GFObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXComp(), vel.getYComp());
		
		xdir = 1;
		ydir = 1;
	}

	public GFObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel.getXComp(), vel.getYComp());
		
		xdir = 1;
		ydir = 1;
		}

	public GFObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel.getXComp(), vel.getYComp(), expiry);
		
		xdir = 1;
		ydir = 1;
	}

	public GFObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel.getXComp(), vel.getYComp());
		
		xdir = 1;
		ydir = 1;
	}

	public GFObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry);
		
		xdir = 1;
		ydir = 1;
	}

	public GFObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height);
		
		xdir = 1;
		ydir = 1;
	}

	public GFObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry);
		
		xdir = 1;
		ydir = 1;
	}

	public GFObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname) {
		super(name, unique_id, x, y, collisionid, gfxname);
		
		xdir = 1;
		ydir = 1;
	}
	
	
	
	
	// These methods replace the move() method, and should be overridden instead of the move() method.
	
	// This method is called by move() if move() is being called before collision logic is done.
	protected void move1()
	{
		
	}
	
	// This method is called by move() if move() is being called after collision logic is done.
	protected void move2()
	{
		
	}
	
	// Overridden to either call move1() or move2(), depending whether or not  move() is being called
	// before or after collision logic is done by GameFrame. 
	@Override
	public void move()
	{
		if(beforeCollisions)
		{
			move1();
			beforeCollisions = false;
		}
		else
		{
			move2();
			beforeCollisions = true;
		}
	}
}
