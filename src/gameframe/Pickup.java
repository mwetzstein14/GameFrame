package gameframe;

/*
 * A Pickup is an object typically found in games. Usually Pickups are used to augment or benefit
 * a player's character in some way when touched (ex: restore health, give new weapon, boost attack,
 * etc). Because Pickups generally only react to the player's character touching them, the default 
 * settings for a Pickup object is to not react to gravity, insulate charge, not react to (physical)
 * collisions, not react to force fields, and not to move in general. One can change this by calling
 * a constructor that allows you to set those settings yourself however. The code for a Pickup to
 * give a player's character an augment or benefit is not in the Pickup class. One must override the
 * hit() method (inherited from JGObject->RBObject->Pickup) in order to make the Pickup disappear
 * when touched by a player's character. One must also override the hit() method for the class of the
 * player's character object in order to determine what augments or benefits the character should
 * receive. 
 */

public class Pickup extends RBObject 
{
	public static int maxPickups; // Maximum number of Pickups that may be created in game. 
	 							  // Check if pickupCount is below this before creating Pickups.
	protected static int pickupCount; // The current number of Pickups in game. 
	
	protected int lifetime; // Used to determine the maximum number of frames that the object will  
    					  // stay in game before it expires. Using this variable to determine when a 
    					  // Pickup expires allows you to both give the Pickup an expiry setting 
    					  // (such as expire off play field) and a set lifetime. The object will 
    					  // expire for  whichever happens first, the expire condition or expiration 
						  // date.
	protected int lifeStage; // Pickups sometimes change behavior depending on how long they have been
						   // in game in order to communicate to players that they are about to 
						   // expire. This variable can be used to control the Pickup's current 
						   // behavior. 
	
	// Below are all the constructors from RBObject modified to also set the lifetime of the Pickup
	// (all constructors set lifetime because a Pickup is intended to be temporary). One can set
	// lifetime to a negative number if they really do not want their Pickup object to expire
	// after a given amount of frames. lifeStage is also set to one (first/beginning stage).
	// Some of the default settings for a Pickup are changed from a typical RBObject. Pickup objects
	// by default have the following settings:
	//
	// gravitate = false;
	// bypassRB = true;
	// bypassField = true;
	// canMove = false;
	//
	// This stops them from having physical interactions, which I anticipate to be the default desire
	// for a Pickup object. 
		
		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, int expiry, long start,
				boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
				boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, expiry, start, grav, insul, byRB,
					byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, int expiry, long start,
				double m, double ch, double co, double e, boolean grav,
				boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e, grav,
					insul, byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, int expiry, long start,
				double m, double ch, double co, double e, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, int expiry, long start, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, expiry, start);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, long start, boolean grav,
				boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, start, grav, insul, byRB, byField, byTile,
					move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, long start, double m,
				double ch, double co, double e, boolean grav, boolean insul,
				boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, start, m, ch, co, e, grav, insul,
					byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, long start, double m,
				double ch, double co, double e, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, start, m, ch, co, e);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, long start, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, start);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
				long start, boolean grav, boolean insul, boolean byRB,
				boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, vel, expiry, start, grav, insul, byRB,
					byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
				long start, double m, double ch, double co, double e, boolean grav,
				boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e,
					grav, insul, byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
				long start, double m, double ch, double co, double e, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
				long start, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, vel, expiry, start);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
				boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
				boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, vel, start, grav, insul, byRB,
					byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
				double m, double ch, double co, double e, boolean grav,
				boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, vel, start, m, ch, co, e, grav, insul,
					byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
				double m, double ch, double co, double e, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, vel, start, m, ch, co, e);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
				int tilebbox_width, int tilebbox_height, Vec2D vel, long start, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
					tilebbox_width, tilebbox_height, vel, start);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int expiry, long start,
				boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
				boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, expiry, start, grav, insul,
					byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int expiry, long start, double m,
				double ch, double co, double e, boolean grav, boolean insul,
				boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e,
					grav, insul, byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int expiry, long start, double m,
				double ch, double co, double e, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int expiry, long start, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, expiry, start);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int expiry, Vec2D vel, long start,
				boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
				boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, grav,
					insul, byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int expiry, Vec2D vel, long start,
				double m, double ch, double co, double e, boolean grav,
				boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
					co, e, grav, insul, byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int expiry, Vec2D vel, long start,
				double m, double ch, double co, double e, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
					co, e);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, int expiry, Vec2D vel, long start, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, long start, boolean grav,
				boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, start, grav, insul, byRB,
					byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, long start, double m, double ch,
				double co, double e, boolean grav, boolean insul, boolean byRB,
				boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e, grav,
					insul, byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, long start, double m, double ch,
				double co, double e, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, long start, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, start);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, Vec2D vel, int expiry, long start,
				boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
				boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, grav,
					insul, byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, Vec2D vel, int expiry, long start,
				double m, double ch, double co, double e, boolean grav,
				boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
					co, e, grav, insul, byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, Vec2D vel, int expiry, long start,
				double m, double ch, double co, double e, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
					co, e);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, Vec2D vel, int expiry, long start, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, Vec2D vel, long start,
				boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
				boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, vel, start, grav, insul,
					byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, Vec2D vel, long start, double m,
				double ch, double co, double e, boolean grav, boolean insul,
				boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e,
					grav, insul, byRB, byField, byTile, move);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, Vec2D vel, long start, double m,
				double ch, double co, double e, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}

		public Pickup(String name, boolean unique_id, double x, double y,
				int collisionid, String gfxname, Vec2D vel, long start, int life) {
			super(name, unique_id, x, y, collisionid, gfxname, vel, start);
			pickupCount++;
			lifetime = life;
			lifeStage = 1;
			
			gravitate = false;
			bypassRB = true;
			bypassField = true;
			canMove = false;
		}
	
	// Returns the number of Pickups currently in game.
	public static int getCount()
	{
		return pickupCount;
	}
	
	// Override this method to control how the Pickup changes as time passes (ex: fades away, 
	// flashes, etc). 
	protected void updateStage()
	{
		
	}
	
	// Override to write code for actions that the Pickup should take before expiring, such as
	// cleanup, spawning new objects, etc. Call the super method to make sure that your Pickup
	// will remove itself when expire is called.
	protected void expire()
	{
		remove();
	}
	
	// move() method is overridden in order to allow Pickup to expire when it has been in game for
	// as many frames as dictated by lifetime. Note: If lifetime is negative, Pickup will never
	// expire.
	@Override
	public void move()
	{
		if(beforeCollisions()) // Make sure that this code is run once when move is called before
		{       			   // collision methods are called. 
			if(elapsed() == lifetime)
				expire();
			
			updateStage();
		}
		
		super.move(); // Convention is to call super at the end in order to implement RBObject physics
					  // capabilities. 
	}
	
	// destroy() is called when the engine releases a JGObject. Used to decrement the current number
	// of Pickup objects and RBObjects in game. 
	@Override
	public void destroy()
	{
		pickupCount--;
		rbCount--;
	}
}
