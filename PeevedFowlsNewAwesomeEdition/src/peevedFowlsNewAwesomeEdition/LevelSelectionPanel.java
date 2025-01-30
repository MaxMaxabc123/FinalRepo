package peevedFowlsNewAwesomeEdition;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LevelSelectionPanel extends JPanel implements ActionListener
{
	
	JTextField levelSubmissionField = new JTextField(50);
	JLabel explainationText = new JLabel("Submit the text of the level");
	JButton submissionButton = new JButton("click me to submit :)");
	JButton quitButton = new JButton("click me to quit");
	boolean fileNotFound=false;
	Scanner scanner = null;
	static String levelText;
	static Level l;
	LevelSelectionPanel()
	{
		submissionButton.addActionListener(this);
		quitButton.addActionListener(this);
		this.setPreferredSize(new Dimension(600,600));
		this.add(levelSubmissionField);
		this.add(explainationText);
		this.add(submissionButton);
		this.add(quitButton);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==quitButton)
		{
			System.exit(ABORT);
		}
		if(e.getSource()==submissionButton)
		{
			try(FileReader reader = new FileReader(levelSubmissionField.getText()))
			{
				scanner = new Scanner(reader);
				levelText = scanner.nextLine();
				System.out.println(levelText);
				levelSubmissionField.setText("");
				l = new Level(600,600,Level.makePhysicsObjectList(levelText),20);
				new GameFrame();
			}
			catch(Exception exp)
			{
				System.out.println("file not found try again");
				fileNotFound=true;
			}
			finally
			{
				if(scanner!=null)
				{
					scanner.close();
				}
			}
		}
	}
	
	
}
