package gameframe;

import java.util.ArrayList;

public class Surface 
{
	public static ArrayList<Surface> list = new ArrayList<Surface>();
	
	protected int[] tileIDs;
	protected double coeffK;
	protected double coeffS;
	protected double elastic;
	private boolean background;
	
	public Surface(int[] ids, double k, double s, double elast, boolean back)
	{
		
	}
	
	public Vec2D hit(RBObject rb, int tilecid, int tx, int ty, int txsize, int tysize)
	{
		
	}
	
	public Vec2D slide(RBObject rb, int tilecid, int tx, int ty, int txsize, int tysize)
	{
		
	}
	
	protected void onContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		
	}
	
	protected void firstContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		
	}
}
