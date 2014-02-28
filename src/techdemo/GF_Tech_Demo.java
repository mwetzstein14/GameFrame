package techdemo;

import jgame.*;
import gameframe.*;

public class GF_Tech_Demo extends GameFrame {
	
	SteelSurface steel;
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
		
		Gravity.gravOn = true;
		Gravity.set_g(new Vec2D(90.0, 0.01));
		
		steel = new SteelSurface();
		
		player = new Launcher();
	}
	
	@Override
	public void manageObjects()
	{
		player.routine();
		
		super.manageObjects();
	}
	
	@Override
	public void collideObjects()
	{
		
	}
	
	@Override
	public void collideBG()
	{
		checkBGCollision(1+2+4, 1);
	}
	
	@Override
	public void paintFrame()
	{
		drawImage(getMouseX() - 8.0, getMouseY() - 8.0, "crosshairs");
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
