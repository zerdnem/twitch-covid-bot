package bot;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

//*************************************************************************
// CommandsPanel.java
// Author: Ashton Honeggar
// 
// 
//*************************************************************************

public class CommandsPanel extends JPanel {
	private JPanel panel, userCommands;
	private JScrollPane scroll, sp;
	private JSplitPane split;
	private JButton b1, b2, b3;
	private CommandListener listener = new CommandListener();
	private ButtonListener buttonListener = new ButtonListener();
	private Dimension minSize = new Dimension(100,100);
	
		public CommandsPanel() {			
			panel = new JPanel();
			panel.setLayout(new GridLayout(0,1));
			
			userCommands = new JPanel();
			userCommands.setLayout(new GridLayout(0,1));

			loadCommands();

			scroll = new JScrollPane(panel);
			Dimension d = new Dimension(panel.getComponent(0).getPreferredSize());
			scroll.getVerticalScrollBar().setUnitIncrement(d.height);
			setLayout(new BorderLayout());
			
			sp = new JScrollPane(userCommands);
			sp.getVerticalScrollBar().setUnitIncrement(d.height);
			

			JPanel south = new JPanel();

			b1 = new JButton("Create Command");
			b1.addActionListener(buttonListener);
			south.add(b1);
			b2 = new JButton("Edit Command");
			b2.addActionListener(buttonListener);
			south.add(b2);
			b3 = new JButton("Delete Command");
			b3.addActionListener(buttonListener);
			south.add(b3);
			
			JPanel container = new JPanel();
			container.setLayout(new BorderLayout());
			
			container.add(south, BorderLayout.SOUTH);
			container.add(sp, BorderLayout.CENTER);
			
			split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scroll, container);
			
			add(split, BorderLayout.CENTER);

//			sp = new JScrollPane(south);
//			
//			add(sp, BorderLayout.SOUTH);

			setVisible(true);
		}

		private void loadCommands() {
			panel.removeAll();
			userCommands.removeAll();
			
			for (Object s : Preferences.getCommandPreferencesMap().keySet().toArray()){
				String str = (String)s;
				JCheckBox temp = new JCheckBox(str, Preferences.getCommandPreference(str));
				temp.addActionListener(listener);
				panel.add(temp);
			}
			
			for (String[] s : UserCommands.getCommands()) {
				JCheckBox temp = new JCheckBox(s[0], Preferences.getCommandPreference(s[0]));
				temp.addActionListener(listener);
				temp.setToolTipText(s[1]);
				userCommands.add(temp);
			}
		}
		
		private class CommandListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent event) {
				JCheckBox source = (JCheckBox)event.getSource();
				Preferences.changePreference(source.getText(), source.isSelected());
				Preferences.savePreferences();
			}
		}
		
		private class ButtonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent event) {
				Object source = event.getSource();
				
				if (source == b1) {
					String command_str = "<html>Enter the keyword/name of the command: </html>";
					JTextField command_tf = new JTextField();
					
					String function_str = "<html>Enter the text to be printed from this command: </html>";
					JTextField function_tf = new JTextField();
					
					Object[] prompt = { command_str, command_tf, function_str, function_tf };
					
					JOptionPane.showMessageDialog(null, prompt, "Add Command", JOptionPane.PLAIN_MESSAGE);
					
					if (UserCommands.commandExists(command_tf.getText())) {
						JOptionPane.showConfirmDialog(null, "Command already exists. "
								+ "To edit it, use the edit function.");
					} else if (command_tf.getText().length() > 0 && function_tf.getText().length() > 0) {
						UserCommands.addCommand(command_tf.getText(), function_tf.getText());
						loadCommands();
						revalidate();
						repaint();
					}
				}
				
				if (source == b2) {
					ArrayList<String[]> userCommands = UserCommands.getCommands();
					if (userCommands.size() > 0) {
						String[] list = new String[userCommands.size()];
						JFrame editFrame = new JFrame("Edit Commands");
						JSplitPane split;
						for (int i = 0; i < userCommands.size(); ++i)
							list[i] = (userCommands.get(i))[0];
						JList listPane = new JList(list);
					} else {
						JOptionPane.showMessageDialog(null, "There are no user commands!", "Edit Commands", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
}
