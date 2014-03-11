package techdemo;

/*
 * Electric field is an example of how to use the ForceField class to simulate a force field 
 * found in real life. It creates an electric force between another charged RBObject and the
 * RBObject associated with it (the object considered to be causing the field. 
 */

import gameframe.*;

public class ElectricField extends ForceField 
{
	private Actor source; // Actor that the field is associated with. 
	
	public ElectricField(Actor sourceActor) 
	{
		super(ForceField.list.size()); // Constructor takes a unique int ID for the ForceField. 
		source = sourceActor;
	}

	// May override this method for special effects to be carried out when an object enters the 
	// influence of the ForceField.
	@Override
	protected void onEnter(RBObject rb) 
	{
		
	}

	// May override this method for special effects to be carried out when an object is under 
	// continuous influence of the ForceField.
	@Override
	protected void whileIn(RBObject rb) 
	{

	}

	// This method is overridden to develop an algorithm for determining whether or not an RBObject
	// has entered the influence of the ForceField. 
	@Override
	public boolean inField(RBObject rb) 
	{
		// First calculate the distance between the source and an RBObject.
		double distance = 
				Math.sqrt(Math.pow((rb.x+rb.getBBox().width/2.0) - (source.x+source.getBBox().width/2.0), 2.0) 
						+ Math.pow((rb.y+rb.getBBox().width/2.0) - (source.y+source.getBBox().width/2.0), 2.0));
		
		// If the distance is less than 40, return true. 
		if(distance < 40.0)
			return true;
		else
			return false;
	}

	// Override this method to determine a formula for calculating the force a ForceField exerts on
	// an RBObject.
	@Override
	protected Vec2D calcForce(RBObject rb) 
	{
		// If the object is an instance of Electrode, then return a zero vector. 
		if(rb instanceof Electrode)
			return new Vec2D(0.0, 0.0);
		
		// Determine the product of the source's charge and the RBObject's charge. 
		double comboCharge = rb.getCharge()*source.getCharge();
		
		// Next, calculate the distance between the source and an RBObject.
		double distance = 
				Math.sqrt(Math.pow((rb.x+rb.getBBox().width/2.0) - (source.x+source.getBBox().width/2.0), 2.0) 
						+ Math.pow((rb.y+rb.getBBox().width/2.0) - (source.y+source.getBBox().width/2.0), 2.0));
		
		// Create a force in the direction from the RBObejct to the source object.
		Vec2D force = new Vec2D(new Coord(rb.x+rb.getBBox().width/2.0, rb.y+rb.getBBox().width/2.0), 
				new Coord(source.x+source.getBBox().width/2.0, source.y+source.getBBox().width/2.0));
		force.changeMag(-5.0*comboCharge/Math.pow(distance, 2.0)); // Change the magnitude of the 
																   // force to be the product of the
																   // two charges of the involved
																   // objects divided by the square
																   // of the distance between those
																   // two objects (and then scaled
																   // by -5.0). 
		
		return force; // Return the force. 
	}

}
