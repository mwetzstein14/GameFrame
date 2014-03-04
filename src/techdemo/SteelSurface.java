package techdemo;

import gameframe.RBObject;
import gameframe.Surface;

public class SteelSurface extends Surface 
{
	public SteelSurface(int[] cids)
	{
		super(cids, 0.5, 0.5, 0.7, false);
	}
	
	@Override
	public void firstContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		
	}
}
