package gameframe.vecmath;

import jgame.JGRectangle;

public class GFUtil 
{
	public static boolean rectOverlap(JGRectangle rect1, JGRectangle rect2)
	{
		int xl1 = rect1.x;
		int xr1 = rect1.x + rect1.width-1;
		int yt1 = rect1.y;
		int yb1 = rect1.y + rect1.height-1;
		
		int xl2 = rect2.x;
		int xr2 = rect2.x + rect2.width-1;
		int yt2 = rect2.y;
		int yb2 = rect2.y + rect2.height-1;
		
		
		if(xl1 >= xl2 && xl1 <= xr2 && yt1 >= yt2 && yt1 <= yb2)
			return true;
		if(xr1 >= xl2 && xr1 <= xr2 && yt1 >= yt2 && yt1 <= yb2)
			return true;
		if(xl1 >= xl2 && xl1 <= xr2 && yb1 >= yt2 && yb1 <= yb2)
			return true;
		if(xr1 >= xl2 && xr1 <= xr2 && yb1 >= yt2 && yb1 <= yb2)
			return true;
		
		if(xl2 >= xl1 && xl2 <= xr1 && yt2 >= yt1 && yt2 <= yb1)
			return true;
		if(xr2 >= xl1 && xr2 <= xr1 && yt2 >= yt1 && yt2 <= yb1)
			return true;
		if(xl2 >= xl1 && xl2 <= xr1 && yb2 >= yt1 && yb2 <= yb1)
			return true;
		if(xr2 >= xl1 && xr2 <= xr1 && yb2 >= yt1 && yb2 <= yb1)
			return true;
		
		return false;	
	}
}
