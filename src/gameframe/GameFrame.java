package gameframe;

import jgame.*;
import jgame.impl.*;
import jgame.platform.*;

public abstract class GameFrame extends JGEngine
{
	protected static long currentFrame = 0;
	
	public static long getCurrFrame()
	{
		return currentFrame;
	}
	
	public GameFrame(JGPoint size)
	{
		initEngine(size.x, size.y);
	}
	
	public void doFrame()
	{

	}
	
	public void frameActivites()
	{
		
	}
	
	/*
	 * The GameFrame class can check input from a user/player. It can read input from both
	 * the keyboard and mouse. (Note: if application is for a mobile or android device, then the input
	 * may correspond to different things, ex: a left mouse click would be a tap on an android device's
	 * screen. GameFrame is not designed yet to check input for android or mobile phone devices
	 * yet (might be a thing to add in later) so I am not sure if all input from those kinds of devices
	 * is properly supported yet.) Use the trackAll() or trackThese() methods to tell GameFrame what  
	 * new set of Buttons it should track. 
	 */
	
	public static final int PRESS = 1; // Pass this as the state to checkButton() to see if the button
	                            	   // has just been pressed down.

	public static final int HOLD = 2; // Pass this as the state to checkButton() to see if the button
	                           		  // is being held down continuously. 

	public static final int RELEASE = 3; // Pass this as the state to checkButton() to see if the 
		                          		 // Button has just been released. 

	private Button[] buttonList; // An array containing all the Button objects for input that
                                 // the Input class is tracking. 

	// Sets up the Input class so that it tracks all possible input (on a computer). 
	public void trackAll()
	{	
		buttonList = new Button[62]; // Set buttonList to a new array that will hold Button objects
									 // for each key on the keyboard and buttons on the mouse. 

		// Create Button objects for all possible keyboard and mouse buttons, add them to buttonList. 
		buttonList[0] = new Button(GameFrame.KeyAlt);
		buttonList[1] = new Button(GameFrame.KeyBackspace);
		buttonList[2] = new Button(GameFrame.KeyCtrl);
		buttonList[3] = new Button(GameFrame.KeyUp);
		buttonList[4] = new Button(GameFrame.KeyDown);
		buttonList[5] = new Button(GameFrame.KeyLeft);
		buttonList[6] = new Button(GameFrame.KeyRight);
		buttonList[7] = new Button(GameFrame.KeyEsc);
		buttonList[8] = new Button(GameFrame.KeyShift);
		buttonList[9] = new Button(GameFrame.KeyTab);
		buttonList[10] = new Button(GameFrame.KeyMouse1);
		buttonList[11] = new Button(GameFrame.KeyMouse2);
		buttonList[12] = new Button(GameFrame.KeyMouse3);
		buttonList[13] = new Button('1');
		buttonList[14] = new Button('2');
		buttonList[15] = new Button('3');
		buttonList[16] = new Button('4');
		buttonList[17] = new Button('5');
		buttonList[18] = new Button('6');
		buttonList[19] = new Button('7');
		buttonList[20] = new Button('8');
		buttonList[21] = new Button('9');
		buttonList[22] = new Button('0');
		buttonList[23] = new Button('`');
		buttonList[24] = new Button('-');
		buttonList[25] = new Button('=');
		buttonList[26] = new Button(GameFrame.KeyEnter);
		buttonList[27] = new Button('q');
		buttonList[28] = new Button('w');
		buttonList[29] = new Button('e');
		buttonList[30] = new Button('r');
		buttonList[31] = new Button('t');
		buttonList[32] = new Button('y');
		buttonList[33] = new Button('u');
		buttonList[34] = new Button('i');
		buttonList[35] = new Button('o');
		buttonList[36] = new Button('p');
		buttonList[37] = new Button('a');
		buttonList[38] = new Button('s');
		buttonList[39] = new Button('d');
		buttonList[40] = new Button('f');
		buttonList[41] = new Button('g');
		buttonList[42] = new Button('h');
		buttonList[43] = new Button('j');
		buttonList[44] = new Button('k');
		buttonList[45] = new Button('l');
		buttonList[46] = new Button('z');
		buttonList[47] = new Button('x');
		buttonList[48] = new Button('c');
		buttonList[49] = new Button('v');
		buttonList[50] = new Button('b');
		buttonList[51] = new Button('n');
		buttonList[52] = new Button('m');
		buttonList[53] = new Button('[');
		buttonList[54] = new Button(']');
		buttonList[55] = new Button('\\');
		buttonList[56] = new Button(';');
		buttonList[57] = new Button('\'');
		buttonList[58] = new Button(',');
		buttonList[59] = new Button('.');
		buttonList[60] = new Button('/');
		buttonList[61] = new Button(' ');
	}

	// Sets Input class to track the buttons whose key code or character symbol is in either array. 
	public void trackThese(int[] intIDs, char[] charIDs)
	{
		buttonList = new Button[intIDs.length + charIDs.length]; // Set buttonList to a new array of
								 // appropriate size.

		// Add all the Button objects for buttons specified by their key code to buttonList.
		for(int i = 0; i < intIDs.length; i++)
		{
			buttonList[i] = new Button(intIDs[i]);
		}

		// Add all the Button objects for buttons specified by their character symbol to buttonList.
		for(int i = intIDs.length; i < intIDs.length + charIDs.length; i++)
		{
			buttonList[i] = new Button(charIDs[i - intIDs.length]);
		}
	}

	// Sets Input class to track the buttons whose key code is in the intIDs array. 
	public void trackThese(int[] intIDs)
	{
		buttonList = new Button[intIDs.length]; // Set buttonList to a new array of appropriate size.

		// Add all the Button objects for buttons specified by their key code to buttonList.
		for(int i = 0; i < intIDs.length; i++)
		{
			buttonList[i] = new Button(intIDs[i]);
		}
	}

	// Sets Input class to track the buttons whose character symbol is in the charIDs array. 
	public void trackThese(char[] charIDs)
	{
		buttonList = new Button[charIDs.length]; // Set buttonList to a new array of appropriate size.

		// Add all the Button objects for buttons specified by their character symbol to buttonList.
		for(int i = 0; i < charIDs.length; i++)
		{
			buttonList[i] = new Button(charIDs[i]);
		}
	}

	// Checks the state of the button given by the key code passed through id for the given state 
	// passed through state (either PRESS, HOLD, or RELEASE). Returns true if button is in given state
	// and false if not. 
	public boolean checkButton(int id, int state)
	{
		// Find Button object for button with key code id.
		for(Button b : buttonList)
		{
			if(b.getIntID() == id)
				return b.checkState(state); // Return whether or not it's in the requested state.
		}
		return false; // Return false if Input is not tracking the input from the button with key code
					  // id.
	}

	// Checks the state of the button given by the character passed through id for the given state 
	// passed through state (either PRESS, HOLD, or RELEASE). Returns true if button is in given state
	// and false if not. 
	public boolean checkButton(char id, int state)
	{
		// Find Button object for button represented by character id.
		for(Button b : buttonList)
		{
			if(b.getCharID() == id)
				return b.checkState(state); // Return whether or not it's in the requested state.
		}
		return false; // Return false if Input is not tracking the input from the button representing 
		              // the given character id.
	}

	// Calls the updateButton() method for all Buttons in buttonList so that they know whether the
	// button they are tracking is PRESS, HOLD, RELEASE, or none of those conditions. 
	public void updateButtons()
	{
		for(Button b : buttonList)
		{
			b.updateButton();
		}
	}

	// Objects of this class track the current state of a single source of input from either the
	// keyboard or mouse (tracks the input from a button). The class is nested in GameFrame because
	// it doesn't make sense for other classes to be able to instantiate the Button class at all. 
	class Button
	{
		private boolean prevState; // Stores whether or not the button being tracked was pressed down
								   // last frame or not. 

		private boolean press; // Set to true when the PRESS condition is true for the button being 
							   // tracked. 
		private boolean hold; // Set to true when the HOLD condition is true for the button being 
                              // tracked.
		private boolean release; // Set to true when the RELEASE condition is true for the button  
								 // being tracked.

		private int intID; // Stores the key code of the button to be tracked. If Button is using a
						   // character to identify the button it is tracking, this is set to -1.
		private char charID; // Stores the character of the button to be tracked. If Button is using a
							 // key code to identify the button it is tracking, this is set to '\n'.

		// Constructor that sets Button to track input for the button represented by the character id.
		public Button(char id)
		{
			charID = id;
			intID = -1;
			
			prevState = false; // button has never been pressed yet.
		}

		// Constructor that sets Button to track input for the button with key code id.
		public Button(int id)
		{
			intID = id;
			charID = '\n';
			
			prevState = false; // button has never been pressed yet.
		}
		
		// Updates whether the button being tracked is in the PRESS, HOLD, or RELEASE state. 
		public void updateButton()
		{
			// Reset states.
			press = false;
			hold = false;
			release = false;
			
			boolean current; // Holds whether button is being held down this frame or not.
			
			// Determine if button is being held down this frame.
			if(intID == -1)
				current = getKey(charID);
			else
				current = getKey(intID);
			
			// Set the state of the button based on whether or not it's being held down this frame and
			// whether or not it was held down last frame. 
			if(current == true && prevState == false)
				press = true;
			if(current == true && prevState == true)
				hold = true;
			if(current == false && prevState == true)
				release = true;
			
			prevState = current; // The condition of the button this frame will be the condition of
								 // the button last frame the next time this method is called. 
		}

		// Returns whether or not the button that this Button object is tracking input for is in the
		// requested state (either PRESS, HOLD, or RELEASE).
		public boolean checkState(int state)
		{
			if(state == PRESS) 
				return press;
			else if(state == HOLD)
				return hold;
			else if(state == RELEASE)
				return release;
			else
				return false; // Returns false if something non-sensical was passed through state. 
		}

		// Returns the key code of the button being tracked or -1 if Button is not using a key code to
		// track the button.
		public int getIntID()
		{
			return intID;
		}

		// Returns the character symbol of the button being tracked or '\n' if Button is not using a
		// character symbol to track the button. 
		public char getCharID()
		{
			return charID;
		}
	}
}



