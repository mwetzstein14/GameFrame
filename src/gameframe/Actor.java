package gameframe;

import jgame.JGObject;

public class Actor extends RBObject 
{
	public static int maxActors;
	protected static int actorCount;
	
	protected String managerID;
	protected double maxSpeed;
	protected int state;
	protected long changeFrame;
	
	// I will write constructors once constructors for RBObject are written.
	
	@Override
	public static int getCount()
	{
		
	}
	
	public void routine()
	{
		
	}
	
	public int getState()
	{
		
	}
	
	public void setState(int newState)
	{
		
	}
	
	public long stateTime()
	{
		
	}
	
	protected void destruction()
	{
		
	}
	
	public void destroy()
	{
		
	}
	
	class Extension extends JGObject
	{
		public Actor master;
		protected double xOffset;
		protected double yOffset;
		protected int state;
		protected long changeFrame;
		protected boolean passCollision;
		
		// I will write constructors once constructors for RBObject are written.
		
		public int getState()
		{
			
		}
		
		public void setState(int newState)
		{
			
		}
		
		public long stateTime()
		{
			
		}
		
		protected void updateOffset()
		{
			
		}
		
		protected void destruction()
		{
			
		}
		
		public boolean passCollision()
		{
			return passCollision();
		}
		
		@Override
		public void move()
		{
			
		}
	}
}
