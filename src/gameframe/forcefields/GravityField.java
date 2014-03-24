package gameframe.forcefields;

import jgame.JGRectangle;
import gameframe.rbs.RBObject;
import gameframe.vecmath.Coord;
import gameframe.vecmath.GFUtil;
import gameframe.vecmath.Vec2D;

public class GravityField extends ForceField 
{
	protected double G; // Used to adjust strength of the gravitational field.
	private boolean planar; // Determines whether gravitational field is a parallel planar field or is 
							// caused by a point mass. 
	protected Double m; // The magnitude of the mass causing the gravitational field. 
	
	// If source of gravitational field is a point mass:
	protected Double sourceX; // Reference to x location of the origin of the field.
	protected Double sourceY; // Reference to y location of the origin of the field. 
	protected double range; // Used to determine how close objects have to be to the source of the
							// electric field to be influenced by it. 
	public static final double infinite = -1; // Used to set the range to infinitely far 
											  // (all RBObjects will be affected). 
	
	// If the gravitational field is a planar one:
	protected JGRectangle areaOfEffect; // A JGRectangle defining the area within the play field that 
										// the electric field influences objects.
	protected Vec2D fieldVec; // Vector used to represent direction and magnitude of the uniform
								 // gravitational field.

	// Constructor for creating a GravityField with a dynamic point mass origin. Uses references 
	// to Double objects to keep track of changing origin. Also uses a reference to keep track of the
	// mass causing the GravityField. 
	public GravityField(String grp, double strength, double range, Double x, Double y, Double mass) 
	{
		super(grp);
		
		G = strength;
		sourceX = x;
		sourceY = y;
		m = mass;
		
		planar = false;
	}
	
	// Constructor for creating a GravityField with a static origin and mass.
	public GravityField(String grp, double strength, double range, double x, double y, double mass) 
	{
		super(grp);
		
		G = strength;
		sourceX = x;
		sourceY = y;
		m = mass;
		
		planar = false;
	}
	
	// Constructor for creating a planar GravityField with the given area of effect in the
	// play field (defined by a JGRectangle). 
	public GravityField(String grp, JGRectangle aoe, Vec2D GVec)
	{
		super(grp);
		
		areaOfEffect = aoe;
		planar = true;
		fieldVec = GVec;
	}

	// Must extend this class and override yourself if you want to make use of this method.
	@Override
	protected void onEnter(RBObject rb) 
	{

	}

	// Must extend this class and override yourself if you want to make use of this method.
	@Override
	protected void whileIn(RBObject rb) 
	{

	}

	// Method that determines whether an RBObject should be affected by the GravityField or not. 
	@Override
	public boolean inField(RBObject rb) 
	{
		if(planar)
		{
			JGRectangle rbSize = new JGRectangle((int)rb.x, (int)rb.y, 
					rb.getBBox().width, rb.getBBox().height);
			
			if(GFUtil.rectOverlap(rbSize, areaOfEffect))
				return true;
			else
				return false;
		}
		else
		{
			if(range != infinite)
			{
				// First calculate the distance between the source and an RBObject.
				double distance = 
						Math.sqrt(Math.pow((rb.x+rb.getBBox().width/2.0) - sourceX, 2.0) 
								+ Math.pow((rb.y+rb.getBBox().width/2.0) - sourceY, 2.0));
				
				// If the distance is less than range, return true. 
				if(distance <= range)
					return true;
				else
					return false;
			}
			else
			{
				return true;
			}
		}
	}

	// Calculates the force the GravityField should exert. 
	@Override
	protected Vec2D calcForce(RBObject rb) 
	{
		if(planar)
		{
			Vec2D force = fieldVec.copy();
			force.scaleMag(rb.getMass());
			
			return force;
		}
		else
		{
			// Determine the product of the source's mass and the RBObject's mass. 
			double comboCharge = rb.getMass()*m;
			
			// Next, calculate the distance between the source and an RBObject.
			double distance = 
					Math.sqrt(Math.pow((rb.x+rb.getBBox().width/2.0) - sourceX, 2.0) 
							+ Math.pow((rb.y+rb.getBBox().width/2.0) - sourceY, 2.0));
			
			// Create a force in the direction from the RBObejct to the source object.
			Vec2D force = new Vec2D(new Coord(rb.x+rb.getBBox().width/2.0, rb.y+rb.getBBox().width/2.0), 
					new Coord(sourceX, sourceY));
			force.changeMag(-G*comboCharge/Math.pow(distance, 2.0)); // Change the magnitude of the 
																   	   // force to be the product of the
																   	   // two masses of the involved
																       // objects divided by the square
																       // of the distance between those
																       // two objects (and then scaled
																       // by -G). 
			return force; // Return the force. 
		}
	}
	
}
