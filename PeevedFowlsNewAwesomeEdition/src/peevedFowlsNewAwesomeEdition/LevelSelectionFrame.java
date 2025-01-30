package peevedFowlsNewAwesomeEdition;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelSelectionFrame extends JFrame
{
	LevelSelectionPanel panel;
	JPanel errorPanel;
	JLabel errorLabel;
	LevelSelectionFrame()
	{
		panel = new LevelSelectionPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
