package gameframe;

import java.util.ArrayList;

public abstract class ForceField 
{
	protected ArrayList<ForceField> list = new ArrayList<ForceField>();
	
	private int fieldID;
	
	public ForceField(int id)
	{
		
	}
	
	public int getID()
	{
		
	}
	
	public Vec2D getForce(RBObject rb)
	{
		
	}
	
	protected abstract void onEnter(RBObject rb);
	
	protected abstract void whileIn(RBObject rb);
	
	protected abstract boolean inField(RBObject rb);
	
	protected abstract Vec2D calcForce(RBObject rb);
}
