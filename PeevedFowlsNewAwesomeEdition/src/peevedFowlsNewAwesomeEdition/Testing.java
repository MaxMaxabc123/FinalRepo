package peevedFowlsNewAwesomeEdition;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Testing 
{
	static boolean currentlyRunningGame = false;
	public static void main(String[] args)
	{	
		while(true)
		{
			if(!currentlyRunningGame)
			{
				new LevelSelectionFrame();
				currentlyRunningGame=true;
			}
			else
			{
				
			}
		}
	}	
}
