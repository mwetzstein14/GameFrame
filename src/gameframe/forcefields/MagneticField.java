package gameframe.forcefields;

import jgame.JGRectangle;
import gameframe.rbs.RBObject;
import gameframe.vecmath.GFUtil;
import gameframe.vecmath.Vec2D;

public class MagneticField extends ForceField 
{
	protected double T; // Used to adjust the strength of the magnetic field.
	protected boolean dirOut; // Used to set the direction of the magnetic field, either into the
							  // play field or out. 
	protected JGRectangle areaOfEffect; // A JGRectangle defining the area within the play field that 
										// the magnetic field influences objects.
	
	// Constructor for creating a magnetic field.
	public MagneticField(String grp, double strength, boolean outOfPlayfield, JGRectangle aoe) 
	{
		super(grp);
		
		T = strength;
		dirOut = outOfPlayfield;
		areaOfEffect = aoe;
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

	// Method that determines whether an RBObject should be affected by the MagneticField or not.
	@Override
	public boolean inField(RBObject rb) 
	{
		JGRectangle rbSize = new JGRectangle((int)rb.x, (int)rb.y, 
				rb.getBBox().width, rb.getBBox().height);
		
		if(GFUtil.rectOverlap(rbSize, areaOfEffect))
			return true;
		else
			return false;
	}

	// Calculates the force the MagneticField should exert. 
	@Override
	protected Vec2D calcForce(RBObject rb) 
	{
		Vec2D force = rb.velocity.copy();
		
		if(Vec2D.useRadians)
			force.addDir(Math.PI/2);
		else
			force.addDir(90);
		
		force.scaleMag(T*rb.getCharge());
		
		if(dirOut)
			return force;
		else
			return force.getInverse();
	}

}
