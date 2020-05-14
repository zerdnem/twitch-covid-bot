package bot;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

//*************************************************************************
// StatusPanel.java
// Author: Ashton Honeggar
// 
// 
//*************************************************************************

public class ControlPanel extends JPanel {
	private String status;
	private JPanel panel;
	private JButton start, stop, restart;
	private JLabel label;
	private static JLabel south;
	
	public ControlPanel() {
		setLayout(new BorderLayout());
		status = "Service: Not Running...";
		
		panel = new JPanel();
		panel.setName("Status");
		label = new JLabel(status);
		panel.add(label);
		add(panel, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setName("Buttons");
//		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		start = new JButton("   Start   ");
		start.setBorder(BorderFactory.createRaisedBevelBorder());
		start.setEnabled(true);
		start.addActionListener(new ButtonListener());
		panel.add(start);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		stop = new JButton("   Stop   ");
		stop.setBorder(BorderFactory.createLoweredBevelBorder());
		stop.setEnabled(false);
		stop.addActionListener(new ButtonListener());
		panel.add(stop);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		restart = new JButton("   Restart   ");
		restart.setBorder(BorderFactory.createLoweredBevelBorder());
		restart.setEnabled(false);
		restart.addActionListener(new ButtonListener());
		panel.add(restart);
		
		add(panel, BorderLayout.CENTER);
		
		JPanel info = new JPanel();
		south = new JLabel("<html>Current channel: #" + Preferences.getStreamerChannel()
				+ "<br>Current bot: " + Preferences.getBotName() + "</html>");
		info.add(south);
		
		add(info, BorderLayout.SOUTH);
		
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			Border raised = BorderFactory.createRaisedBevelBorder();
			Border lowered = BorderFactory.createLoweredBevelBorder();
			
			if (source == start && !RunBot.isRunning())
			{
				new ThreadRunner("BotThread");
				
				start.setBorder(lowered);
				start.setEnabled(false);
				stop.setBorder(raised);
				stop.setEnabled(true);
				restart.setBorder(raised);
				restart.setEnabled(true);
				label.setText("Service: Running...");
			}
			else if (source == stop && RunBot.isRunning())
			{
				RunBot.stopBot();
				
				start.setBorder(raised);
				start.setEnabled(true);
				stop.setBorder(lowered);
				stop.setEnabled(false);
				restart.setBorder(lowered);
				restart.setEnabled(false);
				label.setText("Service: Not Running...");
			}
		}
	}

	public static void update() {
		south.setText("<html>Current channel: #" + Preferences.getStreamerChannel()
				+ "<br>Current bot: " + Preferences.getBotName() + "</html>");
	}
}
