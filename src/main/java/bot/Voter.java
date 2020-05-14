//*************************************************************************
// 
// Author: Ashton Honeggar
// 
//
//*************************************************************************
package bot;

import java.util.ArrayList;

public class Voter {
	
	static ArrayList<Vote> voteList = new ArrayList<Vote>();
	
	public static void godVote(String nick, String god)
	{
		if (!Vote.voteActive)
			return;
		
		boolean exists = false;
		
		for (Vote v : voteList)
			if (v.getNick().equals(nick))
			{
				exists = true;
				v.setGod(god);
			}
		
		if (!exists)
			voteList.add(new Vote(nick, god, "god"));	
	}
	
	public static void modeVote(String nick, String mode)
	{
		if (!Vote.voteActive)
			return;
		
		boolean exists = false;
		
		for (Vote v: voteList)
			if (v.getNick().equals(nick))
			{
				exists = true;
				v.setMode(mode);
			}
		if (!exists)
			voteList.add(new Vote(nick, mode, "mode"));
	}
	
	public static void roleVote(String nick, String role)
	{
		if (!Vote.voteActive)
			return;
		
		boolean exists = false;
		
		for (Vote v: voteList)
			if (v.getNick().equals(nick))
			{
				exists = true;
				v.setRole(role);
			}
		if (!exists)
			voteList.add(new Vote(nick, role, "role"));
	}

	public static String getResults()
	{
		String str = "";
		int gods[] = new int[83];
		int modes[] = new int[7];
		int roles[] = new int[5];
		int highest = 0, index = -1;
		
		for (Vote v: voteList)
		{
			if (v.getGod() >= 0)
				gods[v.godChoice]++;
			if (v.getMode() >= 0)
				modes[v.modeChoice]++;
			if (v.getRole() >= 0)
				roles[v.roleChoice]++;
		}
		
		for (int i = 0; i < 83; i++)
		{
			if (gods[i] > highest)
			{
				highest = gods[i];
				index = i;
			}
		}
		
		if (highest != 0)
			str = str.concat(" The highest voted god was: " + Vote.findGod(index) + ".");
		
		highest = 0;
		index = -1;
		
		for (int i = 0; i < 7; i++)
		{
			if (modes[i] > highest)
			{
				highest = modes[i];
				index = i;
			}
		}
		
		if (highest != 0)
			str = str.concat(" The highest voted mode was: " + Vote.findMode(index) + ".");
		
		highest = 0;
		index = -1;
		
		for (int i = 0; i < 5; i++)
		{
			if (roles[i] > highest)
			{
				highest = roles[i];
				index = i;
			}
		}
		
		if (highest != 0)
			str = str.concat(" The highest voted role was: " + Vote.findRole(index) + ".");
		
		return str;
	}

	public static void resetVote()
	{
			voteList.clear();
	}
}
