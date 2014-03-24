package gameframe.rbs;

import gameframe.vecmath.Vec2D;

/*
 * A Particle is an RBObject with optional physics capabilities that is intended to be a temporary
 * object created for a visual effect. Particles generally have minimal interaction with other objects
 * aside from physics interactions (exerting forces on other objects). A Particle would not be used to
 * create an object which is persistent or significantly interacts with other objects (ex: an enemy). 
 * Particles are suited for effects where objects need to be created either in abundance or 
 * continually and then disappear with time (ex: fire, flying shrapnel, bullet cases, sparks, 
 * splashes, smoke, etc). 
 */

public class Particle extends RBObject 
{
	public static int maxParticles; // Maximum number of Particles that may be created in game. 
									// Check if particleCount is below this before creating Particles.
	protected static int particleCount; // The current number of Particles in game. 
	
	protected int lifetime; // Used to determine the maximum number of frames that the object will  
						    // stay in game before it expires. Using this variable to determine when a 
						    // Particle expires allows you to both give the Particle an expiry setting 
						    // (such as expire off play field) and a set lifetime. The object will 
						    // expire for  whichever happens first, the expire condition or expiration 
						    // date. 
	
	// Below are all the constructors from RBObject modified to also set the lifetime of the Particle
	// (all constructors set lifetime because a Particle is intended to be temporary). One can set
	// lifetime to a negative number if they really do not want their Particle object to expire
	// after a given amount of frames. 
	
	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, grav, insul, byRB,
				byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e, grav,
				insul, byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, grav, insul, byRB, byField, byTile,
				move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e, grav, insul,
				byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, grav, insul, byRB,
				byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, grav, insul, byRB,
				byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e, grav, insul,
				byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, grav, insul,
				byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, grav,
				insul, byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e, grav, insul, byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, start, grav, insul, byRB,
				byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e, grav,
				insul, byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, start);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, grav,
				insul, byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e, grav, insul, byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, grav, insul,
				byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean byTile, boolean move, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e,
				grav, insul, byRB, byField, byTile, move);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e);
		particleCount++;
		lifetime = life;
	}

	public Particle(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, int life) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start);
		particleCount++;
		lifetime = life;
	}

	// Returns the total number of Particle objects currently created. 
	public static int getCount()
	{
		return particleCount;
	}
	
	// Override to write code for actions that the Particle should take before expiring, such as
	// cleanup, spawning new objects, etc. Call the super method to make sure that your Particle
	// will remove itself when expire is called.
	protected void expire()
	{
		remove();
	}
	
	// move() method is overridden in order to allow Particle to expire when it has been in game for
	// as many frames as dictated by lifetime. Note: If lifetime is negative, Particle will never
	// expire. 
	@Override
	public void move()
	{
		if(beforeCollisions()) // Make sure that this code is run once when move is called before
		{       			   // collision methods are called. 
			if(elapsed() == lifetime)
				expire();
		}
		
		super.move(); // Convention is to call super at the end in order to implement RBObject physics
					  // capabilities. 
	}
	
	// destroy() is called when the engine releases a JGObject. Used to decrement the current number
	// of Particle objects and RBObjects in game. 
	@Override
	public void destroy()
	{
		particleCount--;
		rbCount--;
	}
}
