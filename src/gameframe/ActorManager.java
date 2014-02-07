package gameframe;

import java.util.ArrayList;

/*
 * ActorManager is a class used to manage the creation (and in some cases destruction) of Actor 
 * objects in game on the fly. For an ActorManager to be used by GameFrame, it must be added to
 * ManagerList by passing it through the add() method as an argument. ActorManager is abstract because
 * ActorManager is not written to know how to spawn objects periodically (there's no way for it to 
 * know what parameters it should pass to the constructors of your custom classes extending Actor).
 * Therefore, to be able to use an ActorManager, one has to extend the class to create one which knows
 * when and how to construct whatever custom Actors you may be using the ActorManager to create. The
 * spawn() method should always be overridden to determine when, how, and what Actors will be created.
 * The destroy() method may also be overridden if the ActorManager should be responsible for figuring
 * out when Actors should have destruction() or remove() called, although this is not necessary if the
 * Actors themselves know when to call those methods. If you do choose to override destroy(), then the
 * super method for destroy should always be called at the end so that ActorManager can offer up any
 * Actors that are no longer in game to the garbage collector. For this reason, it is not wise to 
 * extend a class from a class extending ActorManager unless you are comfortable running all the code
 * in the superclass's destroy() method in your overridden version, as calling super.destroy() is the 
 * only way to make sure your ActorManager doesn't hold onto memory and run methods for objects which
 * are no longer in game. 
 */

public abstract class ActorManager 
{
	// Names reserved for several spawning modes which may be typically implemented. 
	public static final int SET_INTERVAL = 100; // Spawn Actors at set intervals.
	public static final int RANDOM_INTERVAL = 200; // Chance to spawn Actor after set interval.
	public static final int WHEN_TOLD = 300; // Spawn Actor when told to by another class/object.
	public static final int MAX_POP = 400; // Make sure that there is always the max number of Actors.
	
	protected ArrayList<Actor> list; // ArrayList containing all the Actors created by this manager. 
	
	private String managerID; // A String ID for the manager. Used to call methods of this manager
							  // through ManagerList. Need not be unique if one wishes to have methods
							  // of this manager called whenever managers with the same ID have their
							  // methods called. 
	
	public int maxPop; // Maximum number of Actors that this manager may have in game at once.
	public int spawnMode; // Determines method of spawning Actors.
	public int[] spawnInstruct; // Used to give specific instructions for spawning Actors.
	public long refFrame; // Used to track the number of frames elapsed since the reference frame
						  // stored in this.
	
	// Constructor which sets up instance variables.
	public ActorManager(String id, int max, int mode, int[] instruct)
	{
		managerID = id;
		maxPop = max;
		spawnMode = mode;
		spawnInstruct = instruct;
		refFrame = 0;
		
		list = new ArrayList<Actor>();
	}
	
	public abstract void spawn(); // Must be implemented by class which extends this class. Add any
								  // Actors created to list. 
	
	// As is, checks to see if all Actors in list are still in game. If they are not, remove them from
	// list so that they may be garbage collected. If you override this method, call the super method
	// at the end. 
	public void destroy()
	{
		for(int i = list.size()-1; i >= 0; i--)
		{
			if(list.get(i).isAlive() == false)
				list.remove(i);
		}
	}
	
	// Method which removes all Actors in list from the game and from list without calling their
	// destruction methods (destruction() may also invoke animations or spawn new objects, which may
	// be undesirable if one is simply trying to reset the ActorManager or remove it from ManagerList).
	public void removeAll()
	{
		for(RBObject rb : list)
		{
			rb.remove();
		}
		
		for(int i = list.size(); i >= 0; i--)
		{
			list.remove(i);
		}
	}
	
	// All Actors have a method called routine(), which contains code that should be run every frame,
	// but should be run before the move() method. This method calls the routine() method for every
	// object in list. 
	public void routines()
	{
		for(Actor a : list)
		{
			a.routine();
		}
	}
	
	// Returns the String ID of this ActorManager. 
	public String getID()
	{
		return managerID;
	}
	
	// Returns the number of frames that have passed since a reference frame (stored in refFrame). 
	protected long elapsed()
	{
		return GameFrame.getCurrFrame() - refFrame;
	}
}
