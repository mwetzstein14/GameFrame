package techdemo;

import gameframe.*;

public class ElectricField extends ForceField 
{
	private Actor source;
	
	public ElectricField(Actor sourceActor) 
	{
		super(ForceField.list.size());
		source = sourceActor;
	}

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
		double distance = 
				Math.sqrt(Math.pow((rb.x+rb.getBBox().width/2.0) - (source.x+source.getBBox().width/2.0), 2.0) 
						+ Math.pow((rb.y+rb.getBBox().width/2.0) - (source.y+source.getBBox().width/2.0), 2.0));
		
		if(distance < 40.0)
			return true;
		else
			return false;
	}

	@Override
	protected Vec2D calcForce(RBObject rb) 
	{
		if(rb instanceof Electrode)
			return new Vec2D(0.0, 0.0);
		
		double comboCharge = rb.getCharge()*source.getCharge();
		
		double distance = 
				Math.sqrt(Math.pow((rb.x+rb.getBBox().width/2.0) - (source.x+source.getBBox().width/2.0), 2.0) 
						+ Math.pow((rb.y+rb.getBBox().width/2.0) - (source.y+source.getBBox().width/2.0), 2.0));
		
		Vec2D force = new Vec2D(new Coord(rb.x+rb.getBBox().width/2.0, rb.y+rb.getBBox().width/2.0), 
				new Coord(source.x+source.getBBox().width/2.0, source.y+source.getBBox().width/2.0));
		force.changeMag(-5.0*comboCharge/Math.pow(distance, 2.0));
		
		return force;
	}

}
