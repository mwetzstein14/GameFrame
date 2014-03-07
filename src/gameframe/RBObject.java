package gameframe;

// Fix constructors to set bypassTile.

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
	private boolean beforeCollisions = true; // Move method is called twice in order to implement 
									 // force fields before collisions and then called again after  
									 // collisions to update velocity. This variable is used to track
	                                 // when move is being called, before or after collisions.
	
	private boolean bounceHit = false; // When RBObject collide with one another, they must move back
									   // to their position the previous frame so that they do not
									   // overlap other RBObjects. This variable is used in 	
									   // whether or not an RBObject will move back to its previous
									   // position due to a collision.
	
	public static int maxRB; // Maximum number of RBObjects that may be created in game. 
							 // Check if rbCount is below this before creating RBObjects.
	protected static int rbCount; // The current number of RBObjects in game. 
	
	private long startFrame; // The frame which this RBObject was created. 
	protected double mass; // The mass of the RBObject, may be used in calculating forces and in 
						   // determining the acceleration caused by a force.
	protected double charge; // The electric charge of the RBObject, may be used in calculating forces.
	private double nextCharge; // Holds onto the new charge of the RBObject just after a single 
							   // collision with another RBObject. Used to update charge at the end
							   // of a frame. 
	protected double coeff; // A partial coefficient of friction between this object and a Surface.
						    // Used in calculating frictional forces.
	protected double elastic; // The elasticity of the object. Used in calculating collisions. 
	
	public Vec2D velocity; // The velocity of the RBObject (speed with direction). Used to set
						   // xspeed and yspeed (JGObject attributes) every frame. 
	public Vec2D accel; // The acceleration of the RBObject, or change in velocity every frame.
						// Forces add to acceleration, which then gets added to velocity in the 
						// move() method and reset to zero so that constant forces do not compound.  
	
	protected boolean gravitate; // Set to true for RBObject to be accelerated by gravity (set with 
							     // Gravity class) or false to ignore gravity. 
	protected boolean insulate; // Set to true for RBObject to retain its electric charge during 
							    // collisions or to false for it to share charge during a collision.
	
	protected int lastSurfaces = 0; // The OR of all the collision IDs of tiles that this RBObject
									// collided with last frame (OR means all of them added 
									// together). Used to identify the Surface objects associated
									// with those tiles.
	
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
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;
		
		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;
		
		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height);
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXComp(), vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel,
			int expiry, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXComp(), vel.getYComp(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXComp(), vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXComp(), vel.getYComp(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}


	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel.getXComp(),
				vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
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
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m, double ch, double co, 
			double e) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
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
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
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
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m, double ch, double co, 
			double e) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXComp(), vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel,
			int expiry, long start, double m, double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXComp(), vel.getYComp(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, double m, double ch, 
			double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXComp(), vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry, long start, double m, 
			double ch, double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXComp(), vel.getYComp(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}
	
	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, double m, double ch, 
			double co, double e) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel.getXComp(),
				vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = true;
		insulate = true;
		
		bypassRB = false;
		bypassField = false;
		bypassTile = false;
		canMove = true;

		xdir = 1;
		ydir = 1;
	}
	
	// Below are the same constructors again, except this time modified to set initial settings
	// for physical interactions of the RBObject.
	
	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname);
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, boolean grav, boolean insul, 
			boolean byRB, boolean byField, boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, boolean grav, boolean insul, 
			boolean byRB, boolean byField, boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height);
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start, boolean grav, 
			boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, boolean grav, boolean insul, 
			boolean byRB, boolean byField, boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXComp(), vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start, boolean grav, 
			boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXComp(), vel.getYComp(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, boolean grav, 
			boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXComp(), vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry, long start, 
			boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXComp(), vel.getYComp(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}


	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, boolean grav, 
			boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel.getXComp(),
				vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = 10.0;
		charge = 0.0;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = 1.0;
		elastic = 1.0;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}
	
	// Below are the same constructors again, except modified this time to set the initial
	// physical attributes as well as the physical interactions settings of the RBObject. 
	
	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, long start, double m, double ch, double co, double e, 
			boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, long start, double m, double ch, double co, 
			double e, boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, long start, double m, double ch, double co, 
			double e, boolean grav, boolean insul, boolean byRB, boolean byField, boolean byTile,
			boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry, long start, double m, double ch, 
			double co, double e, boolean grav, boolean insul, boolean byRB, boolean byField, 
			boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = new Vec2D(new Coord(0.0, 0.0));
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, long start, double m, double ch, double co, 
			double e, boolean grav, boolean insul, boolean byRB, boolean byField,
			boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXComp(), vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, Vec2D vel, int expiry, long start, double m, double ch, 
			double co, double e, boolean grav, boolean insul, boolean byRB, boolean byField, 
			boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, vel.getXComp(), vel.getYComp(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, long start, double m, double ch, 
			double co, double e, boolean grav, boolean insul, boolean byRB, boolean byField, 
			boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXComp(), vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, Vec2D vel, int expiry, long start, double m, 
			double ch, double co, double e, boolean grav, boolean insul, boolean byRB,
			boolean byField, boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, vel.getXComp(), vel.getYComp(),
				expiry);
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
	}
	
	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, Vec2D vel, long start, double m, double ch, 
			double co, double e, boolean grav, boolean insul, boolean byRB, boolean byField, 
			boolean byTile, boolean move) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, vel.getXComp(),
				vel.getYComp());
		
		rbCount++;
		startFrame = start;
		
		mass = m;
		charge = ch;
		nextCharge = charge; // Charge for next frame will be the starting charge.
		coeff = co;
		elastic = e;
		
		velocity = vel;
		accel = new Vec2D(new Coord(0.0, 0.0));
		
		
		gravitate = grav;
		insulate = insul;
		
		bypassRB = byRB;
		bypassField = byField;
		bypassTile = byTile;
		canMove = move;

		xdir = 1;
		ydir = 1;
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
	
	// Used for getting the current charge of the RBObject while still processing collisions.
	public double getNextCharge()
	{
		return nextCharge;
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
	
	// Returns true if the given collision ID matches any of the collision IDs of tiles associated
	// with Surfaces that the RBObject collided with last frame. 
	public boolean matchSurface(int id)
	{
		return and(id, lastSurfaces);
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
		// First, find momentum of RBObject, then add the impulse to it.
		Vec2D momentum = getMomentum();
		momentum.addVec(impulse);
		
		// Now use the new momentum of the RBObject to determine the change in velocity.
		momentum.scaleMag(1.0/mass);
		momentum.subtractVec(velocity);
		
		accel.addVec(momentum); // Add the change in velocity to acceleration. 
	}
	
	// Returns true if RBObject is an insulator, false if it is a conductor.
	public boolean insulate()
	{
		return insulate;
	}
	
	// Returns true if RBObject is set to bypass collisions with other RBObjects and false otherwise.
	public boolean bypassRB()
	{
		return bypassRB;
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
	
	// Overridden to call hitRB if the RBObject happens to collide with another RBObject.
	@Override
	public void hit(JGObject obj)
	{	
		// If RBObject's setting is to collide with other RBObjects and the RBObject collided with
		// another RBObject with the same settings, then carry out the collision code.
		if(!bypassRB && obj instanceof RBObject && !((RBObject)obj).bypassRB())
			hitRB((RBObject)obj);
	}
	
	// Determines the outcome of a collision between this RBObject and another.
	private void hitRB(RBObject rbArg)
	{
		// If this RBObject is not an insulator (a conductor) and the other RBObject is not an
		// insulator, then share charge between the two objects.
		if(!insulate && !rbArg.insulate())
		{
			nextCharge = (nextCharge + rbArg.getNextCharge())/2.0;
		}
		
		// The rest of this method determines how the velocity of the object will change due to a
		// collision. Unless physics is pretty fresh in your head, it may be hard to figure out
		// how this determines the change in velocity or what some of the terms I will be mentioning
		// in the comments mean. Just trust me though that this is sort of how it works.
		
		bounceHit = true; // In move method, return RBObject to its position just before the collision.
		
		double e = elastic * rbArg.getElastic(); // Use the partial elasticity of both colliding
												 // RBObjects to determine the elasticity of the 
												 // collision (e).
		
		// Note on elasticity: If less than one, RBObjects lose kinetic energy overall (and therefore 
		// speed), if equal to one, combined kinetic energy of the two objects stays the same, and
		// if greater than one, they gain kinetic energy overall.
		
		// Find the initial relative velocity of approach of the two RBObjects. (The difference 
		// between their velocities).
		Vec2D v_reli = velocity.copy();
		v_reli.subtractVec(rbArg.velocity);
		
		// Multiply the elasticity of the collision by the initial relative velocity of approach to
		// solve for the final relative velocity of approach.
		Vec2D v_relf = v_reli.copy();
		v_relf.scaleMag(e);
		
		// Find the difference between the final and initial relative velocity of approach. The 
		// difference is the velocity vector that needs to be broken up and added to both RBObjects
		// in order to find their new speed.
		v_relf.subtractVec(v_reli);
		Vec2D deltaVel = v_relf;
		
		// Split the change in relative velocity into two vectors pointing in opposite directions.
		// These are not necessarily the right magnitude, but they add up to the original deltaVel.
		Vec2D vel1 = deltaVel.copy();
		vel1.scaleMag(2.0);
		Vec2D vel2 = deltaVel.getInverse();
		
		// The impulses on both RBObjects, determined in the next step.
		// Impulse is the change in momentum.
		Vec2D myImpulse;
		Vec2D argImpulse;
		
		// Find which part of deltaVel (vel1 or vel2) is in the opposite direction of this RBObject's
		// velocity. Because we are working with doubles and there is a degree of error, we must
		// see whether vel1 is closer to matching the direction of this RBObject's velocity or the 
		// other RBObject's velocity in order to figure this out.
		if(Math.abs((vel1.getAngle() - velocity.getAngle())) < 
				Math.abs((vel1.getAngle() - rbArg.velocity.getAngle())))
		{
			// If vel2 is in the opposite direction of this RBObject's velocity, then scale it
			// by this RBObject's mass in order to create the impulse to be added to this RBObject.
			vel2.scaleMag(mass);
			myImpulse = vel2;
			
			// vel1 must then be in the opposite direction of the other RBObject's velocity, so scale
			// it by that RBObject's mass in order to create the impulse on that RBObject.
			vel1.scaleMag(rbArg.getMass());
			argImpulse = vel1;
		}
		else // Same as above, except if vel1 points in the opposite direction of this RBObject's
			 // velocity instead of vel2.
		{
			vel1.scaleMag(mass);
			myImpulse = vel1;
			
			vel2.scaleMag(rbArg.getMass());
			argImpulse = vel2;
		}
		
		// According to conservation of momentum, both RBObjects have to exert equal but opposite
		// impulses on each other. The differences between the magnitudes of the two impulses must
		// be found so that half of that difference can be used to adjust the impulse on this object
		// to be equal to the impulse on the other.
		Vec2D difference = myImpulse.copy();
		difference.subtractVec(argImpulse);
		
		// Adjust the impulse on this RBObject using half of difference, then add the impulse to
		// this RBObject's momentum (momentum actually changes when move() is called). 
		difference.scaleMag(0.5);
		myImpulse.subtractVec(difference);
		addImpulse(myImpulse);
	}
	
	// Overridden in order to determine the interaction between this RBObject and a tile if that
	// tile is associated with a Surface. 
	@Override
	public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize)
	{
		// If the RBObject is not set to bypass tiles, then run collision logic for tiles that
		// are associated with a surface.
		if(!bypassTile)
		{
			// For every surface object in Surface.list, check to see if the surface should be used
			// if this RBObject collides with a tile whose collision ID is found in the OR of tilecid.
			for(Surface surface : Surface.list)
			{
				// If a surface that should be used is found:
				if(surface.checkTileID(tilecid, this))
				{	
					// Add the impulse that should result from the RBObject colliding with the 
					// tile using the surface.
					addImpulse(surface.hit(this, tilecid, tx, ty, txsize, tysize));
					// Add the force that should result from the RBObject sliding against the tile 
					// using the surface.
					addForce(surface.slide(this, tilecid, tx, ty, txsize, tysize));
					
					lastSurfaces = tilecid; // Set lastSurfaces equal to the OR of the tiles collided
											// with this frame. 
				}
			}
		}
		
		
	}
	
	// move() is called twice by GameFrame, once before collisions are checked and once after they
	// are checked. move() is overridden so that if it is being called before, it will accelerate
	// the RBObject based on the influence of gravity as well as any ForceFields in ForceField.list.
	// If one plans to override move() when extending this class, they should wrap all their code
	// in one two if statements, one that checks if move is being called before collisions or one
	// that checks if it is being called after. That way, the code will only be executed once, and it
	// may either happen before or after collisions. 
	@Override
	public void move()
	{
		// If move is called before collisions are checked, add the accelerations due to Gravity and
		// ForceFields.
		if(beforeCollisions)
		{
			// So that the object doesn't actually move when move() is called before collisions. 
			xspeed = 0;
			yspeed = 0;
			
			// If this RBObject should be affected by gravity, then add the gravity acceleration
			// vector.
			if(gravitate)
				accel.addVec(Gravity.get_g());
			
			// If this RBObject should be affected by ForceFields, then add the forces caused by any
			// ForceFields. 
			if(!bypassField)
			{
				ArrayList<Integer> fieldIDs = new ArrayList<Integer>(); // Holds the IDs of the
																		// ForceFields that affected
																		// this RBObject this frame.
				
				// For every ForceField in ForceField.list, check if it exerts a force on this 
				// RBObject and then add the force and record the ForceField's ID if it does.
				for(ForceField field : ForceField.list)
				{
					Vec2D force = field.getForce(this);
					
					if(!(force.equals(new Vec2D(0.0, 0.0))))
					{
						fieldIDs.add(field.getID());
						addForce(force);
					}
				}
				
				lastFields = fieldIDs; // Update which ForceFields affected this RBObject for next
									   // frame.
			}
			
			beforeCollisions = false; // Next time this method is called, it should carry out code
									  // that should be executed after collision detection is done.
		}
		
		// If move is called after collisions are checked, update the velocity vector of the object
		// and then set the x and y speed values that JGEngine uses to move JGObjects equal to the
		// x and y components of velocity.
		if(!beforeCollisions)
		{
			// If the RBObject was involved in a collision, move it back to its position just
			// before the collision.
			if(bounceHit == true && (getLastX() != 0.0 && getLastY() != 0.0))
			{
				x = getLastX();
				y = getLastY();
				
				bounceHit = false; // Reset bounceHit.
			}
			
			// Only update the velocity of the RBObject if its setting currently permit it to move.
			if(canMove) 
			{	
				velocity.addVec(accel);
				
				xspeed = velocity.getXComp();
				yspeed = velocity.getYComp();
			}
		
			accel = new Vec2D(0.0, 0.0); // Reset the acceleration vector to zero so that it does not
										 // compound each frame.
			
			charge = nextCharge; // Update the resulting charge of the RBObject after this frame. 
			
			beforeCollisions = true; // Next time this method is called, it should carry out code
			  						 // that should be executed before collision detection is done.
		}
	}
	
	// destroy() is called when the engine releases a JGObject. Used to decrement the current number
	// of and RBObjects in game.
	@Override
	public void destroy()
	{
		rbCount--;
	}
	
	// beforeCollisions is a private instance variable that should not be changed by methods of
	// classes which extend this class. Therefore, if you are writing a class which extends this
	// class that needs to check the value of beforeCollisions, you should use this method to check
	// its value instead of the instance variable. The instance variable is essentially disguised as
	// a method for the convenience of the programmer extending this class. 
	public boolean beforeCollisions()
	{
		return beforeCollisions;
	}
}
