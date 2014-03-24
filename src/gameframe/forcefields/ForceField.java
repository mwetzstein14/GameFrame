package gameframe.forcefields;

import gameframe.rbs.RBObject;
import gameframe.vecmath.Coord;
import gameframe.vecmath.Vec2D;

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
	public static ArrayList<ForceField> list = new ArrayList<ForceField>();
	
	private int fieldID; // A unique ID number for the ForceField created. Only unique among 
						 // ForceFields in the same group.
	private String group; // String used in identifying a ForceField object and associating it with
						  // a group of other ForceField objects. 
	
	// Static method for clearing all ForceField objects out of ForceField.list.
	public static void removeAll()
	{
		// Iterate through list backwards and remove all objects.
		for(int i = list.size()-1; i >= 0; i--)
		{
			list.remove(i);
		}
	}
	
	// Static method for removing the ForceField object whose unique ID matches the argument passed
	// through id and are a member of the group passed through grp.
	public static void removeID(String grp, int id)
	{
		// Iterate through list backwards.
		for(int i = list.size()-1; i >= 0; i--)
		{
			// Once ForceField with matching group and ID is found, remove it.
			if(list.get(i).getGroup().equals(grp) && list.get(i).getID() == id)
			{
				list.remove(i);
				break; // IDs are unique for ForceFields, so it is not necessary to continue 
					   // searching for more matches. 
			}
		}
	}
	
	// Static method for removing all ForceField objects in the group passed through grp.
	public static void removeGroup(String grp)
	{
		// Iterate through list backwards.
		for(int i = list.size()-1; i >= 0; i--)
		{
			// Once ForceField with matching group is found, remove it.
			if(list.get(i).getGroup().equals(grp))
			{
				list.remove(i); 
			}
		}
	}
	
	// Static method for getting the number of ForceFields created in a given group.
	public static int countGroup(String grp)
	{
		int groupCount = 0; // Used to keep track of the number of ForceFields in group grp. 

		// Finds ForceFields in list with matching group, adds one to groupCount when one is found. 
		for(int i = 0; i < list.size(); i++)
		{
				if(list.get(i).getGroup() == grp)
					groupCount++;
		}
		
		return groupCount; // Return the number of ForceFields in group grp found. 
	}
	
	// Constructor for a ForceField. Requires that you give it an ID. Automatically adds the
	// ForceField to ForceField.list. 
	public ForceField(String grp)
	{
		group = grp; // Sets unique integer ID.
		
		int groupCount = 0; // Used to keep track of the number of ForceFields with the same group
							// already created.
		
		// Finds ForceFields in list with matching group, adds one to groupCount when one is found. 
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getGroup() == grp)
				groupCount++;
		}
		
		fieldID = groupCount; // Uses number of ForceFields already in group to assign ID. IDs start
							  // at zero.
		
		list.add(this); // Add the ForceField to list. 
	}
	
	// Returns the ID of the ForceField.
	public int getID()
	{
		return fieldID;
	}
	
	// Returns the group of the ForceField.
	public String getGroup()
	{
		return group;
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
