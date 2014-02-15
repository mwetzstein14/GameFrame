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

	// Returns the number of Actors currently in game.
	public static int getCount()
	{
		return actorCount;
	}
	
	// Override this method to implement how your Actor should behave and interact with other Actors
	// or RBObjects. This method is called every frame before any calls to move() or checkCollision()
	// methods.
	public void routine()
	{
		
	}
	
	// Returns the current state that the Actor is in. Useful if you are creating Actors or RBOBjects  
	// that interact differently with other Actors depend on the states of the other Actors. 
	public int getState()
	{
		return state;
	}
	
	// Either the Actor itself or other JGObjects may change the state that an Actor is in. 
	// Override if any settings for the Actor should be updated upon changing state. Call the 
	// super method at the end. 
	public void setState(int newState)
	{
		state = newState;
		changeFrame = GameFrame.getCurrFrame();
	}
	
	// Used to get the number of frames that the Actor has been in its current state. 
	public long stateTime()
	{
		return GameFrame.getCurrFrame() - changeFrame;
	}
	
	// Override to write code for actions that the Actor should take before being destroyed, such 
	// as cleanup, spawning new objects, etc. Call the super method to make sure that your Actor
	// will remove itself when it is destroyed. When the object should then remove itself from
	// the game, call this method instead of remove() if you wish to have the actions defined in this
	// method take place.
	// Note: Any Extensions of this Actor should be destroyed in this method. 
	protected void destruction()
	{
		remove();
	}
	
	// destroy() is called when the engine releases a JGObject. Used to decrement the current number
	// of Actor objects and RBObjects in game.
	public void destroy()
	{
		rbCount--;
		actorCount--;
	}
	
	/*
	 * Extension is a nested class inside Actor because only Actor objects may have Extensions 
	 * associated with them. The Extension class is used to create in game objects which either 
	 * follow or even appear to be part of an Actor object in game, but have their own collision
	 * detection and events that are separate from that of the Actor which it follows. Extensions 
	 * extend JGObject because they do not have any physics capabilities associated with them 
	 * (however, they are capable of passing any collisions which they have to the Actor that they
	 * are following, so that it is as if the Actor had the collision instead of the Extension even
	 * though the Extension is what made contact with the other JGObject/RBObject). The creation and
	 * destruction of Extension objects should be handled by the Actor that they are associated with.
	 */
	
	protected class Extension extends JGObject
	{
		public Actor master; // Reference to the Actor which this Extension is following.
		
		// The location of the Extension is in relation to the Actor it is following (in other words,
		// the location of the Actor is like the origin for an Extension). 
		protected double xOffset; // X offset from the location of its master Actor.
		protected double yOffset; // Y offset from the location of its master Actor. 
		
		protected int state; // Extensions may have different states, similar to Actors.
		protected long changeFrame; // Records the frame which the Extensions last changed its state.  
		  							// Used in determining how long the Extension has been in its 
									// current state.
		protected boolean passJGCollision; // If set to true, then the Extension object will invoke 
										   // the hit() method of its master Actor whenever it has a 
										   // collision with a JGObject. 
		protected boolean passTileCollision; // If set to true, then the Extension object will invoke 
		   									 // the hit() method of its master Actor whenever it has a 
		   									 // collision with a tile.
		
		// I will write constructors once constructors for RBObject are written.
		
		// Returns the current state that the Extension is in. 
		public int getState()
		{
			return state;
		}
		
		// Either the Extension itself or other JGObjects may change the state that an Actor is in.
		// Override if any settings for the Extension should be updated upon changing state. Call
		// the super method at the end. 
		public void setState(int newState)
		{
			state = newState;
			changeFrame = GameFrame.getCurrFrame();
		}
		
		// Used to get the number of frames that the Extension has been in its current state.
		public long stateTime()
		{
			return GameFrame.getCurrFrame() - changeFrame;
		}
		
		// Override to change the offset of the Extension from the location of its master Actor. Can 
		// be used to animate movement of the Extension around its master Actor. The updateOffset()
		// method of any Extensions created should be called in the routine() method of its master 
		// Actor. 
		protected void updateOffset()
		{
			
		}
		
		// Override to write code for actions that the Extension should take before being destroyed,  
		// such as cleanup, spawning new objects, etc. Call the super method to make sure that your 
		// Extension will remove itself when it is destroyed. When the object should then remove itself 
		// from the game, call this method instead of remove() if you wish to have the actions defined  
		// in this method take place.
		protected void destruction()
		{
			
		}
		
		// The hit() method is overridden to pass any collisions that this Extension has with other
		// JGObjects to its master Actor if passRBCollision is true and the collision is not with the 
		// Extension's master Actor. 
		@Override
		public void hit(JGObject obj)
		{
			if(passJGCollision && obj != master)
			{
				master.hit(obj);
			}
		}
		
		// The hit_bg() method is overridden to pass any collisions that this Extension has with tiles
		// to its master Actor if passTileCollision is true.
		@Override
		public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize)
		{
			if(passTileCollision)
			{
				master.hit_bg(tilecid, tx, ty, txsize, tysize);
			}
		}
		
		// The move() method is overridden to set the x and y coordinate positions of the Extension
		// in game to the proper offset from the location of its master Actor in game. The coordinates
		// of the Extension are updated twice a frame, before and after collisions, due to the fact 
		// that move() is called twice. If I wrote the code so that it was updated once, either 
		// before or after collisions are checked, then there would be a chance that the code would
		// never execute. 
		@Override
		public void move()
		{
			x = master.x + xOffset;
			y = master.y + yOffset;
		}
	}
}
