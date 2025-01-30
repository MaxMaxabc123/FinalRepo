package peevedFowlsNewAwesomeEdition;

public class Speedy extends Fowl implements Speediness
{
	// checks to see if the speed has been applied
	boolean speedApply=true;
	// maker method of speedy the exact same as the normal fowl
	public Speedy(int x, int y, int size) 
	{
		super(x, y, size);
	}
	// if the speedApply is true then the speedMultipler is applied and then speedapply is made false
	// but when it is called again speedmultiply is made true and then the speed of bird is divided
	// basically this is a switch and whenever it is called it applies/reverses the speed multiplier
	@Override
	public void switchSpeed() 
	{
		// if speedApply is true then multiply the speed of the bird by the multiplier then the
		// speedApply is set to false, so that it doesnt repeatedly multiply when it is called again
		if(speedApply)
		{
			speedOfBird*=speedMultiplier;
			speedApply=false;
		}
		// if speedApply is false then first set it to true then divide the speedOfBird by the speedMultiper
		// and undo the multiplication while setting it up to be multiplied again for the future
		else
		{
			speedApply=true;
			speedOfBird/=speedMultiplier;
		}
	}

}
