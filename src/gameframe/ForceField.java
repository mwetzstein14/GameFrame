package gameframe;

import java.util.ArrayList;

/*
 * The ForceField class is meant to be implemented in order to create forces that affect RBObjects
 * depending on their location and their current physical properties (ex: mass, charge, velocity, etc).
 * ForceField classes should be used to create non-contact forces (forces that do not result from one
 * object making contact with another). 
 */

public abstract class ForceField 
{
	/*
	 * This ArrayList should store all objects derived from ForceField that are created.
	 * Only ForceFields in this ArrayList will be able to affect RBObjects, as RBObjects access
	 * ForceFields using this static ArrayList. 
	 */
	protected ArrayList<ForceField> list = new ArrayList<ForceField>();
	
	private int fieldID; // A unique ID number for the ForceField created. If a ForceField is given
						 // the same ID number as another ForceField already in list, then that
						 // ForceField already in list will be removed from list. 
	
	// Constructor for a ForceField. Requires that you give it an ID. Automatically adds the
	// ForceField to ForceField.list. 
	public ForceField(int id)
	{
		fieldID = id; // Sets unique integer ID.
		
		int index = -1; // Holds index of any ForceField in list that has a matching ID with this one.
		
		// Finds the index of the ForceField in list that has a matching ID if there is one. 
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getID() == id)
				index = i;
		}
		
		// Removes the conflicting ForceField if there is one and adds this ForceField to list. 
		if (index > -1)
			list.remove(index);
		list.add(this);
	}
	
	// Returns the ID of the ForceField.
	public int getID()
	{
		return fieldID;
	}
	
	// Returns the force that should be applied to the RBObject passed as an argument. 
	public Vec2D getForce(RBObject rb)
	{
		if(inField(rb)) // If the RBObject is under the influence of the ForceField.
		{
			// Check if the RBObject was under the influence of the ForceField last frame.
			boolean wasIn = rb.matchField(fieldID);
			
			// If the RBObject was under the influence of the ForceField last frame, then call
			// whileIn(), otherwise the RBObject is entering the ForceField for the first time
			// and onEnter() should be called.
			if(wasIn) 
				whileIn(rb);
			else
				onEnter(rb);
			
			return calcForce(rb); // Return the force that should be exerted on the RBObject.
		}
		else // The RBObject is not under the influence of the field, and no force should be applied. 
			return new Vec2D(new Coord(0.0, 0.0));
	}
	
	// Implement this method to do something when an RBObject first comes under influence of the
	// ForceField. Takes the RBObject that entered the field as a parameter. 
	protected abstract void onEnter(RBObject rb);
	
	// Implement this method to do something while an RBObject is under influence of the
	// ForceField. Takes the RBObject that entered the field as a parameter.
	protected abstract void whileIn(RBObject rb);
	
	// Implement this method to fill in the logic that determines whether a potential RBObject has 
	// entered the influence of the ForceField or not. 
	public abstract boolean inField(RBObject rb);
	
	// Implement this method to determine how to calculate the force that the ForceField exerts on
	// an RBObject that is under its influence. 
	protected abstract Vec2D calcForce(RBObject rb);
}
