//*************************************************************************
// 
// Author: Ashton Honeggar
// 
//
//*************************************************************************
package bot;
import java.util.ArrayList;

import org.pircbotx.hooks.events.MessageEvent;

public class GodVoter{

	static boolean voteActive;
	static ArrayList<String> godList = new ArrayList<String>();
	static ArrayList<String> modeList = new ArrayList<String>();
	static ArrayList<String> roleList = new ArrayList<String>();
	static int[] gods = new int[83];
	static int[] modes = new int[7];
	static int[] roles = new int[5];
	static String topGod;
	static String topRole;
	static String topMode;
	

	public static void startVote()
	{
		voteActive = true;
	}

	public static void endVote()
	{
		voteActive = false;
	}

	public static void resetVote()
	{
		for (int u = 0; u < gods.length; u++)
			gods[u] = 0;
		
		for (int u = 0; u < modes.length; u++)
			modes[u] = 0;
		
		for (int u = 0; u < roles.length; u++)
			roles[u] = 0;
		
		topGod = null;
		topRole = null;
		topMode = null;
	}
	
	public static void voteMode(String mode)
	{
		if (!voteActive)
			return;

		if ("conquest".equals(mode.toLowerCase()) || "conq".equals(mode.toLowerCase())) {
			modes[0]++;
		} else if ("joust".equals(mode.toLowerCase())) {
			modes[1]++;
		} else if ("motd".equals(mode.toLowerCase())) {
			modes[2]++;
		} else if ("siege".equals(mode.toLowerCase()) || "seige".equals(mode.toLowerCase())) {
			modes[3]++;
		} else if ("arena".equals(mode.toLowerCase())) {
			modes[4]++;
		} else if ("duel".equals(mode.toLowerCase())) {
			modes[5]++;
		} else if ("assault".equals(mode.toLowerCase())) {
			modes[6]++;
		}
	}

	public static void voteRole(String role)
	{

		if (!voteActive)
			return;

		if ("sup".equals(role.toLowerCase()) || "supp".equals(role.toLowerCase()) || "support".equals(role.toLowerCase())) {
			roles[0]++;
		} else if ("adc".equals(role.toLowerCase()) || "adcarry".equals(role.toLowerCase())) {
			roles[1]++;
		} else if ("jungle".equals(role.toLowerCase()) || "jungler".equals(role.toLowerCase()) || "jg".equals(role.toLowerCase()) || "jgl".equals(role.toLowerCase()) || "jglr".equals(role.toLowerCase())) {
			roles[2]++;
		} else if ("mid".equals(role.toLowerCase()) || "middle".equals(role.toLowerCase()) || "midd".equals(role.toLowerCase())) {
			roles[3]++;
		} else if ("solo".equals(role.toLowerCase())) {
			roles[4]++;
		}
	}
	
	public static void voteGod(String god)
	{
		if (!voteActive)
			return;

		if ("agni".equals(god.toLowerCase())) {
			gods[0]++;
		} else if ("amc".equals(god.toLowerCase()) || "ah muzen cab".equals(god.toLowerCase()) || "ahmuzencab".equals(god.toLowerCase())) {
			gods[1]++;
		} else if ("ap".equals(god.toLowerCase()) || "ah puch".equals(god.toLowerCase()) || "ahpuch".equals(god.toLowerCase()) || "puch".equals(god.toLowerCase())) {
			gods[2]++;
		} else if ("amaterasu".equals(god.toLowerCase()) || "ama".equals(god.toLowerCase())) {
			gods[3]++;
		} else if ("anhur".equals(god.toLowerCase())) {
			gods[4]++;
		} else if ("anubis".equals(god.toLowerCase()) || "goobis".equals(god.toLowerCase()) || "noobis".equals(god.toLowerCase()) || "nubis".equals(god.toLowerCase())) {
			gods[5]++;
		} else if ("ao".equals(god.toLowerCase()) || "kuang".equals(god.toLowerCase()) || "ao kuang".equals(god.toLowerCase()) || "aokuang".equals(god.toLowerCase()) || "ak".equals(god.toLowerCase())) {
			gods[6]++;
		} else if ("aphro".equals(god.toLowerCase()) || "aphrodite".equals(god.toLowerCase()) || "afro".equals(god.toLowerCase())) {
			gods[7]++;
		} else if ("apollo".equals(god.toLowerCase())) {
			gods[8]++;
		} else if ("arachne".equals(god.toLowerCase())) {
			gods[9]++;
		} else if ("ares".equals(god.toLowerCase())) {
			gods[10]++;
		} else if ("art".equals(god.toLowerCase()) || "artemis".equals(god.toLowerCase())) {
			gods[11]++;
		} else if ("athena".equals(god.toLowerCase())) {
			gods[12]++;
		} else if ("awilix".equals(god.toLowerCase())) {
			gods[13]++;
		} else if ("bacchus".equals(god.toLowerCase()) || "baccus".equals(god.toLowerCase())) {
			gods[14]++;
		} else if ("baka".equals(god.toLowerCase()) || "bakasura".equals(god.toLowerCase())) {
			gods[15]++;
		} else if ("bastet".equals(god.toLowerCase())) {
			gods[16]++;
		} else if ("bell".equals(god.toLowerCase()) || "bellona".equals(god.toLowerCase())) {
			gods[17]++;
		} else if ("cabra".equals(god.toLowerCase()) || "cabraken".equals(god.toLowerCase()) || "cabroken".equals(god.toLowerCase()) || "cabrakan".equals(god.toLowerCase())) {
			gods[18]++;
		} else if ("chaac".equals(god.toLowerCase())) {
			gods[19]++;
		} else if ("change".equals(god.toLowerCase()) || "chang'e".equals(god.toLowerCase())) {
			gods[20]++;
		} else if ("chiron".equals(god.toLowerCase())) {
			gods[21]++;
		} else if ("chronos".equals(god.toLowerCase())) {
			gods[22]++;
		} else if ("cupid".equals(god.toLowerCase())) {
			gods[23]++;
		} else if ("fenrir".equals(god.toLowerCase()) || "fen".equals(god.toLowerCase())) {
			gods[24]++;
		} else if ("freya".equals(god.toLowerCase())) {
			gods[25]++;
		} else if ("geb".equals(god.toLowerCase())) {
			gods[26]++;
		} else if ("guan".equals(god.toLowerCase()) || "guan yu".equals(god.toLowerCase()) || "guanyu".equals(god.toLowerCase()) || "gy".equals(god.toLowerCase())) {
			gods[27]++;
		} else if ("hades".equals(god.toLowerCase())) {
			gods[28]++;
		} else if ("hebo".equals(god.toLowerCase()) || "he bo".equals(god.toLowerCase()) || "hb".equals(god.toLowerCase())) {
			gods[29]++;
		} else if ("hel".equals(god.toLowerCase()) || "hell".equals(god.toLowerCase())) {
			gods[30]++;
		} else if ("herc".equals(god.toLowerCase()) || "hercule".equals(god.toLowerCase()) || "hercules".equals(god.toLowerCase())) {
			gods[31]++;
		} else if ("hou".equals(god.toLowerCase()) || "hou yi".equals(god.toLowerCase()) || "houyi".equals(god.toLowerCase()) || "hy".equals(god.toLowerCase())) {
			gods[32]++;
		} else if ("hun".equals(god.toLowerCase()) || "batz".equals(god.toLowerCase()) || "bats".equals(god.toLowerCase()) || "hun batz".equals(god.toLowerCase()) || "hunbatz".equals(god.toLowerCase())) {
			gods[33]++;
		} else if ("isis".equals(god.toLowerCase())) {
			gods[34]++;
		} else if ("janus".equals(god.toLowerCase()) || "jan".equals(god.toLowerCase())) {
			gods[35]++;
		} else if ("jing".equals(god.toLowerCase()) || "wei".equals(god.toLowerCase()) || "jingwei".equals(god.toLowerCase()) || "way".equals(god.toLowerCase()) || "jing wei".equals(god.toLowerCase()) || "jing way".equals(god.toLowerCase()) || "jingway".equals(god.toLowerCase()) || "jw".equals(god.toLowerCase())) {
			gods[36]++;
		} else if ("kali".equals(god.toLowerCase())) {
			gods[37]++;
		} else if ("khep".equals(god.toLowerCase()) || "khepri".equals(god.toLowerCase())) {
			gods[38]++;
		} else if ("kkk".equals(god.toLowerCase()) || "kuku".equals(god.toLowerCase()) || "kukulkan".equals(god.toLowerCase())) {
			gods[39]++;
		} else if ("kumba".equals(god.toLowerCase()) || "kumbha".equals(god.toLowerCase()) || "kumbhakarna".equals(god.toLowerCase()) || "kumbakarna".equals(god.toLowerCase())) {
			gods[40]++;
		} else if ("loki".equals(god.toLowerCase())) {
			gods[41]++;
		} else if ("med".equals(god.toLowerCase()) || "medusa".equals(god.toLowerCase())) {
			gods[42]++;
		} else if ("merc".equals(god.toLowerCase()) || "mer".equals(god.toLowerCase()) || "mercury".equals(god.toLowerCase())) {
			gods[43]++;
		} else if ("ne".equals(god.toLowerCase()) || "zha".equals(god.toLowerCase()) || "nezha".equals(god.toLowerCase()) || "ne zha".equals(god.toLowerCase()) || "nz".equals(god.toLowerCase())) {
			gods[44]++;
		} else if ("neith".equals(god.toLowerCase()) || "nieth".equals(god.toLowerCase())) {
			gods[45]++;
		} else if ("nem".equals(god.toLowerCase()) || "nemesis".equals(god.toLowerCase())) {
			gods[46]++;
		} else if ("nox".equals(god.toLowerCase())) {
			gods[47]++;
		} else if ("nu".equals(god.toLowerCase()) || "wa".equals(god.toLowerCase()) || "nw".equals(god.toLowerCase()) || "nuwa".equals(god.toLowerCase()) || "nu wa".equals(god.toLowerCase())) {
			gods[48]++;
		} else if ("odin".equals(god.toLowerCase()) || "brodin".equals(god.toLowerCase()) || "oden".equals(god.toLowerCase())) {
			gods[49]++;
		} else if ("osiris".equals(god.toLowerCase())) {
			gods[50]++;
		} else if ("pos".equals(god.toLowerCase()) || "pose".equals(god.toLowerCase()) || "poseidon".equals(god.toLowerCase()) || "posiedon".equals(god.toLowerCase()) || "poseiden".equals(god.toLowerCase())) {
			gods[51]++;
		} else if ("ra".equals(god.toLowerCase())) {
			gods[52]++;
		} else if ("raij".equals(god.toLowerCase()) || "raijin".equals(god.toLowerCase())) {
			gods[53]++;
		} else if ("ram".equals(god.toLowerCase()) || "rama".equals(god.toLowerCase())) {
			gods[54]++;
		} else if ("rat".equals(god.toLowerCase()) || "ratatoskr".equals(god.toLowerCase())) {
			gods[55]++;
		} else if ("ravan".equals(god.toLowerCase()) || "rav".equals(god.toLowerCase()) || "ravana".equals(god.toLowerCase())) {
			gods[56]++;
		} else if ("scylla".equals(god.toLowerCase())) {
			gods[57]++;
		} else if ("serq".equals(god.toLowerCase()) || "serqet".equals(god.toLowerCase()) || "serket".equals(god.toLowerCase())) {
			gods[58]++;
		} else if ("skadi".equals(god.toLowerCase()) || "kaldr".equals(god.toLowerCase())) {
			gods[59]++;
		} else if ("sobek".equals(god.toLowerCase())) {
			gods[60]++;
		} else if ("sol".equals(god.toLowerCase())) {
			gods[61]++;
		} else if ("swk".equals(god.toLowerCase()) || "sun".equals(god.toLowerCase()) || "wukong".equals(god.toLowerCase()) || "sunwukong".equals(god.toLowerCase()) || "sun wukong".equals(god.toLowerCase()) || "sun wu kong".equals(god.toLowerCase())) {
			gods[62]++;
		} else if ("sylv".equals(god.toLowerCase()) || "sylvanus".equals(god.toLowerCase())) {
			gods[63]++;
		} else if ("than".equals(god.toLowerCase()) || "thana".equals(god.toLowerCase()) || "thanatos".equals(god.toLowerCase())) {
			gods[64]++;
		} else if ("thor".equals(god.toLowerCase())) {
			gods[65]++;
		} else if ("tyr".equals(god.toLowerCase())) {
			gods[66]++;
		} else if ("ullr".equals(god.toLowerCase())) {
			gods[67]++;
		} else if ("vaman".equals(god.toLowerCase()) || "vam".equals(god.toLowerCase()) || "vamana".equals(god.toLowerCase())) {
			gods[68]++;
		} else if ("vulcan".equals(god.toLowerCase())) {
			gods[69]++;
		} else if ("xbal".equals(god.toLowerCase()) || "xbalanque".equals(god.toLowerCase())) {
			gods[70]++;
		} else if ("xing".equals(god.toLowerCase()) || "tian".equals(god.toLowerCase()) || "xt".equals(god.toLowerCase()) || "xingtian".equals(god.toLowerCase()) || "xing tian".equals(god.toLowerCase())) {
			gods[71]++;
		} else if ("ymir".equals(god.toLowerCase())) {
			gods[72]++;
		} else if ("zeus".equals(god.toLowerCase()) || "zues".equals(god.toLowerCase())) {
			gods[73]++;
		} else if ("zk".equals(god.toLowerCase()) || "zong".equals(god.toLowerCase()) || "zhong".equals(god.toLowerCase()) || "zhongkui".equals(god.toLowerCase()) || "kui".equals(god.toLowerCase()) || "zhong kui".equals(god.toLowerCase())) {
			gods[74]++;
		} else if ("susan".equals(god.toLowerCase()) || "susanoo".equals(god.toLowerCase()) || "susano".equals(god.toLowerCase()) || "susano'o".equals(god.toLowerCase())) {
			gods[75]++;
		} else if ("cama".equals(god.toLowerCase()) || "camasots".equals(god.toLowerCase()) || "camazot".equals(god.toLowerCase()) || "camaz".equals(god.toLowerCase()) || "camazots".equals(god.toLowerCase()) || "camazotz".equals(god.toLowerCase())) {
			gods[76]++;
		} else if ("es".equals(god.toLowerCase()) || "erl".equals(god.toLowerCase()) || "erlan".equals(god.toLowerCase()) || "erlang".equals(god.toLowerCase()) || "shen".equals(god.toLowerCase()) || "erlang shen".equals(god.toLowerCase()) || "erlangshen".equals(god.toLowerCase())) {
			gods[77]++;
		} else if ("iz".equals(god.toLowerCase()) || "iza".equals(god.toLowerCase()) || "izanamy".equals(god.toLowerCase()) || "izanami".equals(god.toLowerCase()) || "izi".equals(god.toLowerCase())) {
			gods[78]++;
		} else if ("nike".equals(god.toLowerCase())) {
			gods[79]++;
		} else if ("terra".equals(god.toLowerCase()) || "tera".equals(god.toLowerCase()) || "terre".equals(god.toLowerCase())) {
			gods[80]++;
		} else if ("toth".equals(god.toLowerCase()) || "thot".equals(god.toLowerCase()) || "thuth".equals(god.toLowerCase()) || "thoth".equals(god.toLowerCase())) {
			gods[81]++;
		} else if ("faf".equals(god.toLowerCase()) || "fafn".equals(god.toLowerCase()) || "fafnir".equals(god.toLowerCase()) || "dragon".equals(god.toLowerCase())) {
			gods[82]++;
		}
	}

	public static String getResults()
	{
		String str = "";
		int highest = 0, index = -1;
		
		if (topGod != null)
			str = str.concat(" The chosen god was " + topGod + ".");
		else
		{
			for (int i = 0; i < gods.length; i++)
			{
				if (gods[i] > highest)
				{
					highest = gods[i];
					index = i;
				}
			}
			
			if (highest != 0)
				str = str.concat(" The chosen god was " + (topGod = findGod(index)) + ".");
			
			highest = 0;
			index = -1;
		}
		
		if (topMode != null)
			str = str.concat(" The chosen mode was " + topMode + ".");
		else
		{
			for (int i = 0; i < modes.length; i++)
				if (modes[i] > highest)
				{
					highest = modes[i];
					index = i;
				}
			
			if (highest != 0)
				str = str.concat(" The chosen mode was " + (topMode = findMode(index)) + ".");
			
			highest = 0;
			index = -1;
		}
		
		if (topRole != null)
			str = str.concat(" The chosen role was " + topRole + ".");
		else
		{		
			for (int i = 0; i < roles.length; i++)
				if (roles[i] > highest)
				{
					highest = roles[i];
					index = i;
				}
			
			if (highest != 0)
				str = str.concat(" The chosen role was " + (topRole = findRole(index)) + ".");
		}
		
		return str;
	}

	public static boolean hasVoted(String nick, String type)
	{
		return false;
	}

	public static String findGod(int i)
	{
		switch (i){
		case 0: return "Agni";
		case 1: return "Ah Muzen Cab";
		case 2: return "Ah Puch";
		case 3: return "Amaterasu";
		case 4: return "Anhur";
		case 5: return "Anubis";
		case 6: return "Ao Kuang";
		case 7: return "Aphrodite";
		case 8: return "Apollo";
		case 9: return "Arachne";
		case 10: return "Ares";
		case 11: return "Artemis";
		case 12: return "Athena";
		case 13: return "Awilix";
		case 14: return "Bacchus";
		case 15: return "Bakasura";
		case 16: return "Bastet";
		case 17: return "Bellona";
		case 18: return "Cabrakan";
		case 19: return "Chaac";
		case 20: return "Chang'e";
		case 21: return "Chiron";
		case 22: return "Chronos";
		case 23: return "Cupid";
		case 24: return "Fenrir";
		case 25: return "Freya";
		case 26: return "Geb";
		case 27: return "Guan Yu";
		case 28: return "Hades";
		case 29: return "He Bo";
		case 30: return "Hel";
		case 31: return "Hercules";
		case 32: return "Hou Yi";
		case 33: return "Hun Batz";
		case 34: return "Isis";
		case 35: return "Janus";
		case 36: return "Jing Wei";
		case 37: return "Kali";
		case 38: return "Khepri";
		case 39: return "Kukulkan";
		case 40: return "Kumbhakarna";
		case 41: return "Loki";
		case 42: return "Medusa";
		case 43: return "Mercury";
		case 44: return "Ne Zha";
		case 45: return "Neith";
		case 46: return "Nemesis";
		case 47: return "Nox";
		case 48: return "Nu Wa";
		case 49: return "Odin";
		case 50: return "Osiris";
		case 51: return "Poseidon";
		case 52: return "Ra";
		case 53: return "Raijin";
		case 54: return "Rama";
		case 55: return "Ratatoskr";
		case 56: return "Ravana";
		case 57: return "Scylla";
		case 58: return "Serqet";
		case 59: return "Skadi";
		case 60: return "Sobek";
		case 61: return "Sol";
		case 62: return "Sun Wukong";
		case 63: return "Sylvanus";
		case 64: return "Thanatos";
		case 65: return "Thor";
		case 66: return "Tyr";
		case 67: return "Ullr";
		case 68: return "Vamana";
		case 69: return "Vulcan";
		case 70: return "Xbalanque";
		case 71: return "Xing Tian";
		case 72: return "Ymir";
		case 73: return "Zeus";
		case 74: return "Zhong Kui";
		case 75: return "Susano";
		case 76: return "Camazotz";
		case 77: return "Erlang Shen";
		case 78: return "Izanami";
		case 79: return "Nike";
		case 80: return "Terra";
		case 81: return "Thoth";
		case 82: return "Fafnir";
		default: return "";
		}
	}

	public static String findMode(int i)
	{
		switch (i){
		case 0: return "Conquest";
		case 1: return "Joust";
		case 2: return "Mode of the Day";
		case 3: return "Siege";
		case 4: return "Arena";
		case 5: return "Duel";
		case 6: return "Assault";
		default: return "";
		}
	}

	public static String findRole(int i)
	{
		switch (i){
		case 0: return "Support";
		case 1: return "ADC";
		case 2: return "Jungler";
		case 3: return "Mid";
		case 4: return "Solo";
		default: return "";
		}
	}

	public static boolean veto(MessageEvent event)
	{
		String message = event.getMessage();
		String veto = message.substring(message.indexOf("!veto") + 6, message.length());
		
		if (veto.split(" ").length == 1)
		{
			if (Vote.voteRole(veto) != -1)
			{
				topRole = findRole(Vote.voteRole(veto));
				return true;
			}
			else if (Vote.voteGod(veto) != -1)
			{
				topGod = findGod(Vote.voteGod(veto));
				return true;
			}
			return false;
		}
		else
		{
			if (Vote.voteRole(veto.split(" ")[0]) != -1)
			{
				topRole = findRole(Vote.voteRole(veto.split(" ")[0]));
				String str = veto.substring(veto.indexOf(veto.split(" ")[1]), veto.length() - 1);
				if (Vote.voteGod(str) != -1)
				{
					topGod = findGod(Vote.voteGod(str));
					return true;
				}
			}
			else if (Vote.voteRole(veto.split(" ")[veto.split(" ").length]) != -1)
			{
				topRole = findRole(Vote.voteRole(veto.split(" ")[veto.split(" ").length]));
				String str = veto.substring(0, veto.indexOf(veto.split(" ")[veto.split(" ").length]) - 1);
				if (Vote.voteGod(str) != -1)
				{
					topGod = findGod(Vote.voteGod(str));
					return true;
				}
			}
		}
		return false;
	}
}

