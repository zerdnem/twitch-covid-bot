//*************************************************************************
// UserCommands.java
// Author: Ashton Honeggar
// 
// 
//*************************************************************************
package bot;

import java.io.IOException;
import java.util.ArrayList;

public class UserCommands {


	public static ArrayList<String[]> getCommands() {
		try {
			return FileRead.getCommands();
		} catch (Exception e) {
			return null;
		}
	}

	public static void putCommands(ArrayList<String[]> commands) {
		try {
			FileRead.putCommands(commands);
		} catch (Exception e) {
			return;
		}
	}

	public static String executeCommand(String phrase) throws IOException {
		for (String[] s : getCommands())
			if (s[0].equalsIgnoreCase(phrase))
				return s[1];
		return null;
	}

	public static boolean commandExists(String phrase) {
		try {
			for (String[] s : getCommands())
				if (s[0].equalsIgnoreCase(phrase))
					return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static void addCommand(String phrase, String message) {
		try {
			ArrayList<String[]> commands = getCommands();
			String[] command = {phrase, message};
			if (!commandExists(phrase))
				commands.add(command);
			else
				for (String[] s : commands)
					if (s[0].equalsIgnoreCase(command[0]))
						s[1] = command[1];
			putCommands(commands);
		} catch (Exception e) {
			return;
		}
	}

	public static void delCommand(String phrase) {
		try {
			ArrayList<String[]> commands = getCommands();
			int index = -1;
			if (!commandExists(phrase))
				return;
			for (String[] s : commands)
				if (s[0].equalsIgnoreCase(phrase))
					index = commands.indexOf(s);
			if (index != -1)
				commands.remove(index);
			putCommands(commands);
		} catch (Exception e) {
			return;
		}
	}
}
