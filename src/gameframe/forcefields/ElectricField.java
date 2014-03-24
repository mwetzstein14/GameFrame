package gameframe.forcefields;

import jgame.JGRectangle;
import gameframe.rbs.RBObject;
import gameframe.vecmath.Coord;
import gameframe.vecmath.GFUtil;
import gameframe.vecmath.Vec2D;

public class ElectricField extends ForceField 
{
	protected double k; // Used to adjust strength of the electric field.
	private boolean planar; // Determines whether electric field is a parallel planar field or is 
							// caused by a point charge. 
	protected Double q; // The magnitude of the charge causing the electric field. 
	
	// If source of electric field is a point charge:
	protected Double sourceX; // Reference to x location of the origin of the field.
	protected Double sourceY; // Reference to y location of the origin of the field. 
	protected double range; // Used to determine how close objects have to be to the source of the
							// electric field to be influenced by it. 
	public static final double infinite = -1; // Used to set the range to infinitely far 
											  // (all RBObjects will be affected). 
	
	// If the electric field is a planar one:
	protected JGRectangle areaOfEffect; // A JGRectangle defining the area within the play field that 
										// the electric field influences objects.
	protected Vec2D fieldVec; // Vector used to represent direction and magnitude of the uniform
								 // electric field.

	// Constructor for creating an ElectricField with a dynamic point charge origin. Uses references 
	// to Double objects to keep track of changing origin. Also uses a reference to keep track of the
	// charge causing the ElectricField. 
	public ElectricField(String grp, double strength, double range, Double x, Double y, Double charge) 
	{
		super(grp);
		
		k = strength;
		sourceX = x;
		sourceY = y;
		q = charge;
		
		planar = false;
	}
	
	// Constructor for creating an ElectricField with a static origin and charge.
	public ElectricField(String grp, double strength, double range, double x, double y, double charge) 
	{
		super(grp);
		
		k = strength;
		sourceX = x;
		sourceY = y;
		q = charge;
		
		planar = false;
	}
	
	// Constructor for creating a planar ElectricField with the given area of effect in the
	// play field (defined by a JGRectangle). 
	public ElectricField(String grp, JGRectangle aoe, Vec2D EVec)
	{
		super(grp);
		
		areaOfEffect = aoe;
		planar = true;
		fieldVec = EVec;
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

	// Method that determines whether an RBObject should be affected by the ElectricField or not. 
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

	// Calculates the force the ElectricField should exert. 
	@Override
	protected Vec2D calcForce(RBObject rb) 
	{
		if(planar)
		{
			Vec2D force = fieldVec.copy();
			force.scaleMag(rb.getCharge());
			
			return force;
		}
		else
		{
			// Determine the product of the source's charge and the RBObject's charge. 
			double comboCharge = rb.getCharge()*q;
			
			// Next, calculate the distance between the source and an RBObject.
			double distance = 
					Math.sqrt(Math.pow((rb.x+rb.getBBox().width/2.0) - sourceX, 2.0) 
							+ Math.pow((rb.y+rb.getBBox().width/2.0) - sourceY, 2.0));
			
			// Create a force in the direction from the RBObejct to the source object.
			Vec2D force = new Vec2D(new Coord(rb.x+rb.getBBox().width/2.0, rb.y+rb.getBBox().width/2.0), 
					new Coord(sourceX, sourceY));
			force.changeMag(-k*comboCharge/Math.pow(distance, 2.0)); // Change the magnitude of the 
																   	   // force to be the product of the
																   	   // two charges of the involved
																       // objects divided by the square
																       // of the distance between those
																       // two objects (and then scaled
																       // by -k). 
			return force; // Return the force. 
		}
	}
	
}
