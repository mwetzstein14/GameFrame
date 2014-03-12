package techdemo;

/* This tech demo for the GameFrame framework is an example game showing how one would use the GameFrame 
 * framework. It also shows off the main features of the framework, namely the physics and object creation
 * management. 
 */

// Must import JGame engine code for framework to work.
import jgame.*;
import gameframe.*;

// Extend the GameFrame class instead of JGEngine for your main class. 
public class GF_Tech_Demo extends GameFrame 
{
	// JGame requires a serial ID for storing game data (this example doesn't use that feature of JGame).
	private static final long serialVersionUID = -2514583079342573916L;
	
	// Variable hold current location of mouse. For use by other classes outside of GF_Tech_Demo.
	public static double mouseX = 0;
	public static double mouseY = 0;
	
	// The there are three Surface objects used in simulating the game environment:
	SteelSurface steelV; // Vertical steel walls.
	SteelSurface steelH; // Horizontal steel floors/ceilings.
	RailingSurface railing; // Railing is a background Surface.
	
	// There are two types of Actors that spawn, Diamonds and Electrodes. These ActorManagers will control their
	// creation. 
	DiamondManager dManager;
	ElectrodeManager eManager;
	
	Launcher player; // An Actor for the player object. Example of an Actor not managed by and ActorManager.

	// Entry method for game, constructor creates a new instance of GF_Tech_Demo, which will create a JGEngine
	// object that will start producing frames to a window with the given dimensions.
	public static void main(String[] args) 
	{
		new GF_Tech_Demo(new JGPoint(640, 480)); // Dimensions for application window are passed through a JGPoint.
	}
	
	// Constructor that is used above. Always call the super constructor and pass a JGPoint defining the window
	// size. Nothing else should be done here.
	public GF_Tech_Demo(JGPoint screenSize) 
	{
		super(screenSize);
	}
	
	// Method called when JGame engine is setting up, initializes canvas for producing frames with the
	// following settings.
	@Override
	public void initCanvas() 
	{
		setCanvasSettings(
				20,  // width of the canvas in tiles
				15,  // height of the canvas in tiles
				16,  // width of one tile
				16,  // height of one tile
				     //    (note: total size = 20*16=320  x  15*16=240)
				null,// foreground color (default is white)
				null,// background color (default is black)
				null // use default font for printing strings to canvas.
				);
	}
	
	// This method is called just before the engine starts producing frames. All necessary setup before producing
	// frames should be done here, such as defining images and animations, setting the tile background. In 
	// addition to these setup requirements for using JGame, GameFrame requires you to set several settings 
	// here and even initialize some objects if necessary.
	@Override
	public void initGame() 
	{	
		setMouseCursor(WAIT_CURSOR); // Indicate to user that game is loading setup by changing mouse cursor to
									 // spinning ball. 
		
		// JGame setup:
		
		setFrameRate(45, 2); // Set the game to produce frames at 45 frames per second. Allow engine to skip
							 // rendering up to two frames.
		
		defineMedia("demo_images.tbl"); // Load media used for sprites and animations defined in the media table.
		
		// Use array of strings to setup tile backdrop arrangement on canvas. Each character represents a tile.
		// H: horizontal steel, V: vertical steel, R: railing, .: empty tile space, D: spawning point for 
		// diamonds, M: spawning point for electrodes.
		setTiles(0, 1, 
				new String[] { 
				"HHHHHHHHHHHHHHHHHHHH",
				"V.R...............DV",
				"V.R................V",
				"V.R....V...........V",
				"V.R....V...........V",
				"V.R....V...........V",
				"V.R....V......HHH..V",
				"V.R....V...........V",
				"V.R................V",
				"V.R................V",
				"V.R.......HHH......V",
				"V.R................V",
				"V.R...............MV",
				"HHHHHHHHHHHHHHHHHHHH" } );
		
		// GameFrame setup:
		
		// First create arrays containing the keys that GameFrame should track input for.
		int[] nonCharKeys = new int[] {KeyUp, KeyDown, KeyShift, KeyMouse1, KeyEnter, KeyEsc};
		char[] charKeys = new char[] {'W', 'S', ' ', 'M', 'C', 'V'};
		
		trackThese(nonCharKeys, charKeys); // Tell GameFrame to track input for keys above. 
		
		Vec2D.useRadians = false; // When setting vector angles, they will be interpreted as degrees.
		
		// Turn gravity on and set the gravity vector.
		Gravity.gravOn = true;
		Gravity.set_g(new Vec2D(90.0, 0.01));
		
		// Set the max number of objects that may be created at once.
		Particle.maxParticles = 100; // Max particles.
		Projectile.maxProjectiles = 30; // Max projectiles.
		Actor.maxActors = 20; // Max actors. 
		
		// Initialize the Surface objects. Pass the collision IDs of the tiles they should be associated with.
		// These IDs are defined in the media table. 
		steelV = new SteelSurface(new int[] {1});
		steelH = new SteelSurface(new int[] {2});
		railing = new RailingSurface(new int[] {4});
		
		// Initialize the ActorManagers. Don't add them to ManagerList until the game is ready to start creating
		// the Actor objects managed by these ActorManagers. 
		dManager = new DiamondManager("diamond", 4, 450, new JGPoint(288, 33));
		eManager = new ElectrodeManager("electrode", 3, 1800, new JGPoint(288, 208));
		
		setMouseCursor(NO_CURSOR); // Game used custom cursor. Hide real one. 
		
		// Game states are a feature of JGame that allow you to run certain code only when a game state is active.
		addGameState("Intro"); // Begins game intro that teaches basics.
		addGameState("ExitGame"); // Run code that allows you to press Esc to quit game. 
	}
	
	// Override the manageObjects() method to implement any code necessary to manage game objects not managed by
	// ActorManagers (in our case, the player object). 
	@Override
	public void manageObjects()
	{
		// Player object needs access to the current mouse location.
		mouseX = getMouseX();
		mouseY = getMouseY();
		
		// If player object is currently instantiated, then its routine method needs to be manually called here.
		if(player != null)
			player.routine();
		
		super.manageObjects(); // Although the super method is called at the end here, it is not called
							   // because of the convention. Here it is necessary to make sure that 
							   // ManagerList runs methods that manage all the other Actors created 
							   // by ActorManagers. That is not always desired.  
	}
	
	// Object-object collision is done by calling checkCollision() and passing the collision IDs of the objects
	// for every desired pair whose collisions should be checked. Override this method to make these collision
	// detection calls each frame. Do not use this method for anything else, not even object-tile collisions.
	@Override
	public void collideObjects()
	{
		checkCollision(8, 8);
		checkCollision(16, 16);
		checkCollision(32, 32);
		checkCollision(8, 16);
		checkCollision(16, 8);
		checkCollision(32, 8);
		checkCollision(8, 32);
		checkCollision(32, 16);
		checkCollision(16, 32);
	}
	
	// Object-tile collision is done by calling checkBGCollision() and passing the AND of all the tile collision
	// IDs and the object collision ID for the object-tile collisions you want to check. Override this method to
	// make these collision detection calls each frame. Do not use this method for anything else, not even 
	// object-object collisions.
	@Override
	public void collideBG()
	{
		checkBGCollision(1+2+4, 1);
		checkBGCollision(1+2+4, 2);
		checkBGCollision(1+2+4, 4);
		checkBGCollision(1+2+4, 8);
		checkBGCollision(1+2+4, 16);
		checkBGCollision(1+2+4, 32);
	}
	
	// Below are methods associated with certain game states. A start<state>() method is called when a state is
	// first added. doFrame<state>() and paintFrame<state>() serve similar purposes to the doFrame() and 
	// paintFrame() methods (carrying out game logic and painting on top of frames) but are only called when
	// their associated state is active. 
	
	// Intro state is the first state to be active. It prints information to the screen for the user to read
	// before playing. 
	
	int textPage = 1; // There are several screens of information for the user to cycle through. This controls
					  // which is showing. 
	
	// Look at user input to cycle through pages of information. 
	public void doFrameIntro()
	{
		// If the enter button is pressed, move onto the next screen of information.
		if(checkButton(KeyEnter, PRESS)) // An example of how you check for buttons being pressed using GameFrame.
		{
			textPage++;
		}
		
		// If textPage reaches four, the user has read all the information and it is time to begin the game. 
		if(textPage == 4)
		{
			addGameState("InGame"); // Proceed to InGame state to initialize/setup remaining game objects.
			removeGameState("Intro"); // Remove intro state.
		}
	}
	
	// Display different information text depending on what page of information the user is on. 
	public void paintFrameIntro()
	{
		// First page of information is a welcome message. 
		if(textPage == 1)
		{	
			drawString("Welcome to the GameFrame Tech Demo!", 160.0, 100.0, 0);
			drawString("Press Enter to Continue...", 160.0, 150.0, 0);
		}
		
		// Second page is some information about GameFrame and the objective of the tech demo game. 
		if(textPage == 2)
		{
			drawString("GameFrame is a 2D game framework built on JGame.", 160.0, 60.0, 0);
			drawString("This framework implements simple physics and manages object creation.", 160.0, 80.0, 0);
			drawString("Watch these features in action as you try to shoot down as many diamonds", 160.0, 100.0, 0);
			drawString("as you can in 180 seconds using the ball launcher.", 160.0, 120.0, 0);
			drawString("Press Enter to Continue...", 160.0, 170.0, 0);
		}
		
		// Last page shows the controls for the game. 
		if(textPage == 3)
		{
			drawString("Controls:", 160.0, 40.0, 0);
			drawString("W: Move up", 160.0, 60.0, 0);
			drawString("S: Move down", 160.0, 70.0, 0);
			drawString("Shift: Brake", 160.0, 80.0, 0);
			drawString("Mouse: Aim", 160.0, 90.0, 0);
			drawString("Left Click: Shoot", 160.0, 100.0, 0);
			drawString("The mass, charge, and velocity of balls fired may be adjusted: ", 160.0, 120.0, 0);
			drawString("M: Select Ball Mass", 160.0, 130.0, 0);
			drawString("C: Select Ball Charge", 160.0, 140.0, 0);
			drawString("V: Select Ball Velocity", 160.0, 150.0, 0);
			drawString("Up Arrow: Increment Selected Property", 160.0, 160.0, 0);
			drawString("Down Arrow: Decrement Selected Property", 160.0, 170.0, 0);
			drawString("Esc: Exit Game", 160.0, 190.0, 0);
			drawString("Press Enter to Continue...", 160.0, 210.0, 0);
		}
	}
	
	// The InGame state is used to reset/start up the main game after the intro or a game over. 
	
	public static int score = 0; // Holds user's current score.
	public int time = 180; // Keeps track of time left until the game ends. Player has 3 minutes.
	public long start; // Holds the frame which the 3 minute timer started.
	
	// Initial setup for a new game. 
	public void startInGame()
	{
		// Add the ActorManager objects to ManagerList so that they now become active and start creating objects.
		ManagerList.add(dManager);
		ManagerList.add(eManager);
		
		// Set the reference frame used in finding elapsed time for the ActorManagers to the current frame.
		// Reference the ActorManagers by their string IDs. 
		ManagerList.setRef(getCurrFrame(), "diamond");
		ManagerList.setRef(getCurrFrame(), "electrode");
		
		player = new Launcher(); // Initialize a new player object.
		
		start = getCurrFrame(); // Reset the frame which the timer began.
		
		// A JGTimer object is used to trigger an event after 180 seconds that ends the InGame state and
		// begins the GameOver state. 
		new JGTimer(8100, true, "InGame") 
		{
			public void alarm() // When time goes off.
			{
					// Remove InGame state and start GameOver state. 
					removeGameState("InGame");
					addGameState("GameOver");
			}
		};
	}
	
	// While InGame, decrement the remaining time displayed by one every second. 
	public void doFrameInGame()
	{
		if((getCurrFrame()-start) % 45 == 0) // 45 frames per second, so decrement once every 45 frames. 
			time--;
	}
	
	// Paints to top of screen information about the mass, charge, and speed of the balls being launched.
	// Also prints information about the user's score and remaining time. Also displays target showing
	// where the user's mouse is.
	public void paintFrameInGame()
	{
		// Display custom crosshairs cursor to show where the ball launcher is aiming. 
		if(getMouseY() > 16)
			drawImage(getMouseX() - 8.0, getMouseY() - 8.0, "crosshairs");
		
		// Print ball mass, charge, and velocity (speed).
		drawString("Mass: " + (float)player.getBallMass(), 8.0, 4.0, -1);
		drawString("Charge: " + (float)player.getBallCharge(), 72.0, 4.0, -1);
		drawString("Velocity: " + (float)player.getBallSpeed(), 136.0, 4.0, -1);
		
		// Print user score and remaining time.
		drawString("Score: " + score, 216.0, 4.0, -1);
		drawString("Time: " + time, 272.0, 4.0, -1);

	}
	
	// GameOver state is used to end the game and show the user their score. Also offers them the opportunity
	// to play the game again. 
	
	int dispScore; // Variable that holds the user's score to be displayed while score is being reset. 
	
	public void startGameOver()
	{
		dispScore = score; // Hold onto user's obtained score.
		score = 0; // Reset score.
		time = 180; // Reset timer.
		
		// When reseting the game, ManagerList.list, ForceField.list, and Surface.list may require you to clear 
		// out all the objects contained by them unless you are sure the same exact objects can be used next time
		// around and do not need to be reset. 
		
		// Reset all Actors by removing the player Actor and all ActorManagers. ActorManagers will be reset and
		// re-added to ManagerList when the InGame state next comes around (player will be reinstantiated then too).
		ManagerList.removeAll(); 
		player.remove();
		
		ForceField.removeAll(); // Remove all ForceFields because they are associated with Actors that are no 
								// longer present.
		
		// The Surface objects do not need to be reset because they will be exactly the same at the beginning of the
		// next game. 
	}
	
	// Check for enter key to signal transition into a new game.
	public void doFrameGameOver()
	{
		if(checkButton(KeyEnter, PRESS))
		{
			// Transition into a new game by removing the GameOver state and adding the InGame state.
			removeGameState("GameOver");
			addGameState("InGame");
		}
	}
	
	// Paint message for GameOver showing the score the user achieved. 
	public void paintFrameGameOver()
	{
		drawString("Time's Up!", 160.0, 100.0, 0);
		drawString("Score: " + dispScore, 160.0, 120.0, 0);
		drawString("Press Enter to Play Again...", 160.0, 150.0, 0);
	}
	
	// The ExitGame state is always active, and allows the user to quit the game at any time by pressing the
	// Esc key. 
	public void doFrameExitGame()
	{
		if(checkButton(KeyEsc, PRESS))
			exitEngine(null);
	}
}
