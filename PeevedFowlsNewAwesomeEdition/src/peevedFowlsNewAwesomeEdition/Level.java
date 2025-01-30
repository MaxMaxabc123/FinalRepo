package peevedFowlsNewAwesomeEdition;

import java.util.ArrayList;

public class Level 
{
	// a level has a x plane size 
	static int levelSizeX;
	// a level has a y plane size
	static int levelSizeY;
	static // a level has a set of coordinates to repsresent the underlying code of the olane
	Coordinate[][] coordinatePlane;
	// a level has a collections of physical objects that they use
	static PhysicalObject[] PhysicalObjectList;
	//a level has a time that you must complete it in
	double levelTime;
	// makes the blank physics plane to be set later by the graphical portion of the code
	public static void makeCoordinateLevelPlane()
	{
		// makes the coordinate plane as big as both the level sizes
		coordinatePlane= new Coordinate[levelSizeX][levelSizeY];
		// makes a coordinate for each iteration of i and j up to the full size of the level using both the
		// x size and y size
		for(int i = 0; i < levelSizeY;i++)
		{
			for(int j = 0; j < levelSizeX;j++)
			{
				coordinatePlane[i][j] = new Coordinate(j,i);
			}
		}
		int b = 0;
		try
		{
			for(int i = 0;i < PhysicalObjectList.length;i++)
			{
				for(int j=0; j < PhysicalObjectList[i].sizeOfObject;j++)
				{
					for(int k = 0; k < PhysicalObjectList[i].sizeOfObject;k++)
					{
						if(LevelSelectionPanel.l.PhysicalObjectList[i] instanceof Fowl)
						{
							
						}
						else
						{
							b++;
							coordinatePlane[LevelSelectionPanel.l.PhysicalObjectList[i].yCoordinate+j][LevelSelectionPanel.l.PhysicalObjectList[i].xCoordinate+k].occupyingObject=LevelSelectionPanel.l.PhysicalObjectList[i];
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			
		}
		
	}
	public static PhysicalObject[] makePhysicsObjectList(String levelString)
	{
		// the size of the physics list is set to 0 initally
		int sizeOfPhysicsList = 0;
		// a blank physics list is made to be returned from this method
		PhysicalObject[] physicalObjectList;
		// goes through the level string and checks the string for 'F' which represents a fowl, 'W' which 
		// represents a worm, and 'S' whcih represents a speedy bird, the presence of each of these physical
		// objects increases the size of the physics object list
		for(int i = 0;i<levelString.length();i++)
		{
			if(levelString.charAt(i)=='F'||levelString.charAt(i)=='W'||levelString.charAt(i)=='S')
			{
				sizeOfPhysicsList++;
			}
		}
		
		// make the physics list that is gonna be retunred the size of the physics list that we declared earlier
		physicalObjectList= new PhysicalObject[sizeOfPhysicsList];
		//k is used as the index counter for the Physicalobjectlist list when it reads a certain char that
		// correlates to a physical object that is supposed to be put into the array
		int k = 0;
		// l is used to iterate through the level string switch
		int l = 0;
		// numTemp is used to read the number from the string
		int numTemp=0;
		// num pos denotes what the num temp is going to based on its posistion relative to the commas
		// in the string each comma iterates the numPos by one to know what the num temp is representing
		int numPos=0;
		// the x posistion of the physical object
		int posX = 0;
		// the y posistion of the physical object
		int posY = 0;
		// the size of the physical object
		int size = 0;
	
		while(true)
		{
			// find the current char at the string
			switch(levelString.charAt(l))
			{
				// if it detects a '(' it simply iterates down
				case'(':
				{
					l++;
					break;
				}
				// when it encounters a number it starts the process of converting the string to an int
				case'1','2','3','4','5','6','7','8','9','0':
				{
					// the numtemp is set to 0 for the new number
					numTemp=0;
					// do this code while the current char is a char between '0' and '9'
					do
					{
						// the number temp is equal to the current numberTemp times 10 plus the conversion of
						// the current char to an int,  when the next char  is a number
						// it multiples it by 10 and adds the current number essentially moving it up the
						// 10s digit
						numTemp = numTemp * 10 + ((int)levelString.charAt(l)-48);
						// iterate l to move down the string
						l++;
					}while(levelString.charAt(l)<='9'&&levelString.charAt(l)>='0');
					// the first number in the parenthesis is the x posistion
					if(numPos==0)
					{
						posX=numTemp;
					}
					// the second number in the parenthesis is the y posistion
					if(numPos==1)
					{
						posY=numTemp;
					}
					// the third number in the parenthesis is the size
					if(numPos == 2)
					{
						size = numTemp;
					}
					break;
				}
				// when it encounters a comma in a pair of parenthesis it iterates the nunpos to the
				// next one and moves on to the next string index
				case',':
				{
					l++;
					numPos++;
					break;
				}
				// when it encounters a ')' it resets the num posistion and movies on to the next index in the 
				//string
				case')':
				{
					numPos=0;
					l++;
					break;
				}
				//when the current String is 'S' it makes a new speedy with the corresponding variables that is
				// set to posistion k in the physical object list index and iterate down the string and iterates
				//k
				case'S':
				{
					physicalObjectList[k]= new Speedy(posX,posY,size);
					k++;
					l++;
					break;
				}
				//when the current String is 'F' it makes a new fowl with the corresponding variables that is
				// set to posistion k in the physical object list index and iterate down the string and iterates
				//k
				case'F':
				{
					physicalObjectList[k]=new Fowl(posX,posY,size);
					k++;
					l++;
					break;
				}
				//when the current String is 'W' it makes a new worm with the corresponding variables that is
				// set to posistion k in the physical object list index and iterate down the string and iterates
				//k
				case'W':
				{
					physicalObjectList[k]=new Worm(posX,posY,size);
					k++;
					l++;
					break;
				}
				// resets the posx,posy,size and speed to be able to add the next part of the string
				case'+':
				{
					posX=0;
					posY=0;
					size=0;
					l++;
					break;
				}
				// returns the physical objectList
				case':':
				{
					return physicalObjectList;
				}
			}
		}

	}
	// the level has a sizex, a size y and a phyiscs list that are part of creating it.
	public Level(int sizeX,int sizeY,PhysicalObject[] listOfPhysicalObjects,double time)
	{
		levelSizeX=sizeX;
		levelSizeY=sizeY;
		PhysicalObjectList=listOfPhysicalObjects;
		levelTime=time;
		makeCoordinateLevelPlane();
	}
}
	

