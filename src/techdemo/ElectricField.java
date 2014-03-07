package techdemo;

import gameframe.ForceField;
import gameframe.RBObject;
import gameframe.Vec2D;

public class ElectricField extends ForceField 
{

	@Override
	protected void onEnter(RBObject rb) 
	{
		
	}

	@Override
	protected void whileIn(RBObject rb) 
	{

	}

	@Override
	public boolean inField(RBObject rb) 
	{
		return false;
	}

	@Override
	protected Vec2D calcForce(RBObject rb) 
	{
		return null;
	}

}
