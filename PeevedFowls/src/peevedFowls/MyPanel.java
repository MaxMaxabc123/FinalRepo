package peevedFowls;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;


public class MyPanel extends JPanel implements ActionListener
{
	final int PANEL_WIDTH = GameMain.gameLevel.levelSizeX;
	final int PANEL_HEIGHT = GameMain.gameLevel.levelSizeX;
	Hashtable<PhysicsObject, Image> hashtable = new Hashtable<>();
	Image pig;
	Image bird;
	Image block;
	Image backGroundImage;
	Timer timer;
	JTextField textField;
	JButton button;
	JSlider sliderOfAngle;
	JSlider powerLevel;
	int x = 0;
	int y = 0;
	static double time=0;
	static double angle;
	boolean buttonPushed=false;
	MyPanel()
	{
		try
		{
			this.setBackground(Color.black);
			this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
			BufferedImage backGroundImageBuff = ImageIO.read(new File("ANGRYBIRDSBACKGROUDN.png"));
			BufferedImage pigImg = ImageIO.read(new File("useThisPig.png"));
			BufferedImage birdImg = ImageIO.read(new File("useThisRed.png"));
			BufferedImage blockImg = ImageIO.read(new File("useThisBlock.png"));
			timer = new Timer(33,this);
			Toolkit.getDefaultToolkit().sync();
			timer.start();
			backGroundImage = backGroundImageBuff.getScaledInstance(PANEL_WIDTH, PANEL_HEIGHT, BufferedImage.SCALE_SMOOTH);
			for(int i = 0; i < GameMain.gameLevel.physicsListLevel.length;i++)
			{
				if(GameMain.gameLevel.physicsListLevel[i] instanceof Fowl)
				{
					bird = birdImg.getScaledInstance(GameMain.gameLevel.physicsListLevel[i].objectSize, GameMain.gameLevel.physicsListLevel[i].objectSize,BufferedImage.SCALE_SMOOTH);
					hashtable.put(GameMain.gameLevel.physicsListLevel[i],bird);
				}
				else if(GameMain.gameLevel.physicsListLevel[i] instanceof Pig)
				{
					pig = pigImg.getScaledInstance(GameMain.gameLevel.physicsListLevel[i].objectSize, GameMain.gameLevel.physicsListLevel[i].objectSize,BufferedImage.SCALE_SMOOTH);
					hashtable.put(GameMain.gameLevel.physicsListLevel[i],pig);
				}
				else if(GameMain.gameLevel.physicsListLevel[i] instanceof Block)
				{
					block = blockImg.getScaledInstance(GameMain.gameLevel.physicsListLevel[i].objectSize, GameMain.gameLevel.physicsListLevel[i].objectSize,BufferedImage.SCALE_SMOOTH);
					hashtable.put(GameMain.gameLevel.physicsListLevel[i],block);
				}
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

		button = new JButton("Submit wat you need");
		button.addActionListener(this);
		sliderOfAngle = new JSlider(JSlider.VERTICAL,0,90,0);
		sliderOfAngle.setMinorTickSpacing(1);
		sliderOfAngle.setMajorTickSpacing(10);
		sliderOfAngle.setPaintTicks(true);
		sliderOfAngle.setPaintLabels(true);
		sliderOfAngle.addChangeListener(new MyChangeListener());
		powerLevel = new JSlider(JSlider.VERTICAL,1,5,1);
		powerLevel.setMajorTickSpacing(1);
		powerLevel.setPaintTicks(true);
		powerLevel.setPaintLabels(true);
		powerLevel.addChangeListener(null);
		powerLevel.addChangeListener(new MyChangeListener());
		this.add(button);
		this.add(sliderOfAngle);
		this.add(powerLevel);
		
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2D=   (Graphics2D) g;
		g2D.drawImage(backGroundImage,0,0,null);
		Enumeration<PhysicsObject> physicsEnum = hashtable.keys();
		Enumeration<Image> imageEnum = hashtable.elements();
		for(int i = 0; i < GameMain.gameLevel.physicsListLevel.length;i++)
		{
			Coordinate currentCoord = physicsEnum.nextElement().getCoordinate();
			System.out.println();
			x=currentCoord.getXCoord();
			y=currentCoord.getYCoord();
			
			g2D.drawImage((Image)imageEnum.nextElement(),x,y,null);
		}
	}
	class MyChangeListener implements ChangeListener {
	    @Override
	    public void stateChanged(ChangeEvent e) 
	    {
	        MyPanel.angle=sliderOfAngle.getValue();
	        System.out.println(MyPanel.angle);
//	        System.out.println(Math.cos(Math.toRadians(angle)));
//	        System.out.println(powerLevel.getValue());
	    }
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		int xCoordinate=0;
		int yCoordinate=0;
		if(e.getSource()==button)
		{
			String vectorOfVelocity = "";
			String vectorOfPosistion="";
//			double number = Math.cos(Math.toRadians(angle))*powerLevel.getValue();
			vectorOfPosistion+=(int)((5*Math.cos(Math.toRadians(MyPanel.angle)))*powerLevel.getValue());
			vectorOfPosistion+="t+";
			vectorOfPosistion+=(int)((3*Math.cos(Math.toRadians(MyPanel.angle)))*powerLevel.getValue());
			vectorOfPosistion+=":";
			vectorOfPosistion+="&";
			vectorOfPosistion+=(int)((-5*Math.sin(Math.toRadians(MyPanel.angle)))*powerLevel.getValue());
			vectorOfPosistion+="t+";
			vectorOfPosistion+=(int)((-3*Math.sin(Math.toRadians(MyPanel.angle)))*powerLevel.getValue());
			vectorOfPosistion+=":";
			vectorOfVelocity+="0t+";
			vectorOfVelocity+=(int)((5*Math.cos(Math.toRadians(MyPanel.angle)))*powerLevel.getValue());
			vectorOfVelocity+=":&";
			vectorOfVelocity+="0t+";
			vectorOfVelocity+=(int)((5*Math.sin(Math.toRadians(MyPanel.angle)))*powerLevel.getValue());
			vectorOfVelocity+=':';
			
			if(vectorOfPosistion.length()==0)
			{
				
			}
			else
			{
				GameMain.gameLevel.physicsListLevel[GameMain.gameLevel.physicsListLevel.length-1].getCollided(GameMain.generateVector(vectorOfPosistion),GameMain.generateVector(vectorOfVelocity));
				System.out.println(GameMain.gameLevel.physicsListLevel[GameMain.gameLevel.physicsListLevel.length-1].objectPosistion);
			}
			buttonPushed=true;
		}
		
//		else if(GameMain.gameLevel.physicsListLevel[0].objectPosistion.evaluateVectorX(0)!=0||GameMain.gameLevel.physicsListLevel[0].objectPosistion.evaluateVectorY(0)!=0)
		else if(buttonPushed)
		{
			GameMain.gameLevel.makeCoordinateLevelPlane();
			for(int i = 0; i < GameMain.gameLevel.physicsListLevel.length;i++)
			{
				for(int j = 0; j < GameMain.gameLevel.physicsListLevel[i].objectSize;j++ )
				{
					for(int k = 0; k < GameMain.gameLevel.physicsListLevel[i].objectSize;k++)
					{

						if((GameMain.gameLevel.physicsListLevel[i].xCoordinate+GameMain.gameLevel.physicsListLevel[i].objectSize>=GameMain.gameLevel.levelSizeX||GameMain.gameLevel.physicsListLevel[i].xCoordinate<=0)&&!GameMain.gameLevel.physicsListLevel[i].hitObjectXAxis)
						{
							System.out.println(GameMain.gameLevel.physicsListLevel[i]);
							GameMain.gameLevel.physicsListLevel[i].newCollisionTime=time;
							GameMain.gameLevel.physicsListLevel[i].changeBooleanTrue();
							GameMain.gameLevel.physicsListLevel[i].hitObjectXAxis=true;
							
						}
						else if((GameMain.gameLevel.physicsListLevel[i].yCoordinate+GameMain.gameLevel.physicsListLevel[i].objectSize>=GameMain.gameLevel.levelSizeY||GameMain.gameLevel.physicsListLevel[i].yCoordinate<=0)&&!GameMain.gameLevel.physicsListLevel[i].hitObjectYAxis)
						{
							System.out.println(GameMain.gameLevel.physicsListLevel[i]);
							GameMain.gameLevel.physicsListLevel[i].newCollisionTime=time;
							GameMain.gameLevel.physicsListLevel[i].changeBooleanTrue();
							GameMain.gameLevel.physicsListLevel[i].hitObjectYAxis=true;
						}
						else if(GameMain.gameLevel.physicsListLevel[i].xCoordinate+GameMain.gameLevel.physicsListLevel[i].objectSize>=GameMain.gameLevel.levelSizeX||GameMain.gameLevel.physicsListLevel[i].xCoordinate<=0)
						{
							
						}
						else if(GameMain.gameLevel.physicsListLevel[i].yCoordinate+GameMain.gameLevel.physicsListLevel[i].objectSize>=GameMain.gameLevel.levelSizeY||GameMain.gameLevel.physicsListLevel[i].yCoordinate<=0)
						{
							
						}
						else if(GameMain.gameLevel.coordinateLevelPlane[GameMain.gameLevel.physicsListLevel[i].yCoordinate+j][GameMain.gameLevel.physicsListLevel[i].xCoordinate+k].occupyingPhysicsObject==null&&!GameMain.gameLevel.coordinateLevelPlane[GameMain.gameLevel.physicsListLevel[i].yCoordinate+j][GameMain.gameLevel.physicsListLevel[i].xCoordinate+k].accessed)
						{
							GameMain.gameLevel.coordinateLevelPlane[GameMain.gameLevel.physicsListLevel[i].yCoordinate+j][GameMain.gameLevel.physicsListLevel[i].xCoordinate+k].occupyingPhysicsObject=GameMain.gameLevel.physicsListLevel[i];
							GameMain.gameLevel.coordinateLevelPlane[GameMain.gameLevel.physicsListLevel[i].yCoordinate+j][GameMain.gameLevel.physicsListLevel[i].xCoordinate+k].accessed=true;
						}
						else
						{
							
							GameMain.gameLevel.coordinateLevelPlane[GameMain.gameLevel.physicsListLevel[i].yCoordinate+j][GameMain.gameLevel.physicsListLevel[i].xCoordinate+k].occupyingPhysicsObject.improvedCollision(GameMain.gameLevel.physicsListLevel[i]);
							return;
						}
					}
				}
			}
//			PhysicsObject[] replacmentPhysicsList = new PhysicsObject[GameMain.gameLevel.physicsListLevel.length-1];
//			int k = 0;
//			for(int i = 0; i < GameMain.gameLevel.physicsListLevel.length;i++)
//			{
//				if(GameMain.gameLevel.physicsListLevel[i].slatedToBeDestroyed&&time-GameMain.gameLevel.physicsListLevel[i].newCollisionTime>=1.5)
//				{
//					PhysicsObject removedObject = GameMain.gameLevel.physicsListLevel[i];
//					System.out.println(removedObject);
//					for(int j = 0; j < GameMain.gameLevel.physicsListLevel.length;j++)
//					{
//						if(GameMain.gameLevel.physicsListLevel[j]==removedObject)
//						{
//							
//						}
//						else
//						{
//							replacmentPhysicsList[k]=GameMain.gameLevel.physicsListLevel[j];
//							k++;
//						}
//					}
//					GameMain.gameLevel.physicsListLevel=replacmentPhysicsList;
//				}
//			}
			
			for(int i = 0; i < GameMain.gameLevel.physicsListLevel.length;i++)
			{
				if(GameMain.gameLevel.physicsListLevel[i] instanceof Fowl)
				{
					GameMain.gameLevel.physicsListLevel[i].Move(time);
				}
			}
			time+=.033;
			repaint();
		}
//			for(int i = 0; i < GameMain.gameLevel.physicsListLevel.length;i++)
//			{
//					xCoordinate = GameMain.gameLevel.physicsListLevel[i].xCoordinate;
//					yCoordinate = GameMain.gameLevel.physicsListLevel[i].yCoordinate;
//				System.out.println(GameMain.gameLevel.physicsListLevel.length);
//				for(int j = 0; j<GameMain.gameLevel.physicsListLevel[i].objectSize;j++)
//				{
//					for(int k = 0; k < GameMain.gameLevel.physicsListLevel[i].objectSize;k++)
//					{
//						if((j+k)==0)
//						{
//							
//						}
//						else if(GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k] instanceof Block||GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k] instanceof Pig)
//						{
//							
//							((Block) GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k]).damageTheBlock(GameMain.gameLevel.physicsListLevel[i].objectVelocity,time);
//							((Block) GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k]).gotHit=true;
//							if(((Block) GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k]).returnBlocksBreakingJoule()<=0)
//							{
//								hashtable.remove(GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k]);
//								PhysicsObject[] newPhysicsObjectList = new PhysicsObject[GameMain.gameLevel.physicsListLevel.length-1];
//								int z = 0;
//								for(int y = 0; y < GameMain.gameLevel.physicsListLevel.length;y++)
//								{
//									if(GameMain.gameLevel.physicsListLevel[y]==GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k])
//									{
//										
//									}
//									else
//									{
//										newPhysicsObjectList[z]=GameMain.gameLevel.physicsListLevel[y];
//										z++;
//									}
//								}
//								GameMain.gameLevel.physicsListLevel=newPhysicsObjectList;
//							}
//							else
//							{
//								GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k].getCollided(GameMain.gameLevel.physicsListLevel[i].objectPosistion, GameMain.gameLevel.physicsListLevel[i].objectVelocity);
//							}
//							System.out.println(i+"************");
//							if(i==GameMain.gameLevel.physicsListLevel.length)
//							{
//								i--;
//							}
//							GameMain.gameLevel.physicsListLevel[i].xCoordinate=GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k].xCoordinate-GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k].objectSize;
//							GameMain.gameLevel.physicsListLevel[i].yCoordinate=GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k].yCoordinate-GameMain.gameLevel.physicsLevelPlane[yCoordinate+j][xCoordinate+k].objectSize;
//							
//						}
//					}
//				}
//			}
//		
//			
//			for(int i = 0; i < GameMain.gameLevel.physicsListLevel.length;i++)
//			{
//				if( GameMain.gameLevel.physicsListLevel[i].gotHit|| GameMain.gameLevel.physicsListLevel[i] instanceof Fowl)
//				{
//					if(GameMain.gameLevel.physicsListLevel[i].objectPosistion.evaluateVectorY(time-GameMain.gameLevel.physicsListLevel[i].newCollisionTime)>=0&&GameMain.gameLevel.physicsListLevel[i].objectSize+GameMain.gameLevel.physicsListLevel[i].yCoordinate>=PANEL_WIDTH)
//					{
//						GameMain.gameLevel.physicsListLevel[i].yCoordinate+=1;
//					}
//					GameMain.gameLevel.physicsListLevel[i].Move(time-GameMain.gameLevel.physicsListLevel[i].newCollisionTime);
//					
//					
//					if((GameMain.gameLevel.physicsListLevel[i].xCoordinate+hashtable.get(GameMain.gameLevel.physicsListLevel[i]).getWidth(null))>=PANEL_WIDTH)
//					{
//						GameMain.gameLevel.physicsListLevel[i].xCoordinate=PANEL_WIDTH-hashtable.get(GameMain.gameLevel.physicsListLevel[i]).getWidth(null);
//						GameMain.gameLevel.physicsListLevel[i].newCollisionTime=time;
//						GameMain.gameLevel.physicsListLevel[i].changeOrientaionX();
//					}
//					if((GameMain.gameLevel.physicsListLevel[i].xCoordinate)<=0)
//					{
//						GameMain.gameLevel.physicsListLevel[i].xCoordinate=0;
//						GameMain.gameLevel.physicsListLevel[i].newCollisionTime=time;
//						GameMain.gameLevel.physicsListLevel[i].changeOrientaionX();
//					}
//					if((GameMain.gameLevel.physicsListLevel[i].yCoordinate+hashtable.get(GameMain.gameLevel.physicsListLevel[i]).getHeight(null))>=PANEL_HEIGHT)
//					{
//						GameMain.gameLevel.physicsListLevel[i].yCoordinate=(PANEL_HEIGHT-(GameMain.gameLevel.physicsListLevel[i].yCoordinate+hashtable.get(GameMain.gameLevel.physicsListLevel[i]).getHeight(null))%800-hashtable.get(GameMain.gameLevel.physicsListLevel[i]).getHeight(null));
//						GameMain.gameLevel.physicsListLevel[i].newCollisionTime=time;
//						GameMain.gameLevel.physicsListLevel[i].changeOrientaionYTrue();
//						
//					}
//					if((GameMain.gameLevel.physicsListLevel[i].yCoordinate)<=0)
//					{
//						GameMain.gameLevel.physicsListLevel[i].yCoordinate=0;
//						GameMain.gameLevel.physicsListLevel[i].newCollisionTime=time;
//					}
//				}
//					else
//					{
//						
//					}
//						
//			}
//				
//			time+=.033;
//			repaint();
//		}
//		
		
	}
}