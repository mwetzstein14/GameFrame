package gameframe;

import jgame.*;
import jgame.impl.*;
import jgame.platform.*;

/*
 * GameFrame is a framework built on top of the JGEngine which adds more specific functionalities to
 * the Engine (much like how StdGame, which comes with JGEngine, is a framework that adds more useful
 * functionalities to the Engine for making highscore, level based games). GameFrame is designed for 
 * two purposes, to manage the creation and organization of game objects while your game is running 
 * and to implement simple physics for effects in your game. GameFrame categorizes several different
 * types of game object classes from which you can extend your own classes for game objects: Actor, 
 * Particle, Pickup, and Projectile (see source code files of these classes for descriptions). Each
 * of these classes extend the RBObject class, which has all the standard physics capabilities they
 * share in common. GameFrame is also capable of implementing some physics, such as (physical)
 * collisions, frictional forces, forces from force fields, gravity, insulating and conducting charge,
 * etc. GameFrame does not support complex physics, such as torque. The philosophy behind what sort of
 * physics is included is that GameFrame is for creating 2D games, not simulations, so the physics 
 * needed to create interesting and creative games does not need to be thorough or accurate because
 * the goal is not to simulate real life. When using GameFrame, one can choose whether they want to 
 * use the physics or organizational tools of GameFrame. Either can be avoided, although using your 
 * own organizational approach to creating and categorizing game objects may require more code than
 * simply turning off the physics capabilities for any or all of your game objects. 
 * 
 * To use GameFrame, one must follow similar steps to using the JGEngine, except your main class 
 * should extend GameFrame instead of JGEngine. One may also extend the other classes included with
 * the GameFrame framework, for creating things such as game objects and force fields, as necessary.
 * When overriding methods for any class included in GameFrame however, it is important to remember
 * that it is a convention to call the super class's method at the end of the overridden method in
 * order to implement the functionality of the framework properly. One must similarly implement the 
 * initCanvas() and initGame() methods, as one would do when using the JGEngine, before producing 
 * frames with the engine. You are responsible for setting any necessary settings for the framework
 * (such as gravity, etc) in the initGame() method, and are responsible for creating ActorManager
 * objects, ForceFields, etc in this method or where otherwise appropriate. ActorManager objects must
 * be added to ManagerList manually. Finally, one must override the doFrame() method and call the 
 * appropriate activities() method inside it in order to use the full functionality of the framework
 * (game objects will not do anything if none of these methods are not called in doFrame()). Do not
 * forget to call the super doFrame() method at the end of doFrame() (as is convention). 
 */

@SuppressWarnings({ "serial", "unused" }) // Suppresses unnecessary warnings. 
public abstract class GameFrame extends JGEngine
{
	protected static long currentFrame = 0; // Used to keep track of how many frames have passed 
											// since the game has started running or since it was
											// last reset. Static so that it can be accessed by 
											// other classes in framework.
	
	// Getter method used by other classes or objects in order to get the current number of frames
	// the game has been running. 
	public static long getCurrFrame()
	{
		return currentFrame;
	}
	
	// Method used to reset currentFrame to zero. May be called in situations where the game resets,
	// changes to a different state (ex: title screen -> first level) or to a different stage or
	// level in the game. 
	public static void resetFrame()
	{
		currentFrame = 0;
	}
	
	// Constructor if your game is being launched as an application. Call this super constructor in
	// your class's constructor and pass it a JGPoint containing the dimensions of the screen in 
	// pixels.
	public GameFrame(JGPoint screenSize)
	{
		initEngine(screenSize.x, screenSize.y);
	}
	
	// Constructor if your game is being launched as an applet. Call this super constructor in your
	// class's constructor to have game launch as an applet. 
	public GameFrame()
	{
		initEngineApplet();
	}
	
	// The doFrame() method is a method that's used to update your game each frame. It is overridden
	// in GameFrame in order to have currentFrame increment at the end of each frame. Call the super
	// doFrame() method at the end of your class's doFrame() method in order to have it properly 
	// keep track of the number of frames that have passed. 
	@Override
	public void doFrame()
	{
		currentFrame++;
	}
	
	// The activities() methods below are used to properly update game objects which are created
	// using classes in the GameFrame framework or classes derived from those classes. The 
	// appropriate activities() method(s) should be called in doFrame() depending on what objects
	// you want to be updated. 
	
	// The general purpose activities() method. Takes no parameters and updates all game objects as 
	// well as all ActorManagers.
	public void activities()
	{
		ManagerList.destroyAll();
		ManagerList.spawnAll();
		ManagerList.routinesAll();
		
		moveObjects();
		checkCollision(0,0);
		checkBGCollision(0,0);
		moveObjects();
	}
	
	// A version of activities() that lets one only update the ActorManagers whose String ID matches
	// the argument passed through manid. All game objects are still updated. 
	public void activities(String manid)
	{
		ManagerList.destroyID(manid);
		ManagerList.spawnID(manid);
		ManagerList.routinesID(manid);
		
		moveObjects();
		checkCollision(0,0);
		checkBGCollision(0,0);
		moveObjects();
	}
	
	// A version of activities() that lets one choose which game objects should be updated and 
	// checked for collisions. The collision ID of the objects to be updated should be passed through
	// objcid. The collision IDs of the other objects and tiles to check collisions against should be
	// passed through srccid and tilecid. You can pass 0 through either of those parameters in order
	// to have it check collisions against all other game objects or all tiles. All ActorManagers are
	// still updated.
	public void activities(int objcid, int srccid, int tilecid)
	{
		ManagerList.destroyAll();
		ManagerList.spawnAll();
		ManagerList.routinesAll();
		
		moveObjects(null, objcid);
		checkCollision(srccid, objcid);
		checkBGCollision(tilecid, objcid);
		moveObjects(null, objcid);
	}
	
	// A version of activities() that combines the two above, letting you choose which ActorManagers
	// and which game objects are updated (as well as what collisions are check for the game objects
	// being updated). 
	public void activities(String manid, int objcid, int srccid, int tilecid)
	{
		ManagerList.destroyID(manid);
		ManagerList.spawnID(manid);
		ManagerList.routinesID(manid);
		
		moveObjects(null, objcid);
		checkCollision(srccid, objcid);
		checkBGCollision(tilecid, objcid);
		moveObjects(null, objcid);
	}
	
	/*
	 * The GameFrame class can check input from a user/player. It can read input from both
	 * the keyboard and mouse. (Note: if application is for a mobile or android device, then the input
	 * may correspond to different things, ex: a left mouse click would be a tap on an android 
	 * device's screen. GameFrame is not designed yet to check input for android or mobile phone 
	 * devices yet (might be a thing to add in later) so I am not sure if all input from those kinds 
	 * of devices is properly supported yet.) Use the trackAll() or trackThese() methods to tell   
	 * GameFrame what new set of Buttons it should track. 
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



