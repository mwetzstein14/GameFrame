package techdemo;

import jgame.*;
import gameframe.*;

public class GF_Tech_Demo extends GameFrame {

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
		setFrameRate(
				60,// 35 = frame rate, 35 frames per second
				2  //  2 = frame skip, skip at most 2 frames before displaying
				   //      a frame again
			);
		
		defineMedia("demo_images.tbl");
	}
	
	
}
