package gameframe;

import java.util.ArrayList;

import jgame.JGObject;

/*
 * RB stands for rigid body. An RBObject is an JGObject with instance variables and methods that 
 * implement simple (somewhat pseudo) physics. RBObjects can set various physical properties which 
 * determine how the object interacts with other RBObjects and tiles associated with Surface objects.
 * RBObjects are also influenced by forces exerted on them by ForceField objects. RBObjects are 
 * capable of having (physical) collisions with other RBObjects and tiles, sharing electric charge
 * with other RBObjects, responding to force fields, and responding to gravity (set by Gravity class).
 * Since the GameFrame framework is designed around using RBObjects instead of JGObjects, it is 
 * recommended that you use RBObjects instead of when possible JGObjects, even if you don't want to 
 * use the physics capabilities of an RBObject. For this reason, RBObjects have several settings
 * that allow you to disable certain unwanted physics functionalities.
 */

public class RBObject extends JGObject 
{
	public static int maxRB; // Maximum number of RBObjects that may be created in game. 
							 // Check if rbCount is below this before creating RBObjects.
	protected static int rbCount; // The current number of RBObjects in game. 
	
	private long startFrame; // The frame which this RBObject was created. 
	protected double mass; // The mass of the RBObject, may be used in calculating forces and in 
						   // determining the acceleration caused by a force.
	protected double charge; // The electric charge of the RBObject, may be used in calculating forces.
	protected double coeff; // A partial coefficient of friction between this object and a Surface.
						    // Used in calculating frictional forces.
	protected double elastic; // The elasticity of the object. Used in calculating collisions. 
	
	public Vec2D velocity; // The velocity of the RBObject (speed with direction). Used to set
						   // xspeed and yspeed (JGObject attributes) every frame. 
	public Vec2D accel; // The acceleration of the RBObject, or change in velocity every frame.
						// Forces add to acceleration, which then gets added to velocity in the 
						// move() method and reset to zero so that constant forces do not compound. 
	public Vec2D jerk; // In physics, jerk is the change in acceleration (the acceleration of 
					   // acceleration if you will). For the purposes of the RBObject though, it is
					   // the same as acceleration except that it is not reset to zero each frame. 
	
	protected boolean gravitate; // Set to true for RBObject to be accelerated by gravity (set with 
							     // Gravity class) or false to ignore gravity. 
	protected boolean insulate; // Set to true for RBObject to retain its electric charge during 
							    // collisions or to false for it to share charge during a collision.
	
	protected int lastTile; // The collision ID of the last tile that triggered a call to the 
						    // hit_bg() method during a frame.
	protected ArrayList<Integer> lastFields = new ArrayList<Integer>(); // The ID of the ForceFields
																	    // that the RBObject was under
																	    // the influence of last frame.
	
	protected boolean bypassRB; // Set to true for RBObject not to have physical interactions with 
							    // other RBObjects.
	protected boolean bypassTile; // Set to true for RBObject to not have physical interactions with 
								  // tiles. 
	protected boolean bypassField; // Set to true for RBObject not to be affected by ForceFields.
	protected boolean canMove; // Set to true to enable the move() method and false to disable it.
							   // Disabling stops most physical interactions with other objects. 
	
	// Below are the constructors from JGObject modified to create RBObjects with default settings. 
	// Also modified to set the speed using a Vec2D vector instead of two doubles for the x and y
	// speed. 
	
	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start) {
		super(name, unique_id, x, y, collisionid, gfxname);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXMag(), vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel,
			int expiry, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXMag(), vel.getYMag(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXMag(), vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXMag(), vel.getYMag(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}


	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel.getXMag(),
				vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}
	
	// Below are the same constructors again, except modified this time to also set the initial
	// physical attributes of the RBObject. 
	
	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m, double ch, double co, 
			double e) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m, double ch, double co, 
			double e) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start, double m, double ch, 
			double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m, double ch, double co, 
			double e) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXMag(), vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel,
			int expiry, long start, double m, double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXMag(), vel.getYMag(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, double m, double ch, 
			double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXMag(), vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry, long start, double m, 
			double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXMag(), vel.getYMag(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}
	
	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, double m, double ch, 
			double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel.getXMag(),
				vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		canMove = true;
	}
	
	// Below are the same constructors again, except this time modified to set initial settings
	// for physical interactions of the RBObject.
	
	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, boolean grav, boolean insul, 
			boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, boolean grav, boolean insul, 
			boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start, boolean grav, 
			boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, boolean grav, boolean insul, 
			boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXMag(), vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start, boolean grav, 
			boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXMag(), vel.getYMag(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, boolean grav, 
			boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXMag(), vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry, long start, 
			boolean grav, boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXMag(), vel.getYMag(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}


	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, boolean grav, 
			boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel.getXMag(),
				vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = 1.0;
		charge = 0.0;
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}
	
	// Below are the same constructors again, except modified this time to set the initial
	// physical attributes as well as the physical interactions settings of the RBObject. 
	
	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch, double co, double e, 
			boolean grav, boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m, double ch, double co, 
			double e, boolean grav, boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m, double ch, double co, 
			double e, boolean grav, boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start, double m, double ch, 
			double co, double e, boolean grav, boolean insul, boolean byRB, boolean byField, 
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m, double ch, double co, 
			double e, boolean grav, boolean insul, boolean byRB, boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXMag(), vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start, double m, double ch, 
			double co, double e, boolean grav, boolean insul, boolean byRB, boolean byField, 
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXMag(), vel.getYMag(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, double m, double ch, 
			double co, double e, boolean grav, boolean insul, boolean byRB, boolean byField, 
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXMag(), vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry, long start, double m, 
			double ch, double co, double e, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXMag(), vel.getYMag(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}
	
	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, double m, double ch, 
			double co, double e, boolean grav, boolean insul, boolean byRB, boolean byField, 
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel.getXMag(),
				vel.getYMag());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		jerk = new Vec2D(new Coord(0.0, 0.0));
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		canMove = move;
	}
	
	// Returns the number of RBObjects currently in game. 
	public static int getCount()
	{
		return rbCount;
	}
	
	// Returns the number of frames that this RBObject has been in game.
	public long elapsed()
	{
		return GameFrame.getCurrFrame() - startFrame;
	}
	
	// Returns the mass of this RBObject.
	public double getMass()
	{
		return mass;
	}
	
	// Returns the electric charge of this RBObject.
	public double getCharge()
	{
		return charge;
	}
	
	// Returns the partial coefficient of friction of this RBObject.
	public double getCoeff()
	{
		return coeff;
	}
	
	// Returns the elasticity of this RBObject.
	public double getElastic()
	{
		return elastic;
	}
	
	// Returns the momentum of this RBObject.
	public Vec2D getMomentum()
	{
		Vec2D momentum = velocity.copy();
		momentum.scaleMag(mass);
		return momentum;
	}
	
	// Returns the collision ID of the last tile which prompted hit_bg() to be called during the
	// current frame. 
	public int getLastTile()
	{
		return lastTile;
	}
	
	// Takes a force and determines the acceleration vector that should be added to accel.
	public void addForce(Vec2D force)
	{
		force.scaleMag(1.0/mass);
		accel.addVec(force);
	}
	
	// Takes an impulse (change in momentum), finds the current momentum of the RBObject, adds the 
	// impulse vector, then changes the new momentum into the new velocity vector of the RBObject.
	public void addImpulse(Vec2D impulse)
	{
		Vec2D newVel = getMomentum();
		newVel.addVec(impulse);
		newVel.scaleMag(1.0/mass);
		velocity = newVel;
	}
	
	// Takes a field ID and returns true if it matches one of the ForceFields that this RBObject was
	// under the influence of last frame and false if it does not.
	public boolean matchField(int id)
	{
		for(int lf : lastFields)
		{
			if(lf == id)
				return true;
		}
		return false;
	}
	
	// Determines the outcome of a collision between this RBObject and another.
	private void hitRB(RBObject rbArg)
	{
		
	}
	
	// Overridden to update xspeed and yspeed with the components of velocity once velocity has been
	// updated by accel and jerk. 
	@Override
	public void move()
	{
		
	}
	
	// Overridden to call hitRB if the RBObject happens to collide with another RBObject.
	@Override
	public void hit(JGObject obj)
	{
		if(obj instanceof RBObject)
			hitRB((RBObject)obj);
	}
	
	// Overridden in order to determine the interaction between this RBObject and a tile if that
	// tile is associated with a Surface. 
	@Override
	public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize)
	{
		
	}
	
	// destroy() is called when the engine releases a JGObject. Used to decrement the current number
	// of and RBObjects in game.
	@Override
	public void destroy()
	{
		rbCount--;
	}
}
