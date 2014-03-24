package gameframe.forcefields;

import java.util.ArrayList;

import jgame.JGRectangle;
import gameframe.rbs.RBObject;
import gameframe.vecmath.GFUtil;
import gameframe.vecmath.Vec2D;

public class FluidField extends ForceField 
{
	protected ArrayList<JGRectangle> fluidArea; // Rectangle areas used to approximate the areas
												// in the play field occupied by the fluid.
	protected double viscosity; // Used to adjust how resistant the fluid is to object motion. 

	// Constructor used to create a FluidField. 
	public FluidField(String grp, double strength, ArrayList<JGRectangle> areas) 
	{
		super(grp);
		
		viscosity = strength;
		fluidArea = areas;
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

	// Method that determines whether an RBObject should be affected by the FluidField or not.
	@Override
	public boolean inField(RBObject rb) 
	{
		JGRectangle rbSize = new JGRectangle((int)rb.x, (int)rb.y, 
				rb.getBBox().width, rb.getBBox().height);
		
		for(JGRectangle area : fluidArea)
		{
			if(GFUtil.rectOverlap(rbSize, area))
			{
				return true;
			}
		}
		
		return false;
	}

	// Calculates the force the FluidField should exert.
	@Override
	protected Vec2D calcForce(RBObject rb) 
	{
		double rbWidth = rb.getBBox().width;
		double rbHeight = rb.getBBox().height;
		double velAngle = rb.velocity.angleWithHor();
		
		double rbSurface = rbWidth*Math.sin(velAngle) + rbHeight*Math.cos(velAngle);
		
		Vec2D force = rb.velocity.getInverse();
		force.scaleMag(Math.abs(rbSurface)*viscosity);
		
		return force;
	}

}
