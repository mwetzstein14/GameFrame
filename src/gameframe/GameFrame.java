package gameframe;

import jgame.*;
import jgame.impl.*;
import jgame.platform.*;

public abstract class GameFrame extends JGEngine
{
	private long currentFrame = 0;
	
	public long getCurrFrame()
	{
		return currentFrame;
	}
	
	public void doFrame()
	{
		
	}
	
}



