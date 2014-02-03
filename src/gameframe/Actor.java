package gameframe;

public class Actor extends RBObject 
{
	public static int maxActors;
	protected static int actorCount;
	
	private String managerID;
	private double maxSpeed;
	private int state;
	private long changeFrame;
	
	// I will write constructors once constructors for RBObject are written.
	
	@Override
	public int getCount()
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
	
	private void destruction()
	{
		
	}
	
	class Extension
	{
		private String master;
		private double xOffset;
		private double yOffset;
		private int state;
		private long changeFrame;
		
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
		
		private void updateOffset()
		{
			
		}
		
		private void destruction()
		{
			
		}
		
		@Override
		public void move()
		{
			
		}
	}
}
