package techdemo;

import jgame.*;
import gameframe.*;

public class GF_Tech_Demo extends GameFrame {
	
	public static double mouseX = 0;
	public static double mouseY = 0;
	
	public int score = 0;
	
	SteelSurface steelV;
	SteelSurface steelH;
	RailingSurface railing;
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
		setFrameRate(45, 2);
		
		defineMedia("demo_images.tbl");
		
		setTiles(0, 1, 
				new String[] { 
				"HHHHHHHHHHHHHHHHHHHH",
				"V.R................V",
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
				"V.R................V",
				"HHHHHHHHHHHHHHHHHHHH" } );
		
		int[] nonCharKeys = new int[] {KeyUp, KeyDown, KeyRight, KeyShift, KeyLeft, KeyMouse1, KeyEsc};
		char[] charKeys = new char[] {'W', 'S', ' ', 'M', 'C', 'V'};
		
		trackThese(nonCharKeys, charKeys);
		
		setCursor(null);
		
		Vec2D.useRadians = false;
		
		Gravity.gravOn = true;
		Gravity.set_g(new Vec2D(90.0, 0.01));
		
		Particle.maxParticles = 100;
		Projectile.maxProjectiles = 30;
		
		steelV = new SteelSurface(new int[] {1});
		steelH = new SteelSurface(new int[] {2});
		railing = new RailingSurface(new int[] {4});
		
		player = new Launcher();
	}
	
	@Override
	public void manageObjects()
	{
		mouseX = getMouseX();
		mouseY = getMouseY();
		
		player.routine();
		
		super.manageObjects();
	}
	
	@Override
	public void collideObjects()
	{
		checkCollision(8, 8);
	}
	
	@Override
	public void collideBG()
	{
		checkBGCollision(1+2+4, 1);
		checkBGCollision(1+2+4, 2);
		checkBGCollision(1+2+4, 4);
		checkBGCollision(1+2+4, 8);
	}
	
	@Override
	public void paintFrame()
	{
		drawImage(getMouseX() - 8.0, getMouseY() - 8.0, "crosshairs");
		
		drawString("Mass: " + (float)player.getBallMass(), 8.0, 4.0, -1);
		drawString("Charge: " + (float)player.getBallCharge(), 72.0, 4.0, -1);
		drawString("Velocity: " + (float)player.getBallSpeed(), 136.0, 4.0, -1);
		drawString("Score: " + score, 250.0, 4.0, -1);
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
}
