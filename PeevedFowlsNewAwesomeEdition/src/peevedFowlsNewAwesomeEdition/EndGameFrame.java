package peevedFowlsNewAwesomeEdition;

import javax.swing.JFrame;

public class EndGameFrame extends JFrame
{
	EndGamePanel panel;
	EndGameFrame()
	{
		panel = new EndGamePanel();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
