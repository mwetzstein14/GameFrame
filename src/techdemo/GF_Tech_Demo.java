package techdemo;

import jgame.*;
import gameframe.*;

public class GF_Tech_Demo extends GameFrame 
{
	private static final long serialVersionUID = -2514583079342573916L;
	
	SteelSurface steelV;
	SteelSurface steelH;
	RailingSurface railing;
	
	DiamondManager dManager;
	ElectrodeManager eManager;
	
	Launcher player;

	public static void main(String[] args) 
	{
		new GF_Tech_Demo(new JGPoint(640, 480));
	}
	
	public GF_Tech_Demo(JGPoint screenSize) 
	{
		super(screenSize);
	}
	
	@Override
	public void initCanvas() 
	{
		setCanvasSettings(
				20,  // width of the canvas in tiles
				15,  // height of the canvas in tiles
				16,  // width of one tile
				16,  // height of one tile
				     //    (note: total size = 20*16=320  x  15*16=240)
				null,// foreground colour -> use default colour white
				null,// background colour -> use default colour black
				null // standard font -> use default font
				);
	}

	@Override
	public void initGame() 
	{	
		setMouseCursor(WAIT_CURSOR);
		
		setFrameRate(45, 2);
		
		defineMedia("demo_images.tbl");
		
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
		
		int[] nonCharKeys = new int[] {KeyUp, KeyDown, KeyShift, KeyMouse1, KeyEnter, KeyEsc};
		char[] charKeys = new char[] {'W', 'S', ' ', 'M', 'C', 'V'};
		
		trackThese(nonCharKeys, charKeys);
		
		Vec2D.useRadians = false;
		
		Gravity.gravOn = true;
		Gravity.set_g(new Vec2D(90.0, 0.01));
		
		Particle.maxParticles = 100;
		Projectile.maxProjectiles = 30;
		Actor.maxActors = 20;
		
		steelV = new SteelSurface(new int[] {1});
		steelH = new SteelSurface(new int[] {2});
		railing = new RailingSurface(new int[] {4});
		
		dManager = new DiamondManager("diamond", 4, 450, new JGPoint(288, 33));
		eManager = new ElectrodeManager("electrode", 3, 1800, new JGPoint(288, 208));
		
		setMouseCursor(NO_CURSOR);
		
		addGameState("Intro");
		addGameState("ExitGame");
	}
	
	@Override
	public void manageObjects()
	{
		mouseX = getMouseX();
		mouseY = getMouseY();
		
		if(player != null)
			player.routine();
		
		super.manageObjects();
	}
	
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
	
	class Crosshairs extends JGObject
	{
		public Crosshairs()
		{
			super("crosshairs", false, 0.0, 0.0, 0, "crosshairs");
		}
		
		@Override
		public void move()
		{
			x = getMouseX() - 8.0;
			y = getMouseY() - 8.0;
		}
	}
	
	
	int textPage = 1;
	
	public void doFrameIntro()
	{
		if(checkButton(KeyEnter, PRESS))
		{
			textPage++;
		}
		
		if(textPage == 4)
		{
			addGameState("InGame");
			removeGameState("Intro");
		}
	}
	
	public void paintFrameIntro()
	{
		if(textPage == 1)
		{
			// Welcome message.
			
			drawString("Welcome to the GameFrame Tech Demo!", 160.0, 100.0, 0);
			drawString("Press Enter to Continue...", 160.0, 150.0, 0);
		}
		
		if(textPage == 2)
		{
			drawString("GameFrame is a 2D game framework built on JGame.", 160.0, 60.0, 0);
			drawString("This framework implements simple physics and manages object creation.", 160.0, 80.0, 0);
			drawString("Watch these features in action as you try to shoot down as many diamonds", 160.0, 100.0, 0);
			drawString("as you can in 180 seconds using the ball launcher.", 160.0, 120.0, 0);
			drawString("Press Enter to Continue...", 160.0, 170.0, 0);
		}
		
		if(textPage == 3)
		{
			// Controls.
			
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
			drawString("Up Key: Increment Selected Property", 160.0, 160.0, 0);
			drawString("Down Key: Decrement Selected Property", 160.0, 170.0, 0);
			drawString("Esc: Exit Game", 160.0, 190.0, 0);
			drawString("Press Enter to Continue...", 160.0, 210.0, 0);
		}
	}
	
	public static double mouseX = 0;
	public static double mouseY = 0;
	
	public static int score = 0;
	public int time = 180;
	public long start;
	
	public void startInGame()
	{
		ManagerList.add(dManager);
		ManagerList.add(eManager);
		
		ManagerList.setRef(getCurrFrame(), "diamond");
		ManagerList.setRef(getCurrFrame(), "electrode");
		
		player = new Launcher();
		
		start = getCurrFrame();
		
		new JGTimer(8100, true, "InGame") 
		{
			public void alarm() 
			{
					removeGameState("InGame");
					addGameState("GameOver");
			}
		};
	}
	
	public void doFrameInGame()
	{
		if((getCurrFrame()-start) % 45 == 0)
			time--;
	}
	
	public void paintFrameInGame()
	{
		if(getMouseY() > 16)
			drawImage(getMouseX() - 8.0, getMouseY() - 8.0, "crosshairs");
		
		drawString("Mass: " + (float)player.getBallMass(), 8.0, 4.0, -1);
		drawString("Charge: " + (float)player.getBallCharge(), 72.0, 4.0, -1);
		drawString("Velocity: " + (float)player.getBallSpeed(), 136.0, 4.0, -1);
		drawString("Score: " + score, 216.0, 4.0, -1);
		drawString("Time: " + time, 272.0, 4.0, -1);

	}
	
	int dispScore;
	
	public void startGameOver()
	{
		dispScore = score;
		score = 0;
		time = 180;
		
		ManagerList.removeAll();
		player.remove();
		
		ForceField.removeAll();
	}
	
	public void doFrameGameOver()
	{
		if(checkButton(KeyEnter, PRESS))
		{
			removeGameState("GameOver");
			addGameState("InGame");
		}
	}
	
	public void paintFrameGameOver()
	{
		drawString("Time's Up!", 160.0, 100.0, 0);
		drawString("Score: " + dispScore, 160.0, 120.0, 0);
		drawString("Press Enter to Play Again...", 160.0, 150.0, 0);
	}
	
	public void doFrameExitGame()
	{
		if(checkButton(KeyEsc, PRESS))
			exitEngine(null);
	}
}
