package gameframe;

import jgame.JGObject;

/*
 * The Actor class is used to create (persistent) game objects which interact with other game objects 
 * based on some sort of behavior or set of behaviors (usually implemented mainly through the 
 * routine() method). In other words, the actor class is used to create subclasses for things like 
 * enemies, players, etc. If an in game object is responsible for creating things like Pickups, 
 * Particles, and Projectiles and interacts with other in game objects in some way, then the class
 * for that object should likely extend the Actor class. The ActorManager and ManagerList classes
 * are optional classes that can be used to make the creation and destruction of Actors simpler and
 * more easily controlled, but it is not necessary that you use an ActorManger object to create 
 * Actors in your game. If you choose not to use an ActorManager to control the in game creation of
 * Actors in your game however, you are responsible for finding an appropriate place to call the
 * routine() method of those Actors. Otherwise, if an Actor is created using an ActorManager, then the
 * GameFrame framework will automatically call the routine() method of that Actor when you call the
 * frameActivities() method.
 */

public class Actor extends RBObject 
{
	public static int maxActors; // Maximum number of Actors that may be created in game. 
								 // Check if actorCount is below this before creating Particles.
	protected static int actorCount;// The current number of Actors in game. 
	
	protected String managerID; // The String (not necessarily unique) ID of the ActorManager that
								// created this Actor. May be set to null if no ActorManager created
								// it.
	protected double maxSpeed; // The maximum speed an Actor may induce on itself (physical
	   						   // interactions may give it a greater speed). Must implement use of
							   // this variable yourself if coding an Actor that accelerates itself.
	protected int state; // An Actor may have several different states in which it exhibits different
					     // behavior (ex: invincibility frames for a player character). Use this to
						 // change and keep track of the current state/behavior of an Actor.
	private long changeFrame; // Records the frame which the Actor last changed its state. Used in
							  // determining how long the Actor has been in its current state.
	
	// Below are the constructors from RBObject modified to set up Actors that do not plan to 
	// use the maxSpeed variable and start with state set to zero (the default beginning state, it's
	// recommended to make the state that your Actor object always begins state zero so that the 
	// state does not have to be set through the constructor).
	
	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, grav, insul, byRB, byField,
				byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e, grav, insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, start);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e, grav, insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, String mID) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start);
		actorCount++;
		maxSpeed = 0.0;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
	}
	
	// Same constructors as above, except this time maxSpeed is also set by an argument through the
	// constructor. 
	
	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start, String mID, 
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, grav, insul, byRB, byField,
				byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID,
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, String mID,
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e, grav, insul, byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, String mID,
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e, grav, insul, byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start, String mID, 
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, String mID, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start);
		actorCount++;
		state = 0;
		changeFrame = start;
		
		managerID = mID;
		maxSpeed = mspeed;
	}
	
	// Same constructors as above, except this time state is set through an argument instead of
	// maxSpeed.
	
	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start, String mID, 
			int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, grav, insul, byRB, byField,
				byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, String mID, 
			int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e, grav, insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, String mID, 
			int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, start);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e, grav, insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start, String mID, 
			int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, String mID, int initState) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start);
		actorCount++;
		maxSpeed = 0.0;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
	}
	
	// Same constructors as above, except this time both state and maxSpeed are set through arguments.
	
	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start, String mID, 
			int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, grav, insul, byRB, byField,
				byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID, int initState, 
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, String mID, int initState, 
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move, String mID, int initState, 
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, String mID, int initState, 
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, String mID, 
			int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID, int initState, 
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, String mID, int initState, 
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e, grav, insul, byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, String mID, 
			int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start, grav, insul, byRB,
				byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move, String mID, int initState, 
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, grav,
				insul, byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e, grav, insul, byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start, String mID, 
			int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, grav, insul,
				byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, String mID, int initState, 
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, String mID, int initState, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public Actor(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, String mID, int initState, 
			double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start);
		actorCount++;
		changeFrame = start;
		
		managerID = mID;
		state = initState;
		maxSpeed = mspeed;
	}

	public static int getCount()
	{
		return actorCount;
	}
	
	public void routine()
	{
		
	}
	
	public int getState()
	{
		return state;
	}
	
	public void setState(int newState)
	{
		state = newState;
		changeFrame = GameFrame.getCurrFrame();
	}
	
	public long stateTime()
	{
		return GameFrame.getCurrFrame() - changeFrame;
	}
	
	protected void destruction()
	{
		remove();
	}
	
	public void destroy()
	{
		rbCount--;
		actorCount--;
	}
	
	class Extension extends JGObject
	{
		public Actor master;
		protected double xOffset;
		protected double yOffset;
		protected int state;
		protected long changeFrame;
		protected boolean passCollision;
		
		// I will write constructors once constructors for RBObject are written.
		
		public int getState()
		{
			return state;
		}
		
		public void setState(int newState)
		{
			state = newState;
			changeFrame = GameFrame.getCurrFrame();
		}
		
		public long stateTime()
		{
			return GameFrame.getCurrFrame() - changeFrame;
		}
		
		protected void updateOffset()
		{
			
		}
		
		protected void destruction()
		{
			
		}
		
		public boolean passCollision()
		{
			return passCollision;
		}
		
		@Override
		public void move()
		{
			
		}
	}
}
