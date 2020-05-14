package bot;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//*************************************************************************
// OptionsPanel.java
// Author: Ashton Honeggar
// 
// 
//*************************************************************************

public class LoginPanel extends JPanel {
	JScrollPane sp = new JScrollPane();
	JPanel panel = new JPanel();
	LoginListener listener = new LoginListener();
	
	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	JTextField tf4;

	public LoginPanel() {
		setLayout(new BorderLayout());
		panel.setLayout(new GridLayout(0,1));
		
		JLabel title = new JLabel("<html>Use this to change the login info of the bot. "
				+ "NOTE: If the OAuth and Username do not match an account, "
				+ "the program will fail without warning.</html>");
				
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		titlePanel.add(title, BorderLayout.CENTER);
		
		add(titlePanel, BorderLayout.NORTH);
		
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel row4 = new JPanel();

		tf1 = new JTextField(Preferences.getStreamerName(),20);
		tf2 = new JTextField(Preferences.getStreamerChannel(),20);
		tf3 = new JTextField(Preferences.getBotName(),20);
		tf4 = new JTextField("",20);

		JLabel label1 = new JLabel("Streamer name: ");
		JLabel label2 = new JLabel("Channel name: ");
		JLabel label3 = new JLabel("Bot name: ");
		JLabel label4 = new JLabel("OAuth code: ");
		
		row1.add(label1);
		row1.add(tf1);
		row2.add(label2);
		row2.add(tf2);
		row3.add(label3);
		row3.add(tf3);
		row4.add(label4);
		row4.add(tf4);
				
		panel.add(row1);
		panel.add(row2);
		panel.add(row3);
		panel.add(row4);
		
//		sp.add(panel);
		
		add(panel, BorderLayout.WEST);
		
		JPanel south = new JPanel();
		
		JButton ok = new JButton("OK");
		ok.addActionListener(listener);
		south.add(ok);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(listener);
		south.add(cancel);
		
		add(south, BorderLayout.SOUTH);
	}
	
	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			JButton source = (JButton)event.getSource();
			
			if (source.getText() == "Cancel") {
				tf1.setText(Preferences.getStreamerName());
				tf2.setText(Preferences.getStreamerChannel());
				tf3.setText(Preferences.getBotName());
				tf4.setText("");
			} else if (source.getText() == "OK") {
				if (tf1.getText() != null && tf1.getText().length() > 0)
					Preferences.setStreamerName(tf1.getText());
				else
					tf1.setText(Preferences.getStreamerName());
				
				if (tf2.getText() != null && tf2.getText().length() > 0) {
					Preferences.setStreamerChannel(tf2.getText());
					ControlPanel.update();
				}
				else
					tf2.setText(Preferences.getStreamerChannel());
				
				if (tf3.getText() != null && tf3.getText().length() > 0 &&
						tf4.getText() != null && tf4.getText().length() > 0) {
					Preferences.setBotName(tf3.getText());
					Preferences.setBotAuth(tf4.getText());
					ControlPanel.update();
				} else if (tf3.getText().length() == 0 && tf4.getText().length() == 0) {
					Preferences.setBotName("");
					Preferences.setBotAuth("");
					tf3.setText("");
				} else {
					tf3.setText(Preferences.getBotName());
					tf4.setText("");
				}
					
			}
			RunBot.updateBot();
			Preferences.savePreferences();
		}
	}
}
