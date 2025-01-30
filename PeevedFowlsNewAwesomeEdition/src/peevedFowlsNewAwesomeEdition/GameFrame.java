package peevedFowlsNewAwesomeEdition;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class GameFrame extends JFrame implements ActionListener
{
	Timer timer;
	LevelPanel newPanel;
	JPanel wormCounter;
	JPanel timeCounter;
	static JLabel JLabel;
	static JLabel timerLabel;
	int wormCount;
	static int timeAmount;
	static int timerDiff=0;
	GameFrame()
	{
		wormCount= LevelPanel.WormCount();
		timeAmount = (int) LevelSelectionPanel.l.levelTime;
		JLabel = new JLabel();
		JLabel.setText(String.valueOf(wormCount)+" worms left in the level");
		timerLabel = new JLabel();
		timerLabel.setText(String.valueOf(timeAmount)+" Seconds left");
		wormCounter = new JPanel();
		wormCounter.add(JLabel);
		timeCounter= new JPanel();
		timeCounter.add(timerLabel);
		newPanel = new LevelPanel();
		this.setLayout(new BorderLayout());
		this.add(newPanel,BorderLayout.CENTER);
		this.add(wormCounter,BorderLayout.EAST);
		this.add(timeCounter,BorderLayout.WEST);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(newPanel);
		timer = new Timer(1000,this);
		timer.start();
	}
	public static void UpdateUi()
	{
		JLabel.setText(String.valueOf(LevelPanel.WormCount())+" worms left in the level");
	}
	public static void UpdateTimer()
	{
		timerLabel.setText(String.valueOf(timeAmount-timerDiff)+" Seconds left");
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		timerDiff++;
		UpdateTimer();
		if(LevelPanel.BirdAlone())
		{
			this.remove(newPanel);
			timer.stop();
			this.dispose();
			Testing.currentlyRunningGame=false;
			new EndGameFrame();
			timerDiff=0;
		}
		if(timeAmount-timerDiff==0)
		{
			this.remove(newPanel);
			timer.stop();
			this.dispose();
			Testing.currentlyRunningGame=false;
			new EndGameFrame();
			timerDiff=0;
		}
		else
		{
			
		}
		
	}
	
}
