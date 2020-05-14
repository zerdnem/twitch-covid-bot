//*************************************************************************
// 
// Author: Ashton Honeggar
// 
//
//*************************************************************************
package bot;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class Commands extends ListenerAdapter {

	Random gen = new Random();
	String bot = Preferences.getBotName();
	String streamer = Preferences.getStreamerName();

	public void onMessage(MessageEvent event) throws Exception {

		String message = event.getMessage();
		String nick = event.getUser().getNick();

		//Hello response
		if (message.equalsIgnoreCase("Hey " + bot) || message.equalsIgnoreCase("Hello " + bot)
				|| message.equalsIgnoreCase("Hi " + bot) && Preferences.getCommandPreference("hello")){
			event.getChannel().send().message("Hello, " + nick + "!");
			return;
		}
		
		// ^
		if (message.equals("^") && Preferences.getCommandPreference("^"))
		{
			event.getChannel().send().message("What that guy said. ^");
			return;
		}

		//!commands
		if (message.equalsIgnoreCase("!commands") && Preferences.getCommandPreference("!commands")) {
			event.getChannel().send().message("");
			return;
		}

		//!startvote
		if (message.equalsIgnoreCase("!startvote") && hasOP(event) && Preferences.getCommandPreference("!startvote"))
		{
			if (GodVoter.voteActive)
				event.getChannel().send().message("A vote is already in progress.");
			else
			{
				event.getChannel().send().message("A Smite selection poll has been started by " + nick +
						". To vote, use \"!votegod\", \"!voterole\", or \"!votemode\" followed by your vote. (For example, !votegod "
						+ "Ah Puch will vote for AP Kappa )");
				GodVoter.resetVote();
				Vote.startVote();
				return;
			}
		}

		//!endvote
		if (message.equalsIgnoreCase("!endvote") && hasOP(event) && Preferences.getCommandPreference("!startvote"))
		{
			Vote.endVote();
			event.getChannel().send().message("The poll has now ended!" + Voter.getResults());
			return;
		}

		//!votegod
		if (message.split(" ")[0].equalsIgnoreCase("!votegod") && Preferences.getCommandPreference("!startvote"))
		{
			String str = "";

			for (int i = 1; i < message.split(" ").length; i++)
			{
				str = str.concat(message.split(" ")[i]);
			}
			Voter.godVote(event.getUser().getNick(), str);
			return;
		}

		if (message.split(" ")[0].equalsIgnoreCase("!corona")) {
			String country = message.split(" ")[1];
			GetCoronaData[] countries = GetCoronaData.getCoronaDataFromJson(country);
			for (GetCoronaData result : countries) {
				if (result.country.equalsIgnoreCase(country)) {
					String text = "BrainSlug " + result.country + " => " + "cases: " + result.cases + ", deaths: " + result.deaths;
					event.getChannel().send().message(text);
					return;
				}
			}
		}

		//!votemode
		if (message.split(" ")[0].equalsIgnoreCase("!votemode") && Preferences.getCommandPreference("!startvote"))
		{
			String str = "";

			for (int i = 1; i < message.split(" ").length; i++)
			{
				str = str.concat(message.split(" ")[i]);
			}
			Voter.modeVote(event.getUser().getNick(), str);
			return;
		}

		//!voterole
		if (message.split(" ")[0].equalsIgnoreCase("!voterole") && Preferences.getCommandPreference("!startvote"))
		{
			String str = "";

			for (int i = 1; i < message.split(" ").length; i++)
			{
				str = str.concat(message.split(" ")[i]);
			}
			Voter.roleVote(event.getUser().getNick(), str);
			return;
		}

		//!veto [god/role]
		if (message.split(" ")[0].equalsIgnoreCase("!veto") && Preferences.getCommandPreference("!veto"))
		{
			if (message.split(" ").length == 1)
			{
				event.getChannel().send().message("Usage: !veto [<god> and/or <role>] | Ex: !veto athena jungle");
				return;
			}
			if (Timer.checkCoins(nick) < 150)
			{
				event.respond("You must have at least 150 coins to use this feature.");
				return;
			}
			if (GodVoter.veto(event))
				event.getChannel().send().message("Veto change successful: " + GodVoter.getResults());
			else
				event.getChannel().send().message("Veto unsuccessful.");
		}
		
		//!results
		if (message.equalsIgnoreCase("!results") && Preferences.getCommandPreference("!results"))
		{
			event.getChannel().send().message("Results: " + Voter.getResults());
			return;
		}
	
		//!elo http://smite.guru/stats/hr/Micfireball/casual
		if (message.split(" ")[0].equalsIgnoreCase("!elo") && Preferences.getCommandPreference("!elo"))
		{
			if (message.split(" ").length > 1)
			{
				if (message.split(" ")[1].equalsIgnoreCase("ranked") || message.split(" ")[1].equalsIgnoreCase("league"))
				{
					if (message.split(" ").length == 2)
						event.getChannel().send().message(Preferences.getStreamerName() + "'s current ranked elo: " + Elo.RankedElo(Preferences.getStreamerChannel()));
					else if (message.split(" ").length == 3)
						event.getChannel().send().message(message.split(" ")[2] + "'s current ranked elo: " + Elo.RankedElo(message.split(" ")[2]));
					else
						event.getChannel().send().message("Correct usage is: !elo [Casual/Ranked] <Player>");
				}
				else if (message.split(" ")[1].equalsIgnoreCase("casual"))
				{
					if (message.split(" ").length == 2)
						event.getChannel().send().message(Preferences.getStreamerName() + "'s current casual elo: " + Elo.CasualElo(Preferences.getStreamerChannel()));
					else if (message.split(" ").length == 3)
						event.getChannel().send().message(message.split(" ")[2] + "'s current casual elo: " + Elo.CasualElo(message.split(" ")[2]));
					else
						event.getChannel().send().message("Correct usage is: !elo [Casual/Ranked] <Player>");
				}
				else if (message.split(" ").length > 2)
					event.getChannel().send().message("Correct usage is: !elo [Casual/Ranked] <Player>");
				else if (message.split(" ").length == 2)
					event.getChannel().send().message(message.split(" ")[1] + "'s current casual elo: " + Elo.CasualElo(message.split(" ")[1]));
			}
			else
				event.getChannel().send().message(Preferences.getStreamerName() + "'s current casual elo: " + Elo.CasualElo(Preferences.getStreamerChannel()));
			return;
		}

		//!sepukku/!seppuku/!seppukku
		if (message.equalsIgnoreCase("!sepukku") || message.equalsIgnoreCase("!seppuku") || 
				message.equalsIgnoreCase("!seppukku") && Preferences.getCommandPreference("!seppuku")){
			event.getChannel().send().message("/timeout " + event.getUser().getNick() + " 1");
			return;
		}

		//!uptime
		if (message.equalsIgnoreCase("!uptime") && Preferences.getCommandPreference("!uptime")){
			event.getChannel().send().message("/me " + UrlRead.getStream(Preferences.getStreamerChannel()).getUptime());
			return;
		}

		//!bonus <player> [coins]
		if (message.split(" ")[0].equalsIgnoreCase("!bonus") && hasOP(event) && Preferences.getCommandPreference("!bonus"))
		{
			ArrayList<Player> players = new ArrayList<Player>();
			boolean exists = false;
			if (message.split(" ").length != 3)
				event.getChannel().send().message("Correct syntax: !bonus <username> [coins]");
			else if (!Pattern.matches("[0-9-]+", message.split(" ")[2]))
				event.getChannel().send().message("Correct syntax: !bonus <username> [coins]");
			else
			{
				players = FileRead.getPlayers();
				for (Player p : players)
				{
					if (p.nick.equalsIgnoreCase(message.split(" ")[1]))
					{
						p.coins += Integer.parseInt(message.split(" ")[2]);
						exists = true;
					}
				}
				if (!exists)
					players.add(new Player(message.split(" ")[1], Integer.parseInt(message.split(" ")[2])));
				FileRead.putPlayers(players);
				event.getChannel().send().message("/me " + message.split(" ")[1] + " has been given " + message.split(" ")[2] + " coins.");
			}
			return;
		}

		//!random god
		//!random loadout
		//!random gamemode
		/*if (message.contains("!random")){
			if (message.split(" ").length == 1 && message.equalsIgnoreCase("!random"))
				event.getChannel().send().message("Use: '!random god/role/gamemode'");
		}*/

		//!hype
		//!ks
		if (message.equalsIgnoreCase("!ks") && Preferences.getCommandPreference("!ks")){
			event.getChannel().send().message("/me Got it! KS! A total of " + FileRead.putVariable("ks", 1) + " kills have been secured. Kappa");
			return;
		}

		//!gamble
		if (message.contains("!gamble") && Preferences.getCommandPreference("!gamble")) {
			if (message.equalsIgnoreCase("!gamble") || message.split(" ").length != 3 || !isInt(message.split(" ")[2]) ||
					(!message.split(" ")[1].equalsIgnoreCase("coin") && !message.split(" ")[1].equalsIgnoreCase("dice")))
				event.getChannel().send().message("/me Use '!gamble coin/dice <amount>' to gamble <amount> of coins. Flipping a coin "
						+ "has a 50% chance of doubling the amount of coins, rolling a dice has a 16.7% chance of getting 6 times the amount of coins");

			int amount = Integer.parseInt(message.split(" ")[2]);

			if (amount > FileRead.getCoins(nick))
			{
				event.respond("You don't have enough coins to do that");
				return;
			}
			if (amount < 15)
			{
				event.respond("Please gamble at least 15 coins.");
				return;
			}

			if (message.split(" ")[1].equalsIgnoreCase("coin"))
				if (gen.nextInt(2) == 0) {
					FileRead.putCoins(nick, amount);
					event.respond("You won! You got " + amount * 2 + " coins and now have " + FileRead.getCoins(nick) + " coins.");
				}
				else {
					FileRead.putCoins(nick, amount * -1);
					event.respond("Sorry, you lost :( You lost " + amount + " coins and now have " + FileRead.getCoins(nick) + " coins.");
				}

			if (message.split(" ")[1].equalsIgnoreCase("dice"))
				if (gen.nextInt(6) == 0) {
					FileRead.putCoins(nick, amount * 5);
					event.respond("You won! You got " + amount * 5 + " coins and now have " + FileRead.getCoins(nick) + " coins.");
				}
				else {
					FileRead.putCoins(nick, amount * -1);
					event.respond("Sorry, you lost :( You lost " + amount + " coins and now have " + FileRead.getCoins(nick) + " coins.");
				}
			return;
		}

		//!rekt
		//!thirsty
		//!RussianRoulette/RR/Roulette
		if (message.equalsIgnoreCase("!rr") || message.equalsIgnoreCase("!roulette") ||
				message.equalsIgnoreCase("!russian") && Preferences.getCommandPreference("!russian")){
			event.getChannel().send().message(Redeem.RouletteAction(FileRead.getPlayerInstance(nick)));
			return;
		}
		
		//!redeem
		/*
		if (message.split(" ")[0].equalsIgnoreCase("!redeem")){
			event.respond(Redeem.RedeemAction(event, message));
			return;
		}
		*/
		
		//!Chemistry/!love
		//!matchmaker
		//!name
		//!fight
		//!bm
		//!banhammer
		//!religion = Christianity (CHRISTISBAE)
		//Filter
		//!flip
		//!viewers


//		if (message.equalsIgnoreCase("!test"))
//			return;		

		//!coins
		if (message.equalsIgnoreCase("!coins") && Preferences.getCommandPreference("!coins"))
		{
			event.respond(Timer.checkCoins(nick) + " coins.");
			return;
		}
		
		//!mods
		if (message.equalsIgnoreCase("!mods") && Preferences.getCommandPreference("!mods"))
		{
			String str = "Mods in chat currently: ";
			for (String s : UrlRead.getModerators(Preferences.getStreamerChannel()))
				str += (s + ", ");
			str = str.substring(0, str.length() - 2);
			str += ".";
			event.getChannel().send().message(str);
			return;
		}
		
		//!checkcoins
		if (message.split(" ")[0].equalsIgnoreCase("!checkcoins") && hasOP(event) && Preferences.getCommandPreference("!checkcoins")){
			if (message.split(" ").length != 2)
				event.getChannel().send().message("/me !checkcoins usage (mods only): !checkcoins [username]");
			else
				event.getChannel().send().message(message.split(" ")[1] + " has " + Timer.checkCoins(message.split(" ")[1]) + " coins.");
			return;
		}
		
		//!addcom
		if (message.split(" ")[0].equalsIgnoreCase("!addcom") && hasOP(event) && Preferences.getCommandPreference("!addcom")){
			if (message.split(" ").length < 3){
				event.getChannel().send().message("/me !addcom usage: !addcom [name] <command/message>");
				return;
			}
			UserCommands.addCommand(message.split(" ")[1], message.substring(message.indexOf(message.split(" ")[2])));
			return;
		}
		
		//!delcom
		if (message.split(" ")[0].equalsIgnoreCase("!delcom") && hasOP(event) && Preferences.getCommandPreference("!addcom")){
			if (message.split(" ").length != 2){
				event.getChannel().send().message("/me !delcom usage: !delcom [name]");
				return;
			}
			if (!UserCommands.commandExists(message.split(" ")[1]))
				event.getChannel().send().message("/me " + message.split(" ")[1] + " command does not exist.");
			else {
				UserCommands.delCommand(message.split(" ")[1]);
				event.getChannel().send().message("/me " + message.split(" ")[1] + " command deleted successfully.");
			}
			return;
		}
		
		//!version
		if (message.equalsIgnoreCase("!version") && hasOP(event) && Preferences.getCommandPreference("!version"))
			event.getChannel().send().message("SaltMod: Version 2.0.0");
		
		//!restartsaltmod
		if (message.equalsIgnoreCase("!restartsaltmod") && hasOP(event))
		{
			Runtime.getRuntime().exec("cmd /c start StartSaltMod.bat /min");
			System.exit(0);
		}
		
		
		if (UserCommands.commandExists(message))
			event.getChannel().send().message(UserCommands.executeCommand(message));
	}

	private boolean hasOP(MessageEvent event)
	{
		/*if (nick.equalsIgnoreCase("Micfireball") || nick.equalsIgnoreCase("Micfirebot") || 
				nick.equalsIgnoreCase("tuxedoedmurphy") || nick.equalsIgnoreCase("bigpimpnkj"))
			return true;*/
		if (event.getTags().toString().contains("broadcaster/1") || event.getTags().toString().contains("mod=1"))
			return true;
		else
			return false;
	}

	//The CSV in this case will have brackets surrounding
	public static ArrayList<String> CSVtoAL(String csv)
	{
		ArrayList<String> list = new ArrayList<String>();
		csv = csv.substring(csv.indexOf("[") + 1, csv.indexOf("]"));
		for (int i = 0; i < csv.split(", ").length; i++)
			list.add(csv.split(", ")[i]);
		return list;
	}

	private static boolean isInt(String str)
	{
		@SuppressWarnings("unused")
		int i;
		try{
			i = Integer.parseInt(str);
		}
		catch (NumberFormatException nfe){
			return false;
		}
		return true;
	}
}
