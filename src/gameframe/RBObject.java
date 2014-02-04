package gameframe;

import java.util.ArrayList;

import jgame.JGObject;

public class RBObject extends JGObject 
{
	public static int maxRB;
	protected static int rbCount;
	
	private long startFrame;
	private double mass;
	private double charge;
	private double coeff;
	private double elastic;
	
	private Vec2D velocity;
	private Vec2D accel;
	private Vec2D jerk;
	
	private boolean gravitate;
	private boolean insulate;
	
	public int lastTile;
	public ArrayList<Integer> lastFields = new ArrayList<Integer>();
	
	private boolean bypassRB;
	private boolean bypassTile;
	private boolean bypassField;
	private boolean canMove;
	
	// JGObject has a lot of constructors that need to be modified to also 
	// do setup for an RBObject. I will modify them later. 
	
	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname) {
		super(name, unique_id, x, y, collisionid, gfxname);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int expiry) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, expiry);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, double xspeed, double yspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, xspeed, yspeed);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, double xspeed, double yspeed,
			int expiry) {
		super(name, unique_id, x, y, collisionid, gfxname, xspeed, yspeed,
				expiry);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, double xspeed,
			double yspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, xspeed, yspeed);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, double xspeed,
			double yspeed, int expiry) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, xspeed, yspeed,
				expiry);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int xdir, int ydir, double xspeed,
			double yspeed, int expiry) {
		super(name, unique_id, x, y, collisionid, gfxname, xdir, ydir, xspeed,
				yspeed, expiry);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int tilebbox_x, int tilebbox_y,
			int tilebbox_width, int tilebbox_height, int xdir, int ydir,
			double xspeed, double yspeed, int expiry) {
		super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x,
				tilebbox_y, tilebbox_width, tilebbox_height, xdir, ydir,
				xspeed, yspeed, expiry);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, double xspeed,
			double yspeed) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, xspeed,
				yspeed);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, double xspeed,
			double yspeed, int xdir, int ydir) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, xspeed,
				yspeed, xdir, ydir);
		// TODO Auto-generated constructor stub
	}

	public RBObject(String name, boolean unique_id, double x, double y,
			int collisionid, String gfxname, int expiry, double xspeed,
			double yspeed, int xdir, int ydir, JGRectangle tilebbox) {
		super(name, unique_id, x, y, collisionid, gfxname, expiry, xspeed,
				yspeed, xdir, ydir, tilebbox);
		// TODO Auto-generated constructor stub
	}
	
	public static int getCount()
	{
		
	}
	
	public long elapsed()
	{
		
	}
	
	public double getMass()
	{
		
	}
	
	public double getCharge()
	{
		
	}
	
	public double getCoeff()
	{
		
	}
	
	public double getElastic()
	{
		
	}
	
	public Vec2D getVelocity()
	{
		
	}
	
	public Vec2D getAccel()
	{
		
	}
	
	public Vec2D getJerk()
	{
		
	}
	
	public Vec2D getMomentum()
	{
		
	}
	
	public void addForce(Vec2D force)
	{
		
	}
	
	public void addImpulse(Vec2D impulse)
	{
		
	}
	
	private void hitRB(RBObject rbArg)
	{
		
	}
	
	@Override
	public void move()
	{
		
	}
	
	@Override
	public void hit(JGObject obj)
	{
		
	}
	
	@Override
	public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize)
	{
		
	}
}
