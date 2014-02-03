package gameframe;

public class InputManager 
{
	
	public static final int PRESS = 1;
	public static final int HOLD = 2;
	public static final int RELEASE = 3;
	
	private Button[] buttonList;
	
	public InputManager()
	{
		
	}
	
	public InputManager(int[] intIDs, char[] charIDs)
	{
		
	}
	
	public boolean checkButtonState(int id, int state)
	{
		
	}
	
	public boolean checkButtonState(char id, int state)
	{
		
	}
	
	class Button
	{
		private boolean prevState;
		
		private boolean Press;
		private boolean Hold;
		private boolean Release;
		
		private int intID;
		private char charID;
		
		public Button(char id)
		{
			
		}
		
		public Button(int id)
		{
			
		}
		
		public void updateButton()
		{
			
		}
		
		public boolean checkForState(int state)
		{
			
		}
	}

}
