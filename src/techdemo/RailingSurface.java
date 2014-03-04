package techdemo;

import gameframe.RBObject;
import gameframe.Surface;

public class RailingSurface extends Surface 
{
	public RailingSurface(int[] cids)
	{
		super(cids, 0.2, 0.2, 1.0, true);
	}
	
	@Override
	public void onContact(RBObject rb, int tx, int ty, int txsize, int tysize)
	{
		
	}
}
