package peevedFowlsNewAwesomeEdition;

import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndGamePanel extends JPanel
{
	JLabel label;
	
	EndGamePanel()
	{
		PrintWriter writer = null;
		this.setPreferredSize(new Dimension(300,300));
		if(GameFrame.timeAmount-GameFrame.timerDiff>0)
		{
			
			try
			{
				writer = new PrintWriter(new FileWriter(new File("levelScore.txt"),false));
				writer.write(String.valueOf((GameFrame.timeAmount-GameFrame.timerDiff)*100));
				label = new JLabel("you win"+" with a score of score :"+String.valueOf((GameFrame.timeAmount-GameFrame.timerDiff)*100));
				this.add(label);
			}
			catch(Exception e)
			{
				System.out.println("level score filed delted pls contact max");
			}
			finally
			{
				if(writer!=null)
				{
					writer.close();
				}
			}
		}
		else
		{
			label = new JLabel("you lose");
			this.add(label);
		}
	}
}
