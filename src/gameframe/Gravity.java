package gameframe;

public abstract class Gravity 
{
	public static boolean gravOn = false;
	public static Vec2D g = new Vec2D(new Coord(0, -1));
	
	public static void set_g(Vec2D vec)
	{
		g = vec;
	}
	
	public static Vec2D get_g()
	{
		return g;
	}
}
