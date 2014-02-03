package gameframe;

import java.util.ArrayList;

public class Surface 
{
	public static ArrayList<Surface> list = new ArrayList<Surface>();
	
	private int[] tileIDs;
	private double surCoeff;
	private double surElastic;
	private boolean background;
	
	public Surface(int[] ids, double coeff, double elast, boolean back)
	{
		
	}
	
	public Vec2D hit(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		
	}
	
	public Vec2D slide(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		
	}
	
	private void onContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		
	}
	
	private void firstContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		
	}
}
