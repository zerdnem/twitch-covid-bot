//*************************************************************************
// RunBot.java
// Author: Ashton Honeggar
// 
// Driver to run bot
//*************************************************************************
package bot;

import javax.swing.JFrame;

import com.google.gson.Gson;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RunBot {
	
	static Configuration twitch;

	static PircBotX bot;
	static Thread botThread;
	private static boolean isRunning = false;

	private static void saveCoronaInfectedCountriesData() throws IOException {
		ArrayList<GetCoronaData> result = GetCoronaData.getInfectedCountriesData((GetCoronaData.CORONA_METER));
		Gson gson = new Gson();
		String json = gson.toJson(result);
		try (FileWriter file = new FileWriter("data.json")) {
			file.write(json);
			System.out.println("Successfully Copied JSON Object to file...");
		}
	}

	public static void main(String[] args) throws IOException {
		Preferences.loadPreferences();

		saveCoronaInfectedCountriesData();

		updateBot();
		
		new ThreadRunner("TimerThread");
		JFrame frame = new BaseGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void sendMessage(String channel, String message) {
		bot.send().message(channel, message);
	}
	
	public static void startBot(Thread t) {
		botThread = t;
		try {
			bot = new PircBotX(twitch);
			isRunning = true;
			bot.startBot();
		} catch (Exception e) {
			e.printStackTrace();
			isRunning = false;
			return;
		}
	}
	
	public static void stopBot() {
		bot.stopBotReconnect();
		bot.sendIRC().quitServer();
		isRunning = false;
	}
	
	public static boolean isRunning() {
		return isRunning;
	}
	
	public static void updateBot() {
		twitch = new Configuration.Builder()
//		.setName(Preferences.getBotName())
				.setName("kigwabot")
				.setLogin("testtingbots")
//		.setLogin(Preferences.getBotName())
		.addServer("irc.chat.twitch.tv", 6667)
//		.setServerPassword("oauth:" + Preferences.getBotAuth())
				.setServerPassword("OAUTH PASSWORD")
		.setAutoReconnect(true)
		.setAutoReconnectAttempts(5)
		.addCapHandler(new EnableCapHandler("twitch.tv/membership"))
		.addCapHandler(new EnableCapHandler("twitch.tv/tags"))
		.addCapHandler(new EnableCapHandler("twitch.tv/commands"))
//		.addAutoJoinChannel("#" + Preferences.getStreamerChannel())
				.addAutoJoinChannel("#kapangyarihan")
		.addListener(new Commands())
		.addListener(new JoinListen())
		.buildConfiguration();
	}
}
