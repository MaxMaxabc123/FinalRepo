package peevedFowls;

import java.util.ArrayList;

public abstract class PhysicsObject
{
	protected Vector objectPosistion;
	protected Vector objectVelocity;
	protected Vector gatvity;
	protected int objectMass;
	protected int xCoordinate;
	protected int yCoordinate;
	protected int previousXCord=0;
	protected int previousYCord=0;
	protected int biggestHeight;
	protected int objectSize;
	protected Coordinate coordinatePhysics;
	double newCollisionTime=0;
	double momentumX=0;
	double momentumY=0;
	int[][] coordinateBox;
	protected boolean hitObjectXAxis=false;
	protected boolean hitObjectYAxis=false;
	boolean slatedToBeDestroyed=false;
	public void changeBooleanTrue()
	{
		slatedToBeDestroyed=true;
	}
	
	
	public Coordinate getCoordinate()
	{
		coordinatePhysics = new Coordinate(xCoordinate,yCoordinate);
		return coordinatePhysics;
	}

	public void improvedCollision(PhysicsObject incomingObject)
	{ 
		int j = 0;
		PhysicsObject[] replacementPhysicsObjectList = new PhysicsObject[GameMain.gameLevel.physicsListLevel.length-1];
		for(int i = 0; i < GameMain.gameLevel.physicsListLevel.length;i++)
		{
			if(GameMain.gameLevel.physicsListLevel[i]==this)
			{
				System.out.println(incomingObject);
			}
			else
			{
				replacementPhysicsObjectList[j]=GameMain.gameLevel.physicsListLevel[i];
				j++;
			}
		}
		GameMain.gameLevel.physicsListLevel=replacementPhysicsObjectList;
		
//		for(int i = 0; i < incomingObject.objectSize;i++)
//		{
//			for(int j = 0; j < incomingObject.objectSize;j++)
//			{
//				if((incomingObject.xCoordinate+j)<=((int)xCoordinate+(1.0/2*objectSize))&&(incomingObject.yCoordinate+i)<=((int)yCoordinate+(1.0/2*objectSize)))
//				{
//					occupidedQuadI=true;
//				}
//				if((incomingObject.xCoordinate+j)<=((int)xCoordinate+(1.0/2*objectSize))&&(incomingObject.yCoordinate+i)>=((int)yCoordinate+(1.0/2*objectSize)))
//				{
//					occupidedQuadIII=true;
//				}
//				if((incomingObject.xCoordinate+j)>=((int)xCoordinate+(1.0/2*objectSize))&&(incomingObject.yCoordinate+i)<=((int)yCoordinate+(1.0/2*objectSize)))
//				{
//					occupidedQuadII=true;
//				}
//				if((incomingObject.xCoordinate+j)>=((int)xCoordinate+(1.0/2*objectSize))&&(incomingObject.yCoordinate+i)>=((int)yCoordinate+(1.0/2*objectSize)))
//				{
//					occupidedQuadIIII=true;
//				}
//			}
//		}
//		int incomingXPosComp =(int) incomingObject.objectVelocity.getAt(0).evaluate(time);
//		String newObjectPosistionStringX=objectPosistion.getAt(0).toString();
//		newObjectPosistionStringX+='+';
//		newObjectPosistionStringX+=incomingXPosComp;
//		newObjectPosistionStringX+="t:";
//		objectPosistion.vectorAlgebraicEquation.set(0, GameMain.generateAlgebraicEquation(newObjectPosistionStringX));	
//		int incomingYPosComp = (int) incomingObject.objectVelocity.getAt(1).evaluate(time);
//		String newObjectPosistionStringY=objectPosistion.getAt(1).toString();
//		newObjectPosistionStringY+='+';
//		newObjectPosistionStringY+=incomingYPosComp;
//		newObjectPosistionStringY+="t:";
//		objectPosistion.vectorAlgebraicEquation.set(1, GameMain.generateAlgebraicEquation(newObjectPosistionStringY));
//		int incomingXVelComp = (int) incomingObject.objectVelocity.getAt(0).evaluate(time);
//		String newObjectVelocityStringX=objectVelocity.getAt(0).toString();
//		newObjectVelocityStringX+='+';
//		newObjectVelocityStringX+=incomingXVelComp;
//		newObjectVelocityStringX+=':';
//		objectVelocity.vectorAlgebraicEquation.set(0,GameMain.generateAlgebraicEquation(newObjectVelocityStringX));
//		int incomingYVelComp = (int) incomingObject.objectVelocity.getAt(1).evaluate(time);
//		String newObjectVelocityStringY=objectVelocity.getAt(1).toString();
//		newObjectVelocityStringY+='+';
//		newObjectVelocityStringY+=incomingYVelComp;
//		newObjectVelocityStringY+=':';
//		objectVelocity.vectorAlgebraicEquation.set(1,GameMain.generateAlgebraicEquation(newObjectVelocityStringY));
//		incomingObject.xCoordinate = incomingObject.previousXCord;
//		incomingObject.yCoordinate = incomingObject.previousYCord;
//		double yDiff=(yCoordinate-(incomingObject.yCoordinate+(incomingObject.objectSize/2.0)));
//		double xDiff=(incomingObject.xCoordinate-(xCoordinate+(objectSize/2.0)));
//		if(this instanceof Block&&incomingObject instanceof Block)
//		{
//			PhysicsObject[] collissionList = new PhysicsObject[2];
//		}
//		if(yDiff<0&&xDiff<0)
//		{
//			incomingObject.objectPosistion.valueMultiplierOfVectorY=Math.sin((Math.atan(yDiff/xDiff)+Math.toRadians(180)));
//			incomingObject.objectPosistion.valueMultiplierOfVectorX=Math.cos((Math.atan(yDiff/xDiff)+Math.toRadians(180)));
//			this.objectPosistion.valueMultiplierOfVectorX = Math.cos((Math.atan(yDiff/xDiff)+Math.toRadians(180)))*-1.0;
//			this.objectPosistion.valueMultiplierOfVectorY = Math.sin((Math.atan(yDiff/xDiff)+Math.toRadians(180)))*-1.0;
//		}
//		incomingObject.objectPosistion.valueMultiplierOfVectorY=Math.sin((Math.atan(yDiff/xDiff)));
//		incomingObject.objectPosistion.valueMultiplierOfVectorX=Math.cos((Math.atan(yDiff/xDiff)));
//		this.objectPosistion.valueMultiplierOfVectorX = Math.cos((Math.atan(yDiff/xDiff)));
//		this.objectPosistion.valueMultiplierOfVectorY = Math.sin((Math.atan(yDiff/xDiff)));
////		incomingObject.objectPosistion.valueMultiplierOfVectorY=Math.sin((Math.atan(yDiff/xDiff)));
////		incomingObject.objectPosistion.valueMultiplierOfVectorX=Math.cos((Math.atan(yDiff/xDiff)));
////		System.out.println(incomingObject.objectPosistion.valueMultiplierOfVectorX);
//		System.out.println("fuck");
//		occupidedQuadI=false;
//		occupidedQuadII=false;
//		occupidedQuadIII=false;
//		occupidedQuadIIII=false;
//		newCollisionTime=time;
	}
	
	public void Move(double time)
	{
		if(xCoordinate+objectSize>=GameMain.gameLevel.levelSizeX||xCoordinate<=0)
		{
			objectPosistion.valueMultiplierOfVectorX*=-1;
		}
		if(yCoordinate+objectSize>=GameMain.gameLevel.levelSizeY||yCoordinate<=0)
		{
			objectPosistion.valueMultiplierOfVectorY*=-1;
		}
		xCoordinate+=(int) objectPosistion.evaluateVectorX(time);
		yCoordinate+=(int) objectPosistion.evaluateVectorY(time);
		
//		System.out.println("Y"+yCoordinate);
//		System.out.println("X"+xCoordinate);
//		System.out.println("Previous x Cord: " + previousXCord);
//		System.out.println("Previous y Cord: " + previousYCord);
//		System.out.println("current x Cord: " + xCoordinate);
//		System.out.println("current y cord: "+ yCoordinate);
//		System.out.println(this);
	}
	public void getCollided(Vector incomingPosistion, Vector incomingVelocity)
	{
		String incomingPosistionXString; 
		String incomingPosistionYString;
		String currentPosistionXString;
		String currentPosistionYString;
		String incomingVelocityXString; 
		String incomingVelocityYString;
		String currentVelocityXString;
		String currentVelocityYString;
		ArrayList<AlgebraicEquation> newStoragePosistionVector = new ArrayList<AlgebraicEquation>();
		ArrayList<AlgebraicEquation> newStorageVeloctiyVector = new ArrayList<AlgebraicEquation>();
		incomingPosistionXString = incomingPosistion.vectorAlgebraicEquation.get(0).toString().substring(0, incomingPosistion.vectorAlgebraicEquation.get(0).toString().length());
		incomingPosistionYString = incomingPosistion.vectorAlgebraicEquation.get(1).toString().substring(0, incomingPosistion.vectorAlgebraicEquation.get(1).toString().length());
		currentPosistionXString = objectPosistion.vectorAlgebraicEquation.get(0).toString().substring(0, objectPosistion.vectorAlgebraicEquation.get(0).toString().length());
		currentPosistionYString = objectPosistion.vectorAlgebraicEquation.get(1).toString().substring(0, objectPosistion.vectorAlgebraicEquation.get(1).toString().length());
		incomingVelocityXString = incomingVelocity.vectorAlgebraicEquation.get(0).toString().substring(0, incomingVelocity.vectorAlgebraicEquation.get(0).toString().length());
		incomingVelocityYString = incomingVelocity.vectorAlgebraicEquation.get(1).toString().substring(0, incomingVelocity.vectorAlgebraicEquation.get(1).toString().length());
		currentVelocityXString = objectVelocity.vectorAlgebraicEquation.get(0).toString().substring(0, objectVelocity.vectorAlgebraicEquation.get(0).toString().length());
		currentVelocityYString = objectVelocity.vectorAlgebraicEquation.get(1).toString().substring(0, objectVelocity.vectorAlgebraicEquation.get(1).toString().length());
		// new posistion vector
		if(incomingPosistionXString.charAt(0)=='-')
		{
			newStoragePosistionVector.add(GameMain.generateAlgebraicEquation(currentPosistionXString+incomingPosistionXString+":"));
		}
		else
		{
			newStoragePosistionVector.add(GameMain.generateAlgebraicEquation(currentPosistionXString+"+"+incomingPosistionXString+":"));
		}
		if(incomingPosistionYString.charAt(0)=='-')
		{
			newStoragePosistionVector.add(GameMain.generateAlgebraicEquation(currentPosistionYString+incomingPosistionYString+":"));
		}
		else
		{
			newStoragePosistionVector.add(GameMain.generateAlgebraicEquation(currentPosistionYString+"+"+incomingPosistionYString+":"));
		}
		// new velocity vectors
		if(incomingVelocityXString.charAt(0)=='-')
		{
			newStorageVeloctiyVector.add(GameMain.generateAlgebraicEquation(currentVelocityXString+incomingVelocityXString+":"));
		}
		else
		{
			newStorageVeloctiyVector.add(GameMain.generateAlgebraicEquation(currentVelocityXString+"+"+incomingVelocityXString+":"));
		}
		if(incomingVelocityYString.charAt(0)=='-')
		{
			newStorageVeloctiyVector.add(GameMain.generateAlgebraicEquation(currentVelocityYString+incomingVelocityYString+":"));
		}
		else
		{
			newStorageVeloctiyVector.add(GameMain.generateAlgebraicEquation(currentVelocityYString+"+"+incomingPosistionYString+":"));
		}
		objectPosistion = new Vector(newStoragePosistionVector);
		objectVelocity = new Vector(newStorageVeloctiyVector);
		
	}
}
	
	

