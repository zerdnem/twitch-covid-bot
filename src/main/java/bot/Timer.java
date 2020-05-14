//*************************************************************************
// 
// Author: Ashton Honeggar
// 
//
//*************************************************************************
package bot;

import java.io.IOException;
import java.util.ArrayList;

public class Timer {
	static long startTime = System.currentTimeMillis();
	static long nextTime = (startTime / 300000) * 300000 + 300000;
	static ArrayList<Player> list = new ArrayList<Player>();

	public static void startTimer() throws IOException {
		while (true){
			while (nextTime > System.currentTimeMillis());
			nextTime += 300000;
			System.out.println("\nTimer.java | NOTICE: Timer reset.\n");
			if (UrlRead.getStream(Preferences.getStreamerChannel()).getLiveStatus())
			{
				try {
					checkViewers();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("ERROR:1");
				}
				try {
					FileRead.putPlayers(list);
				} catch (IOException e) {
					System.out.println("ERROR:2");
					e.printStackTrace();
				}
			}
		}
	}

	public static void checkViewers() throws IOException {
		boolean first = true;
		ArrayList<String> viewers = new ArrayList<String>();
		viewers = UrlRead.getViewers(Preferences.getStreamerChannel());
		list = FileRead.getPlayers();
		for (String s : viewers)
		{
			for (Player p : list)
			{
				if (s.equalsIgnoreCase(p.nick))
				{
					p.coins += 5;
					first = false;
				}
			}
			if (first == true)
			{
				list.add(new Player(s, 5));
			}
			first = true;
		}
	}

	public static int checkCoins(String nick) throws IOException {
		ArrayList<Player> players = new ArrayList<Player>();
		players = FileRead.getPlayers();
		for (Player p : players)
		{
			if (p.nick.equalsIgnoreCase(nick))
				return p.coins;
		}
		return 0;
	}
}
