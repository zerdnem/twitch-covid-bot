//*************************************************************************
// Preferences.java
// Author: Ashton Honeggar
// 
// 
//*************************************************************************
package bot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Preferences implements Serializable {
	private static String streamer_name;
	private static String streamer_channel;
	private static String bot_name;
	private static String oauth;
	private static Map<String, Boolean> command_preferences;
	
	public String s_name, s_channel, b_name, b_oauth;
	public Map<String, Boolean> c_prefs;
	
	public static boolean loadPreferences() {
		if (streamer_name != null &&
				streamer_channel != null &&
				bot_name != null &&
				oauth != null &&
				command_preferences != null)
			return false;

		Preferences temp = new Preferences();
		
		File prefDat = new File("preferences.dat");
		if (!prefDat.isFile())
		{
			try {
				prefDat.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			while(!openFirstRunDialog());
			if (bot_name == null)
				bot_name = "";
			
			if (oauth == null)
				oauth = "";
			
			if (command_preferences == null)
				setDefaultCommands();
			savePreferences();
			return false;
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(prefDat));
			
			temp = (Preferences) ois.readObject();
			ois.close();
			
			streamer_name = temp.s_name;
			streamer_channel = temp.s_channel;
			bot_name = temp.b_name;
			oauth = temp.b_oauth;
			command_preferences = temp.c_prefs;
		} catch (Exception e) {
			if (bot_name == null)
				bot_name = "";
			
			if (oauth == null)
				oauth = "";
			
			if (command_preferences == null)
				setDefaultCommands();
			
			while(!openFirstRunDialog());
			
			savePreferences();
			return false;
		}
		
		if (streamer_name == null || streamer_channel == null || 
				bot_name == null || oauth == null)
			while(!openFirstRunDialog());
		
		if (command_preferences == null || command_preferences.size() == 0)
			setDefaultCommands();
		
		return true;
	}
	
	private static void setDefaultCommands() {
		HashMap<String, Boolean> temp = new HashMap<String, Boolean>();
		temp.put("!corona", true);
		temp.put("hello", false);
		temp.put("^", false);
		temp.put("!commands", false);
		temp.put("!startvote", false);
		temp.put("!veto", false);
		temp.put("!results", false);
		temp.put("!elo", false);
		temp.put("!seppuku", false);
		temp.put("!uptime", false);
		temp.put("!bonus", false);
		temp.put("!ks", false);
		temp.put("!gamble", false);
		temp.put("!russian", false);
		temp.put("!coins", false);
		temp.put("!mods", false);
		temp.put("!checkcoins", false);
		temp.put("!addcom", false);
		temp.put("!version", true);
		temp.put("custom_commands", true);
		
		command_preferences = temp;
	}

	public static boolean savePreferences() {
		File prefDat = new File("preferences.dat");
		try {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(prefDat));
		
		Preferences temp = new Preferences();
		temp.s_name = streamer_name;
		temp.s_channel = streamer_channel;
		temp.b_name = bot_name;
		temp.b_oauth = oauth;
		temp.c_prefs = command_preferences;
		
		oos.writeObject(temp);
		oos.close();
		return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Map<String, Boolean> getCommandPreferencesMap() {
		return command_preferences;
	}
	
	public static boolean getCommandPreference(String command) {
		return command_preferences.getOrDefault(command, false);
	}
	
	public static String getStreamerName() {
		return streamer_name;
	}
	
	public static String getStreamerChannel() {
		return streamer_channel;
	}
	
	public static String getBotName() {
		return bot_name;
	}
	
	public static String getBotAuth() {
		return oauth;
	}
	
	public static void setStreamerName(String name) {
		streamer_name = name;
	}
	
	public static void setStreamerChannel(String channel) {
		streamer_channel = channel;
	}
	
	public static void setBotName(String name) {
		bot_name = name;
	}
	
	public static void setBotAuth(String auth) {
		oauth = auth;
	}
	
	public static boolean changePreference(String command, boolean preference) {
		if (!command_preferences.containsKey(command))
			return false;
		
		command_preferences.replace(command, preference);
		return true;
	}
	
	public static boolean addCommand(String command, boolean preference) {
		if (command_preferences.containsKey(command))
			return false;
		
		command_preferences.put(command, preference);
		return true;
	}
	
	public static boolean removeCommand(String command) {
		if (!command_preferences.containsKey(command))
			return false;
		
		command_preferences.remove(command);
		return true;
	}
	
	private static boolean openFirstRunDialog() {
		JTextField s_name = new JTextField();
		String str_sName = "<html><B><U>REQUIRED:</U></B> Enter streamer's name here.</html>";
		
		JTextField s_channel = new JTextField();
		String str_sChannel = "<html><B><U>REQUIRED:</U></B> Enter streamer's channel (twitch name) here.</html>";
		
		JTextField b_name = new JTextField();
		String str_bName = "Enter the login for the account you wish to be a bot. (Defaults to SaltMod).";
		
		JTextField b_oauth = new JTextField();
		String str_bOauth = "Enter the OAuth code for the account that you entered above.";
		
		Object[] preferences = { str_sName, s_name,
				str_sChannel, s_channel, str_bName, b_name,
				str_bOauth, b_oauth
		};
		
		JOptionPane.showMessageDialog(null, preferences, "First Run - Preferences", JOptionPane.PLAIN_MESSAGE);
		
		if (s_name.getText() == null || s_name.getText().length() == 0)
			return false;
				
		if (s_channel.getText() == null || s_name.getText().length() == 0)
			return false;
		
		streamer_name = s_name.getText();
		streamer_channel = s_channel.getText();
		
		if (b_name.getText() == null || b_name.getText().length() == 0) {
			bot_name = "";
			oauth = "";
		}
		else if (b_oauth.getText() == null || b_oauth.getText().length() == 0)
			return false;
		else {
			bot_name = b_name.getText();
			oauth = b_oauth.getText();
		}
		
		savePreferences();
		return true;
	}
}
