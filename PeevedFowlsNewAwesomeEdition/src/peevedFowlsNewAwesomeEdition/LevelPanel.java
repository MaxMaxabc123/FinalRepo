package peevedFowlsNewAwesomeEdition;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;




public class LevelPanel extends JPanel implements ActionListener,KeyListener
{
	// a hashtable to hold the collections of the physical objects with their respective image.
	Hashtable<PhysicalObject, Image> hashtable = new Hashtable<>();
	// the background image is needed in the game
	Image backGround;
	// the bird image is needed in the game
	Image bird;
	// the worm image is needed in the game
	Image worm;
	// the speedy image is needed in this game
	Image speedy;
	// a timer is made to keep track of how much time the game has left
	int x;
	// y cord for physical objects
	int y;
	// the game level has a specific fowl that is moving
	Fowl gameFowl;
	//level has a time function/
	int time=0;
	Set<Integer> pressedKeys = new HashSet<>();
	public static boolean BirdAlone()
	{
		if(LevelSelectionPanel.l.PhysicalObjectList.length==1)
			return true;
		else
			return false;
	}
	public static int WormCount()
	{
		int j = 0;
		for(int i = 0; i < LevelSelectionPanel.l.PhysicalObjectList.length;i++)
		{
			if(LevelSelectionPanel.l.PhysicalObjectList[i] instanceof Worm)
			{
				j++;
			}
		}
		return j;
	}
	public void RemoveSpecificObject(PhysicalObject object)
	{
		PhysicalObject[] objectList = new PhysicalObject[LevelSelectionPanel.l.PhysicalObjectList.length-1];
		int j = 0;
		for(int i = 0; i < LevelSelectionPanel.l.PhysicalObjectList.length;i++)
		{
			if(LevelSelectionPanel.l.PhysicalObjectList[i]==object)
			{
				
			}
			else
			{
				
				objectList[j]=LevelSelectionPanel.l.PhysicalObjectList[i];
				j++;
			}
		}
		LevelSelectionPanel.l.PhysicalObjectList=objectList;
	}
	LevelPanel()
	{

		//setup of the games graphics and timeer
		try 
		{
			// makes the game 800 by 800
			this.setPreferredSize(new Dimension(600,600));
			// the buffered speedy image used to resize and render the image in the scene
			BufferedImage speedyImage = ImageIO.read(new File("speedyspeedymrbobeedee.png"));
			// the background buffered used to resize and render the image in the scene
			BufferedImage backGroundImageBuff = ImageIO.read(new File("ANGRYBIRDSBACKGROUDN.png"));
			// the buffered worm image used to resize and render the image in the scene
			BufferedImage wormImg = ImageIO.read(new File("worm.png"));
			// the buffered redImage used to rresize and render the image in the scene
			BufferedImage birdImg = ImageIO.read(new File("useThisRed.png"));
			// syncs the default toolkit and makes updates of the image smooth
			Toolkit.getDefaultToolkit().sync();
			//background image set to size of the whole level which is 800 by 800
			backGround = backGroundImageBuff.getScaledInstance(800, 800, BufferedImage.SCALE_SMOOTH);
			
			
			// iterates through the list and checks what is the current indexes type is and adds a new image and object to the hashtable
			// to be rendered and used later;
			for(int i = 0; i < LevelSelectionPanel.l.PhysicalObjectList.length;i++)
			{
				// if it is a speedy bird then add the speedy image and the speedy physical object to the hashtable
				if(LevelSelectionPanel.l.PhysicalObjectList[i] instanceof Speedy)
				{
					speedy = speedyImage.getScaledInstance(LevelSelectionPanel.l.PhysicalObjectList[i].sizeOfObject, LevelSelectionPanel.l.PhysicalObjectList[i].sizeOfObject, BufferedImage.SCALE_SMOOTH);
					gameFowl = (Speedy) LevelSelectionPanel.l.PhysicalObjectList[i];
					hashtable.put(LevelSelectionPanel.l.PhysicalObjectList[i], speedy);
				}
				// if it is a worm add a new worm image and the worm into the hashtable
				else if(LevelSelectionPanel.l.PhysicalObjectList[i] instanceof Worm)
				{
					worm = wormImg.getScaledInstance(LevelSelectionPanel.l.PhysicalObjectList[i].sizeOfObject, LevelSelectionPanel.l.PhysicalObjectList[i].sizeOfObject, BufferedImage.SCALE_SMOOTH);
					hashtable.put(LevelSelectionPanel.l.PhysicalObjectList[i],worm);
				}
				// if it is a default fowl(red bird) then make a new bird (red) image then add the fowl and the image to the hashtable list
				else if(LevelSelectionPanel.l.PhysicalObjectList[i] instanceof Fowl)
				{
					bird = birdImg.getScaledInstance(LevelSelectionPanel.l.PhysicalObjectList[i].sizeOfObject, LevelSelectionPanel.l.PhysicalObjectList[i].sizeOfObject, BufferedImage.SCALE_SMOOTH);
					gameFowl = (Fowl) LevelSelectionPanel.l.PhysicalObjectList[i];
					hashtable.put(LevelSelectionPanel.l.PhysicalObjectList[i],bird);
				}
			}
			
		}
		
		// catch any exceptions made from missing images or other.
		catch(Exception e)
		{
			e.printStackTrace();
		}
		int b = 0;
		for(int i = 0;i < LevelSelectionPanel.l.PhysicalObjectList.length;i++)
		{
			for(int j=0; j < LevelSelectionPanel.l.PhysicalObjectList[i].sizeOfObject;j++)
			{
				for(int k = 0; k < LevelSelectionPanel.l.PhysicalObjectList[i].sizeOfObject;k++)
				{
					if(LevelSelectionPanel.l.PhysicalObjectList[i] instanceof Fowl)
					{
						
					}
					else
					{
						b++;
						LevelSelectionPanel.l.coordinatePlane[LevelSelectionPanel.l.PhysicalObjectList[i].yCoordinate+j][LevelSelectionPanel.l.PhysicalObjectList[i].xCoordinate+k].occupyingObject=LevelSelectionPanel.l.PhysicalObjectList[i];
					}
				}
			}
		}
	}
	public void paint(Graphics g)
	{
		// creates a 2d graphics to draw the images needed for the game
		Graphics2D g2D = (Graphics2D) g;
		// draws the background image
		g2D.drawImage(backGround, 0,0,null);
		// makes an enumeration of the physical objects in the level
		Enumeration<PhysicalObject> physicalEnum = hashtable.keys();
		// makes an enumeration of the images in the enum
		Enumeration<Image> imageEnum = hashtable.elements();
		// for all the objects in the list render the picture of the objects at the coordinates of the object 
		
		for(int i = 0; i < LevelSelectionPanel.l.PhysicalObjectList.length;i++)
		{
			// makes a coordinate based on the current coordinate of the physics object
			Coordinate currentCoord = physicalEnum.nextElement().getCoordinate();	
			//makes a x coordinate based on the current coordinate
			x=currentCoord.getXCoord();
			//makes a x coordinate based on the current coordinate
			y=currentCoord.getYCoord();
			// draws the current imgae of the respective object to the plane
			g2D.drawImage((Image)imageEnum.nextElement(),x,y,null);
		}
	}
	// listens to the key being pressed and updates the game accordingly
	@Override
	public void keyTyped(KeyEvent e) 
	{
//		switch(e.getKeyChar())
//		{
//			
//			case'a':
//			{
//				gameFowl.moveBackwardX();
//				for(int i = 0 ; i < gameFowl.sizeOfObject;i++)
//				{
//					for(int j = 0; j < gameFowl.sizeOfObject;j++)
//					{
//						if(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject!=null)
//						{
//							hashtable.remove(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
//							RemoveSpecificObject(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
//							LevelSelectionPanel.l.makeCoordinateLevelPlane();
//							System.out.println("triggered");
//						}
//					}
//				}
//				repaint();
//				break;
//			}
//			case'd':
//			{
//				gameFowl.moveForwardX();
//				for(int i = 0 ; i < gameFowl.sizeOfObject;i++)
//				{
//					for(int j = 0; j < gameFowl.sizeOfObject;j++)
//					{
//						if(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject!=null)
//						{
//							hashtable.remove(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
//							RemoveSpecificObject(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
//							LevelSelectionPanel.l.makeCoordinateLevelPlane();
//							System.out.println("triggered");
//						}
//					}
//				}
//				repaint();
//				break;
//			}
//			case'w':
//			{
//				gameFowl.moveBackwardY();
//				for(int i = 0 ; i < gameFowl.sizeOfObject;i++)
//				{
//					for(int j = 0; j < gameFowl.sizeOfObject;j++)
//					{
//						if(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject!=null)
//						{
//							hashtable.remove(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
//							RemoveSpecificObject(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
//							LevelSelectionPanel.l.makeCoordinateLevelPlane();
//							System.out.println("triggered");
//						}
//					}
//				}
//				repaint();
//				break;
//			}
//			case's':
//			{
//				gameFowl.moveForwardY();
//				for(int i = 0 ; i < gameFowl.sizeOfObject;i++)
//				{
//					for(int j = 0; j < gameFowl.sizeOfObject;j++)
//					{
//						if(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject!=null)
//						{
//							hashtable.remove(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
//							RemoveSpecificObject(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
//							LevelSelectionPanel.l.makeCoordinateLevelPlane();
//							System.out.println("triggered");
//						}
//					}
//				}
//				repaint();
//				break;
//			}
//		}
//		
	}
	// listens to the keyboard and starts the game
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		pressedKeys.add(e.getKeyCode());
		Iterator<Integer> iterator = pressedKeys.iterator();
		
		for(int a = 0; a < pressedKeys.size();a++)
		{
			
			switch(iterator.next())
			{
				
				case 65:
				{
					
					if(gameFowl.xCoordinate-gameFowl.speedOfBird<0)
					{
						gameFowl.xCoordinate=0;
						break;
					}
					else	
					{
						gameFowl.moveBackwardX();
						for(int i = 0 ; i < gameFowl.sizeOfObject;i++)
						{
							for(int j = 0; j < gameFowl.sizeOfObject;j++)
							{
								if(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject!=null)
								{
									hashtable.remove(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
									RemoveSpecificObject(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
									LevelSelectionPanel.l.makeCoordinateLevelPlane();
									GameFrame.UpdateUi();
								}
							}
						}
						repaint();
						break;
					}
				}
				case 68:
				{
					if(gameFowl.xCoordinate+gameFowl.speedOfBird+gameFowl.sizeOfObject>=LevelSelectionPanel.l.levelSizeX)
					{
						gameFowl.xCoordinate=LevelSelectionPanel.l.levelSizeX-gameFowl.sizeOfObject;
						break;
					}
					
					else
					{
						gameFowl.moveForwardX();
						for(int i = 0 ; i < gameFowl.sizeOfObject;i++)
						{
							for(int j = 0; j < gameFowl.sizeOfObject;j++)
							{
								if(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject!=null)
								{
									hashtable.remove(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
									RemoveSpecificObject(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
									LevelSelectionPanel.l.makeCoordinateLevelPlane();
									GameFrame.UpdateUi();
								}
							}
						}
						repaint();
						break;
					}
				}
				case 87:
				{
					if(gameFowl.yCoordinate-gameFowl.speedOfBird<0)
					{
						gameFowl.yCoordinate=0;
						break;
					}
					else
					{
						gameFowl.moveBackwardY();
						for(int i = 0 ; i < gameFowl.sizeOfObject;i++)
						{
							for(int j = 0; j < gameFowl.sizeOfObject;j++)
							{
								if(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject!=null)
								{
									hashtable.remove(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
									RemoveSpecificObject(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
									LevelSelectionPanel.l.makeCoordinateLevelPlane();
									GameFrame.UpdateUi();
								}
							}
						}
						repaint();
						break;
					}
				}
				case 83:
				{
					if(gameFowl.yCoordinate+gameFowl.speedOfBird+gameFowl.sizeOfObject>=LevelSelectionPanel.l.levelSizeY)
					{
						gameFowl.yCoordinate=LevelSelectionPanel.l.levelSizeY-gameFowl.sizeOfObject;
						break;
					}
					else
					{
						gameFowl.moveForwardY();
						for(int i = 0 ; i < gameFowl.sizeOfObject;i++)
						{
							for(int j = 0; j < gameFowl.sizeOfObject;j++)
							{
								if(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject!=null)
								{
									hashtable.remove(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
									RemoveSpecificObject(LevelSelectionPanel.l.coordinatePlane[gameFowl.yCoordinate+i][gameFowl.xCoordinate+j].occupyingObject);
									LevelSelectionPanel.l.makeCoordinateLevelPlane();
									GameFrame.UpdateUi();
								}
							}
						}
						repaint();
						break;
					}
				}
				case 32:
				{
					if(gameFowl instanceof Speedy)
						((Speedy) gameFowl).switchSpeed();
				}
			}
		}
			
			
	}
	// only added this here because we need it to implement the method
	@Override
	public void keyReleased(KeyEvent e) {
		pressedKeys.remove(e.getKeyCode());
		
	}
	// constantly run to print the current time of the game
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		
	}
	
	
	
	
}
