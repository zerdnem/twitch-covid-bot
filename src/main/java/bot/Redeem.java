//*************************************************************************
// Redeem.java
// Author: Ashton Honeggar
// 
// 
//*************************************************************************
package bot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.pircbotx.hooks.events.MessageEvent;

public class Redeem {
	static ArrayList<Player> list = new ArrayList<Player>();
	static int participants;
	
	//Russian Roulette
	public static String RouletteAction(Player user) throws IOException {
		if (user.coins < 15)
			return user.nick + ": Please wait until you have at least 15 coins to join RR.";
		
		if (IsPlayer(user))
			return "/me " + user.nick + " has been removed from RR. There are now " + RemoveRoulette(user) +
					"/6 players left in RR. To join, type '!RR'.";
		
		else if (list.size() < 2)
			return "/me " + user.nick + " has been added to RR. There are now " + AddRoulette(user) +
					"/6 players needed to start RR. To join, type '!RR'.";
		
		else if (list.size() < 5)
			return "/me " + user.nick + " has been added to RR. There are now " + AddRoulette(user) +
					"/6 players needed to start RR. The pot is now up to " + GetRoulettePot() +
					" coins. To join, type '!RR'.";
		else {
			AddRoulette(user);
			return RouletteFinish();
		}
	}
	
	private static String RouletteFinish() throws IOException {
		Random rand = new Random();
		Player loser = list.get(rand.nextInt(6));
		int pot = GetRoulettePot();
		int winnings = loser.coins;
		FileRead.putCoins(loser.nick, winnings * -1);
		int amount = 0;
		for (Player p : list){
			if (!p.nick.equals(loser.nick))
				amount = (int) (((double) p.coins * winnings) / pot);
				FileRead.putCoins(p.nick, amount);
		}
		list.clear();
		
		return "/me " + loser.nick + " was shot and killed! " + winnings + " coins were distributed "
				+ "among the survivors.";
	}

	private static int GetRoulettePot() {
		int pot = 0;
		for (Player p : list)
			pot += p.coins;
		return pot;
	}

	public static int AddRoulette(Player user) {
		list.add(user);
		participants = list.size();
		return participants;
	}
	
	public static int RemoveRoulette(Player user) {
		int index = -1;
		for (Player p : list)
			if (p.nick.equalsIgnoreCase(user.nick))
				index = list.indexOf(p);
		list.remove(index);
		participants = list.size();
		return participants;
	}
	
	public static int GetRouletteNumber() {
		return participants;
	}
	
	public static ArrayList<Player> GetRoulettePlayers() {
		return list;
	}
	
	public static boolean IsPlayer(Player user) {
		for (Player p : list)
			if (p.nick.equals(user.nick))
				return true;
		return false;
	}
	
	//Redeem
	public static String RedeemAction(MessageEvent event, String message) throws IOException {
		
		return "";
	}
	
}
