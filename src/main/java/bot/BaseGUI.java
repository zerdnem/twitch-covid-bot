package bot;
//*************************************************************************
// BaseGUI.java
// Author: Ashton Honeggar
// 
// 
//*************************************************************************

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class BaseGUI extends JFrame {
	private JTabbedPane panel;
	
	public BaseGUI() {
		super("TwitchBot");
		setPreferredSize(new Dimension(900, 600));
		panel = new JTabbedPane();
		Preferences.loadPreferences();
		panel.addTab("Control", new ControlPanel());
		panel.addTab("Commands", new CommandsPanel());
//		panel.addTab("Options", new OptionsPanel());
		panel.addTab("Login", new LoginPanel());
		
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
