//*************************************************************************
// 
// Author: Ashton Honeggar
// 
//
//*************************************************************************
package bot;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class FileRead {

	public static ArrayList<Player> getPlayers() throws IOException {
		File playFile = new File("players.txt");
		if (!playFile.isFile())
			playFile.createNewFile();
		
		BufferedReader br = new BufferedReader(new FileReader("players.txt"));
		ArrayList<Player> players = new ArrayList<Player>();
		String line;
		String nick;
		int coins;
				
		while ((line = br.readLine()) != null && line.contains("="))
		{
			nick = line.split("=")[0];
			coins = Integer.parseInt(line.split("=")[1]);
			players.add(new Player(nick, coins));
		}
		br.close();
		return players;
	}
	
	public static void putPlayers(ArrayList<Player> players) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("players.txt"));
		StringBuilder sb = new StringBuilder();
		for (Player p : players)
			sb.append(p.nick + "=" + p.coins + "\n");
		bw.write(sb.toString());
		bw.close();
	}

	public static Player getPlayerInstance(String nick) throws IOException {
		ArrayList<Player> list = getPlayers();
		for (Player p : list)
			if (nick.equalsIgnoreCase(p.nick)){
				return p;
			}
		return new Player(nick, -1);
	}

	public static int getVariable(String varName) throws IOException{
		
		String line, temp;
		BufferedReader br = new BufferedReader(new FileReader("storage.txt"));
		
		while ((line = br.readLine()) != null && !line.contains(varName));
		br.close();

		if (!line.contains(varName))
			return -1;
		else
			temp = line.split("=")[1];
		
		return Integer.parseInt(temp);
	}

	public static int putVariable(String varName, int amount) throws IOException {

		int value = -1;
		String line, temp;
		ArrayList<String> file = new ArrayList<String>();
		
		File storage = new File("storage.txt");
		if (!storage.isFile())
			storage.createNewFile();
		
		BufferedReader br = new BufferedReader(new FileReader("storage.txt"));

		while ((line = br.readLine()) != null && line.contains("="))
			file.add(line);
		br.close();
		
		boolean found = false;
		
		for (String s : file)
			if (s.contains(varName + "="))
			{
				temp = s.split("=")[1];
				value = Integer.parseInt(temp);
				value += amount;
				temp = varName + "=" + value;
				file.set(file.indexOf(s), temp);
				found = true;
			}
		
		if (!found)
		{
			temp = varName + "=" + amount;
			file.add(temp);
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("storage.txt"));
		temp = "";
		
		for (String s : file)
			temp = temp.concat(s + "\n");
		
		bw.write(temp);
		bw.close();
		
		return value;
	}

	public static int getCoins(String nick) throws IOException {
		ArrayList<Player> players = getPlayers();
		players = FileRead.getPlayers();
		for (Player p : players)
		{
			if (p.nick.equals(nick))
			{
				return p.coins;
			}
		}
		return -1;
	}

	public static int putCoins(String nick, int amount) throws IOException {
		ArrayList<Player> players = getPlayers();
		for (Player p : players)
		{
			if (p.nick.equals(nick))
			{
				p.coins += amount;
				FileRead.putPlayers(players);
				return p.coins;
			}
		}
		players.add(new Player(nick, 5 + amount));
		FileRead.putPlayers(players);
		return 5 + amount;
	}

	public static ArrayList<String[]> getCommands() throws IOException {
		File comFile = new File("commands.txt");
		if (!comFile.isFile())
			comFile.createNewFile();
		
		BufferedReader br = new BufferedReader(new FileReader("commands.txt"));
		ArrayList<String[]> commands = new ArrayList<String[]>();
		String line;
		String phrase;
		String message;
		
		while ((line = br.readLine()) != null && line.contains("=")){
			String[] command = new String[2];
			phrase = line.split("=")[0];
			message = line.split("=")[1];
			command[0] = phrase;
			command[1] = message;
			commands.add(command);
		}
		br.close();
		return commands;
	}

	public static void putCommands(ArrayList<String[]> commands) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("commands.txt"));
		StringBuilder sb = new StringBuilder();
		for (String[] s : commands){
			sb.append(s[0]);
			sb.append('=');
			sb.append(s[1]);
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.close();
	}
}