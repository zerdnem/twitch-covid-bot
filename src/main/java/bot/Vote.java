//*************************************************************************
// 
// Author: Ashton Honeggar
// 
//
//*************************************************************************
package bot;

public class Vote{

	static boolean voteActive;
	String nick;
	int godChoice;
	int modeChoice;
	int roleChoice;
	
	public Vote(String nick, String vote, String type)
	{
		this.nick = nick;
		if (type.equals("god"))
		{
			godChoice = voteGod(vote);
			modeChoice = -1;
			roleChoice = -1;
		}
		else if (type.equals("mode"))
		{
			godChoice = -1;
			modeChoice = voteMode(vote);
			roleChoice = -1;
		}
		else if (type.equals("role"))
		{
			godChoice = -1;
			modeChoice = -1;
			roleChoice = voteRole(vote);
		}
		else
		{
			godChoice = -1;
			modeChoice = -1;
			roleChoice = -1;
		}
	}

	public static void startVote()
	{
		voteActive = true;
	}

	public static void endVote()
	{
		voteActive = false;
	}
	
	public static int voteMode(String mode)
	{
		if ("conquest".equals(mode.toLowerCase()) || "conq".equals(mode.toLowerCase())) {
			return 0;
		} else if ("joust".equals(mode.toLowerCase())) {
			return 1;
		} else if ("motd".equals(mode.toLowerCase())) {
			return 2;
		} else if ("siege".equals(mode.toLowerCase()) || "seige".equals(mode.toLowerCase())) {
			return 3;
		} else if ("arena".equals(mode.toLowerCase())) {
			return 4;
		} else if ("duel".equals(mode.toLowerCase())) {
			return 5;
		} else if ("assault".equals(mode.toLowerCase())) {
			return 6;
		}
		return -1;
	}

	public static int voteRole(String role)
	{
		if ("sup".equals(role.toLowerCase()) || "supp".equals(role.toLowerCase()) || "support".equals(role.toLowerCase())) {
			return 0;
		} else if ("adc".equals(role.toLowerCase()) || "carry".equals(role.toLowerCase()) || "adcarry".equals(role.toLowerCase())) {
			return 1;
		} else if ("jungle".equals(role.toLowerCase()) || "jungler".equals(role.toLowerCase()) || "jg".equals(role.toLowerCase()) || "jgl".equals(role.toLowerCase()) || "jglr".equals(role.toLowerCase())) {
			return 2;
		} else if ("mid".equals(role.toLowerCase()) || "middle".equals(role.toLowerCase()) || "midd".equals(role.toLowerCase())) {
			return 3;
		} else if ("solo".equals(role.toLowerCase())) {
			return 4;
		}
		return -1;
	}
	
	public static int voteGod(String god)
	{
		if ("agni".equals(god.toLowerCase())) {
			return 0;
		} else if ("amc".equals(god.toLowerCase()) || "ah muzen cab".equals(god.toLowerCase()) || "ahmuzencab".equals(god.toLowerCase())) {
			return 1;
		} else if ("ap".equals(god.toLowerCase()) || "ah puch".equals(god.toLowerCase()) || "ahpuch".equals(god.toLowerCase()) || "puch".equals(god.toLowerCase())) {
			return 2;
		} else if ("amaterasu".equals(god.toLowerCase()) || "ama".equals(god.toLowerCase())) {
			return 3;
		} else if ("anhur".equals(god.toLowerCase())) {
			return 4;
		} else if ("anubis".equals(god.toLowerCase())) {
			return 5;
		} else if ("ao".equals(god.toLowerCase()) || "kuang".equals(god.toLowerCase()) || "ao kuang".equals(god.toLowerCase()) || "aokuang".equals(god.toLowerCase()) || "ak".equals(god.toLowerCase())) {
			return 6;
		} else if ("aphro".equals(god.toLowerCase()) || "aphrodite".equals(god.toLowerCase()) || "afro".equals(god.toLowerCase())) {
			return 7;
		} else if ("apollo".equals(god.toLowerCase())) {
			return 8;
		} else if ("arachne".equals(god.toLowerCase())) {
			return 9;
		} else if ("ares".equals(god.toLowerCase())) {
			return 10;
		} else if ("art".equals(god.toLowerCase()) || "artemis".equals(god.toLowerCase())) {
			return 11;
		} else if ("athena".equals(god.toLowerCase())) {
			return 12;
		} else if ("awilix".equals(god.toLowerCase())) {
			return 13;
		} else if ("bacchus".equals(god.toLowerCase()) || "baccus".equals(god.toLowerCase())) {
			return 14;
		} else if ("baka".equals(god.toLowerCase()) || "bakasura".equals(god.toLowerCase())) {
			return 15;
		} else if ("bastet".equals(god.toLowerCase())) {
			return 16;
		} else if ("bell".equals(god.toLowerCase()) || "bellona".equals(god.toLowerCase())) {
			return 17;
		} else if ("cabra".equals(god.toLowerCase()) || "cabraken".equals(god.toLowerCase()) || "cabroken".equals(god.toLowerCase()) || "cabrakan".equals(god.toLowerCase())) {
			return 18;
		} else if ("chaac".equals(god.toLowerCase())) {
			return 19;
		} else if ("change".equals(god.toLowerCase()) || "chang'e".equals(god.toLowerCase())) {
			return 20;
		} else if ("chiron".equals(god.toLowerCase())) {
			return 21;
		} else if ("chronos".equals(god.toLowerCase())) {
			return 22;
		} else if ("cupid".equals(god.toLowerCase())) {
			return 23;
		} else if ("fenrir".equals(god.toLowerCase()) || "fen".equals(god.toLowerCase())) {
			return 24;
		} else if ("freya".equals(god.toLowerCase())) {
			return 25;
		} else if ("geb".equals(god.toLowerCase())) {
			return 26;
		} else if ("guan".equals(god.toLowerCase()) || "guan yu".equals(god.toLowerCase()) || "guanyu".equals(god.toLowerCase()) || "gy".equals(god.toLowerCase())) {
			return 27;
		} else if ("hades".equals(god.toLowerCase())) {
			return 28;
		} else if ("hebo".equals(god.toLowerCase()) || "he bo".equals(god.toLowerCase()) || "hb".equals(god.toLowerCase())) {
			return 29;
		} else if ("hel".equals(god.toLowerCase()) || "hell".equals(god.toLowerCase())) {
			return 30;
		} else if ("herc".equals(god.toLowerCase()) || "hercule".equals(god.toLowerCase()) || "hercules".equals(god.toLowerCase())) {
			return 31;
		} else if ("hou".equals(god.toLowerCase()) || "hou yi".equals(god.toLowerCase()) || "houyi".equals(god.toLowerCase()) || "hy".equals(god.toLowerCase())) {
			return 32;
		} else if ("hun".equals(god.toLowerCase()) || "batz".equals(god.toLowerCase()) || "bats".equals(god.toLowerCase()) || "hun batz".equals(god.toLowerCase()) || "hunbatz".equals(god.toLowerCase())) {
			return 33;
		} else if ("isis".equals(god.toLowerCase())) {
			return 34;
		} else if ("janus".equals(god.toLowerCase()) || "jan".equals(god.toLowerCase())) {
			return 35;
		} else if ("jing".equals(god.toLowerCase()) || "wei".equals(god.toLowerCase()) || "jingwei".equals(god.toLowerCase()) || "way".equals(god.toLowerCase()) || "jing wei".equals(god.toLowerCase()) || "jing way".equals(god.toLowerCase()) || "jingway".equals(god.toLowerCase()) || "jw".equals(god.toLowerCase())) {
			return 36;
		} else if ("kali".equals(god.toLowerCase())) {
			return 37;
		} else if ("khep".equals(god.toLowerCase()) || "khepri".equals(god.toLowerCase())) {
			return 38;
		} else if ("kkk".equals(god.toLowerCase()) || "kuku".equals(god.toLowerCase()) || "kukulkan".equals(god.toLowerCase())) {
			return 39;
		} else if ("kumba".equals(god.toLowerCase()) || "kumbha".equals(god.toLowerCase()) || "kumbhakarna".equals(god.toLowerCase()) || "kumbakarna".equals(god.toLowerCase())) {
			return 40;
		} else if ("loki".equals(god.toLowerCase())) {
			return 41;
		} else if ("med".equals(god.toLowerCase()) || "medusa".equals(god.toLowerCase())) {
			return 42;
		} else if ("merc".equals(god.toLowerCase()) || "mer".equals(god.toLowerCase()) || "mercury".equals(god.toLowerCase())) {
			return 43;
		} else if ("ne".equals(god.toLowerCase()) || "zha".equals(god.toLowerCase()) || "nezha".equals(god.toLowerCase()) || "ne zha".equals(god.toLowerCase()) || "nz".equals(god.toLowerCase())) {
			return 44;
		} else if ("neith".equals(god.toLowerCase()) || "nieth".equals(god.toLowerCase())) {
			return 45;
		} else if ("nem".equals(god.toLowerCase()) || "nemesis".equals(god.toLowerCase())) {
			return 46;
		} else if ("nox".equals(god.toLowerCase())) {
			return 47;
		} else if ("nu".equals(god.toLowerCase()) || "wa".equals(god.toLowerCase()) || "nw".equals(god.toLowerCase()) || "nuwa".equals(god.toLowerCase()) || "nu wa".equals(god.toLowerCase())) {
			return 48;
		} else if ("odin".equals(god.toLowerCase()) || "brodin".equals(god.toLowerCase()) || "oden".equals(god.toLowerCase())) {
			return 49;
		} else if ("osiris".equals(god.toLowerCase())) {
			return 50;
		} else if ("pos".equals(god.toLowerCase()) || "pose".equals(god.toLowerCase()) || "poseidon".equals(god.toLowerCase()) || "posiedon".equals(god.toLowerCase()) || "poseiden".equals(god.toLowerCase())) {
			return 51;
		} else if ("ra".equals(god.toLowerCase())) {
			return 52;
		} else if ("raij".equals(god.toLowerCase()) || "raijin".equals(god.toLowerCase())) {
			return 53;
		} else if ("ram".equals(god.toLowerCase()) || "rama".equals(god.toLowerCase())) {
			return 54;
		} else if ("rat".equals(god.toLowerCase()) || "ratatoskr".equals(god.toLowerCase())) {
			return 55;
		} else if ("ravan".equals(god.toLowerCase()) || "rav".equals(god.toLowerCase()) || "ravana".equals(god.toLowerCase())) {
			return 56;
		} else if ("scylla".equals(god.toLowerCase())) {
			return 57;
		} else if ("serq".equals(god.toLowerCase()) || "serqet".equals(god.toLowerCase()) || "serket".equals(god.toLowerCase())) {
			return 58;
		} else if ("skadi".equals(god.toLowerCase()) || "kaldr".equals(god.toLowerCase())) {
			return 59;
		} else if ("sobek".equals(god.toLowerCase())) {
			return 60;
		} else if ("sol".equals(god.toLowerCase())) {
			return 61;
		} else if ("swk".equals(god.toLowerCase()) || "sun".equals(god.toLowerCase()) || "wukong".equals(god.toLowerCase()) || "sunwukong".equals(god.toLowerCase()) || "sun wukong".equals(god.toLowerCase()) || "sun wu kong".equals(god.toLowerCase())) {
			return 62;
		} else if ("sylv".equals(god.toLowerCase()) || "sylvanus".equals(god.toLowerCase())) {
			return 63;
		} else if ("than".equals(god.toLowerCase()) || "thana".equals(god.toLowerCase()) || "thanatos".equals(god.toLowerCase())) {
			return 64;
		} else if ("thor".equals(god.toLowerCase())) {
			return 65;
		} else if ("tyr".equals(god.toLowerCase())) {
			return 66;
		} else if ("ullr".equals(god.toLowerCase())) {
			return 67;
		} else if ("vaman".equals(god.toLowerCase()) || "vam".equals(god.toLowerCase()) || "vamana".equals(god.toLowerCase())) {
			return 68;
		} else if ("vulcan".equals(god.toLowerCase())) {
			return 69;
		} else if ("xbal".equals(god.toLowerCase()) || "xbalanque".equals(god.toLowerCase())) {
			return 70;
		} else if ("xing".equals(god.toLowerCase()) || "tian".equals(god.toLowerCase()) || "xt".equals(god.toLowerCase()) || "xingtian".equals(god.toLowerCase()) || "xing tian".equals(god.toLowerCase())) {
			return 71;
		} else if ("ymir".equals(god.toLowerCase())) {
			return 72;
		} else if ("zeus".equals(god.toLowerCase()) || "zues".equals(god.toLowerCase())) {
			return 73;
		} else if ("zk".equals(god.toLowerCase()) || "zong".equals(god.toLowerCase()) || "zhong".equals(god.toLowerCase()) || "zhongkui".equals(god.toLowerCase()) || "kui".equals(god.toLowerCase()) || "zhong kui".equals(god.toLowerCase())) {
			return 74;
		} else if ("susan".equals(god.toLowerCase()) || "susanoo".equals(god.toLowerCase()) || "susano".equals(god.toLowerCase()) || "susano'o".equals(god.toLowerCase())) {
			return 75;
		} else if ("cama".equals(god.toLowerCase()) || "camasots".equals(god.toLowerCase()) || "camazot".equals(god.toLowerCase()) || "camaz".equals(god.toLowerCase()) || "camazots".equals(god.toLowerCase()) || "camazotz".equals(god.toLowerCase())) {
			return 76;
		} else if ("es".equals(god.toLowerCase()) || "erl".equals(god.toLowerCase()) || "erlan".equals(god.toLowerCase()) || "erlang".equals(god.toLowerCase()) || "shen".equals(god.toLowerCase()) || "erlang shen".equals(god.toLowerCase()) || "erlangshen".equals(god.toLowerCase())) {
			return 77;
		} else if ("iz".equals(god.toLowerCase()) || "iza".equals(god.toLowerCase()) || "izanamy".equals(god.toLowerCase()) || "izanami".equals(god.toLowerCase()) || "izi".equals(god.toLowerCase())) {
			return 78;
		} else if ("nike".equals(god.toLowerCase())) {
			return 79;
		} else if ("terra".equals(god.toLowerCase()) || "tera".equals(god.toLowerCase()) || "terre".equals(god.toLowerCase())) {
			return 80;
		} else if ("toth".equals(god.toLowerCase()) || "thot".equals(god.toLowerCase()) || "thuth".equals(god.toLowerCase()) || "thoth".equals(god.toLowerCase())) {
			return 81;
		} else if ("faf".equals(god.toLowerCase()) || "fafn".equals(god.toLowerCase()) || "fafnir".equals(god.toLowerCase()) || "dragon".equals(god.toLowerCase())) {
			return 82;
		}
		return -1;
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

	public void setGod(String god)
	{
		godChoice = voteGod(god);
	}
	
	public void setRole(String role)
	{
		roleChoice = voteRole(role);
	}
	
	public void setMode(String mode)
	{
		modeChoice = voteMode(mode);
	}

	public int getGod()
	{
		return godChoice;
	}
	
	public int getRole()
	{
		return roleChoice;
	}
	
	public int getMode()
	{
		return modeChoice;
	}
	
	public String getNick()
	{
		return nick;
	}


}

