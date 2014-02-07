package gameframe;

import java.util.ArrayList;

/*
 * ManagerList is a class used as an mediator between the GameFrame class and any ActorManager objects
 * created. ManagerList can be used to call methods such as spawn() and destroy() (methods that are 
 * typically called each frame) for all ActorManager objects at once or for all ActorManager objects
 * sharing the same string ID (ActorManager IDs need not be unique unlike IDs for other classes). 
 * For ManagerList to be able to act as a mediator between GameFrame and an ActorManager object, the
 * ActorManager object must be added to ManagerList.list. ManagerList is declared abstract because it
 * is an entirely static class and should never actually be instantiated as an object. 
 */

public abstract class ManagerList 
{
	// ArrayList which holds the ActorManager objects which ManagerList manages. 
	private static ArrayList<ActorManager> list = new ArrayList<ActorManager>();
	
	// Method used to add an ActorManager object to list. 
	public static void add(ActorManager manager)
	{
		list.add(manager);
	}
	
	// Removes all ActorManagers in list, getting rid of their actors along with them. 
	public static void removeAll()
	{
		// First make each ActorManager (and the game engine) let go of its Actors, removing them from
		// the game by the next frame and offering them up for garbage collection. 
		for(ActorManager am : list)
		{
			am.removeAll();
		}
		
		// Finally, remove the ActorManager objects themselves. 
		for(int i = list.size()-1; i >= 0; i--)
		{
			list.remove(i);
		}
	}
	
	// Same as removeAll(), but instead only affects ActorManagers which share the passed String ID
	// in common. 
	public static void removeID(String id)
	{
		ArrayList<Integer> matches = new ArrayList<Integer>(); // Stores indexes of ActorManagers
		                                                       // with String ID id. 
		
		// First finds indexes of ActorManagers with String ID id. 
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getID().equals(id))
			{
				matches.add(i);
			}
		}
		
		// Next, for each match, remove the ActorManager along with its Actors for garbage collection.
		// Iterate backwards through matches because we are removing objects from list. 
		for(int i = matches.size()-1; i >=0; i--)
		{
			list.get(i).removeAll();
			list.remove(i);
		}
	}
	
	// Sets the refFrame for all ActorManagers with the passed String ID to a new reference frame. 
	public static void setRef(long newRef, String id)
	{
		ArrayList<Integer> matches = new ArrayList<Integer>(); // Stores indexes of ActorManagers
        													   // with String ID id.
		
		// First finds indexes of ActorManagers with String ID id.
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getID().equals(id))
			{
				matches.add(i);
			}
		}
		
		// Next, for each match, set refFrame to newRef.
		for(int match : matches)
		{
			list.get(match).refFrame = newRef;
		}
	}
	
	// Sets the maxPop for all ActorManagers with the passed String ID to a new maximum population. 
	public static void setPop(int newPop, String id)
	{
		ArrayList<Integer> matches = new ArrayList<Integer>(); // Stores indexes of ActorManagers
			   												   // with String ID id.
			
		// First finds indexes of ActorManagers with String ID id.
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getID().equals(id))
			{
				matches.add(i);
			}
		}
			
		// Next, for each match, set maxPop to newPop.
		for(int match : matches)
		{
			list.get(match).maxPop = newPop;
		}
	}
		
	// Sets the spawnMode for all ActorManagers with the passed String ID to a new spawning mode. 
	public static void setMode(int newMode, String id)
	{
		ArrayList<Integer> matches = new ArrayList<Integer>(); // Stores indexes of ActorManagers
		   													   // with String ID id.
		
		// First finds indexes of ActorManagers with String ID id.
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getID().equals(id))
			{
				matches.add(i);
			}
		}
		
		// Next, for each match, set spawnMode to newMode.
		for(int match : matches)
		{
			list.get(match).spawnMode = newMode;
		}
	}
	
	// Sets the spawnInstruct for all ActorManagers with the passed String ID to a new 
	// set of spawning instructions.
	public static void setInstruct(int[] newInstruct, String id)
	{
		ArrayList<Integer> matches = new ArrayList<Integer>(); // Stores indexes of ActorManagers
		   													   // with String ID id.
		
		// First finds indexes of ActorManagers with String ID id.
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getID().equals(id))
			{
				matches.add(i);
			}
		}
		
		// Next, for each match, set spawnInstruct to newInstruct.
		for(int match : matches)
		{
			list.get(match).spawnInstruct = newInstruct;
		}
	}
	
	// Calls the spawn() method for every ActorManager in list. 
	public static void spawnAll()
	{
		for(ActorManager am : list)
		{
			am.spawn();
		}
	}
	
	// Same as spawnAll(), but instead only affects ActorManagers which share the passed String ID
	// in common.
	public static void spawnID(String id)
	{
		ArrayList<Integer> matches = new ArrayList<Integer>(); // Stores indexes of ActorManagers
		   													   // with String ID id.
		
		// First finds indexes of ActorManagers with String ID id.
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getID().equals(id))
			{
				matches.add(i);
			}
		}
		
		// Next, for each match, call spawn().
		for(int match : matches)
		{
			list.get(match).spawn();
		}
	}
	
	// Calls the destroy method for every ActorManager in list.
	public static void destroyAll()
	{
		for(ActorManager am : list)
		{
			am.destroy();
		}
	}
	
	// Same as destroyAll(), but instead only affects ActorManagers which share the passed String ID
	// in common.
	public static void destroyID(String id)
	{
		ArrayList<Integer> matches = new ArrayList<Integer>(); // Stores indexes of ActorManagers
		   													   // with String ID id.
		
		// First finds indexes of ActorManagers with String ID id.
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getID().equals(id))
			{
				matches.add(i);
			}
		}
		
		// Next, for each match, call destroy().
		for(int match : matches)
		{
			list.get(match).destroy();
		}
	}
	
	// Calls the routines() method for every ActorManager in list. 
	public static void routinesAll()
	{
		for(ActorManager am : list)
		{
			am.routines();
		}
	}
	
	// Same as routinesAll(), but instead only affects ActorManagers which share the passed String ID
	// in common.
	public static void routinesID(String id)
	{
		ArrayList<Integer> matches = new ArrayList<Integer>(); // Stores indexes of ActorManagers
		   													   // with String ID id.
		
		// First finds indexes of ActorManagers with String ID id.
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getID().equals(id))
			{
				matches.add(i);
			}
		}
		
		// Next, for each match, call routines().
		for(int match : matches)
		{
			list.get(match).routines();
		}
	}
}
