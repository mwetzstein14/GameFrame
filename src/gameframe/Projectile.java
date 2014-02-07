package gameframe;

/*
 * Projectile is a class used to create objects that are created by Actors (typically) as a method of
 * interaction with other Actors (a typical case of this is enemy missile, bullet, or laser attacks).
 * The default settings for a Projectile are a little different than that of an RBObjects in that 
 * they don't react to gravity or physical collisions with other RBObjects. This is because usually
 * Projectiles are intended to fly relatively straight if they are being used as an enemy attack, and
 * also generally disappear on contact with other objects (and sometimes tiles) once they have either
 * been intercepted or interacted with the object. The Projectile class does not contain the code for
 * disappearing on contact with other JGObjects/RBObjects or interacting with them. The code for 
 * making Projectiles disappear should be implemented by overriding the hit() method (inherited from 
 * JGObject->RBObject->Projectile), and the code for interacting with other JGObjects should be 
 * written in the hit() method for the other object's class. 
 */

public class Projectile extends RBObject 
{
	public static int maxProjectiles; // Maximum number of Projectiles that may be created in game. 
	  								  // Check if projectileCount is below this before creating 
	                                  // Projectiles.
	protected static int projectileCount; // The current number of Projectiles in game. 
	
	protected double maxSpeed; // The maximum speed a Projectile may induce on itself (physical
							   // interactions may give it a greater speed). Must implement use of
							   // this variable yourself if coding a Projectile that accelerates 
							   // itself. 
	
	// Below are the constructors from RBObject modified to set up Projectiles that do not plan to 
	// use the maxSpeed variable.
	
	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, grav, insul, byRB,
				byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e, grav,
				insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, grav, insul, byRB, byField,
				move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e, grav, insul,
				byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, grav, insul, byRB,
				byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, grav, insul, byRB,
				byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e, grav, insul,
				byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, grav, insul,
				byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, grav,
				insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e, grav, insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, start, grav, insul, byRB,
				byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e, grav,
				insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, start);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, grav,
				insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e, grav, insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, grav, insul,
				byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e,
				grav, insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = 0.0;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start);
		projectileCount++;
		maxSpeed = 0.0;
		
		gravitate = false;
		bypassRB = true;
	}
	
	// Same constructors as above, except this time maxSpeed is also set by an argument through the
	// constructor. 
	
	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, grav, insul, byRB,
				byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e, grav,
				insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start,
			double m, double ch, double co, double e, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, expiry, start);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, grav, insul, byRB, byField,
				move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e, grav, insul,
				byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m,
			double ch, double co, double e, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, start);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, grav, insul, byRB,
				byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double m, double ch, double co, double e, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry,
			long start, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, expiry, start);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, grav, insul, byRB,
				byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e, grav, insul,
				byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start,
			double m, double ch, double co, double e, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y,
				tilebbox_width, tilebbox_height, vel, start);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, grav, insul,
				byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e,
				grav, insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m,
			double ch, double co, double e, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, start);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, grav,
				insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e, grav, insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start,
			double m, double ch, double co, double e, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start, m, ch,
				co, e);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel, start);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start, grav, insul, byRB,
				byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e, grav,
				insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch,
			double co, double e, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, start);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, grav,
				insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, boolean grav,
			boolean insul, boolean byRB, boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e, grav, insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start,
			double m, double ch, double co, double e, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start, m, ch,
				co, e);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, expiry, start);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start,
			boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, grav, insul,
				byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, boolean grav, boolean insul,
			boolean byRB, boolean byField, boolean move, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e,
				grav, insul, byRB, byField, move);
		projectileCount++;
		maxSpeed = mspeed;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m,
			double ch, double co, double e, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start, m, ch, co, e);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	public Projectile(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double mspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, vel, start);
		projectileCount++;
		maxSpeed = mspeed;
		
		gravitate = false;
		bypassRB = true;
	}

	// Returns the number of Projectiles currently in game.
	public static int getCount()
	{
		return projectileCount;
	}
	
	// Override to write code for actions that the Pickup should take before being destroyed, such as
	// cleanup, spawning new objects, etc. Call the super method to make sure that your Pickup
	// will remove itself when expire is called. When the object should then remove itself from
	// the game, call this method instead of remove() if you wish to have the actions defined in this
	// method take place.
	public void destruction()
	{
		remove();
	}
	
	// destroy() is called when the engine releases a JGObject. Used to decrement the current number
	// of Projectile objects and RBObjects in game.
	@Override
	public void destroy()
	{
		projectileCount--;
		rbCount--;
	}
}
