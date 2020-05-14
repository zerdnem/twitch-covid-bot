//*************************************************************************
// UrlRead.java
// Author: Ashton Honeggar
// 
// 
//*************************************************************************
package bot;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;

public class UrlRead {
	@SuppressWarnings("deprecation")
	public static Stream getStream(String channel) throws IOException
	{
		URL url = new URL("https://api.twitch.tv/kraken/streams/" + channel.toLowerCase());
		String temp = null;
		
		URLConnection con = url.openConnection();
			con.setRequestProperty("Client-ID", "");
		InputStream is = con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String line = null;
		
		line = br.readLine();
		if (line.contains("\"stream\":null"))
			return new Stream(false, -1, null, -1, null, -1, -1, -1);
		
		long id;
		String game;
		int viewerCount;
		ArrayList<String> viewers = new ArrayList<String>();
		String start;
		long streamStart;
		int resolution;
		int fps;
		
		temp = line.substring(line.indexOf("_id") + 5, line.indexOf(",\"game\":"));
		id = Long.parseLong(temp);
		
		temp = line.substring(line.indexOf("game\":") + 7, line.indexOf("\",\"viewers\""));
		game = temp;
		
		temp = line.substring(line.indexOf("viewers\":") + 9, line.indexOf(",\"video_height\":"));
		viewerCount = Integer.parseInt(temp);
		
		viewers = getViewers(channel);
		
		temp = line.substring(line.indexOf("created_at\":") + 13, line.indexOf("\",\"is_playlist"));
		start = temp;

		streamStart = Date.UTC(Integer.parseInt(start.split("-")[0]) - 1900, Integer.parseInt(start.split("-")[1]) - 1, 
				Integer.parseInt(start.split("-")[2].split("T")[0]), Integer.parseInt(start.split(":")[0].split("T")[1]), 
				Integer.parseInt(start.split(":")[1]), Integer.parseInt(start.split(":")[2].split("Z")[0]));
		
		temp = line.substring(line.indexOf("video_height") + 14, line.indexOf(",\"average_fps"));
		resolution = Integer.parseInt(temp);
		
		temp = line.substring(line.indexOf("average_fps") + 13, line.indexOf(",\"delay\""));
		if (Double.parseDouble(temp) > 54)
			fps = 60;
		else if (Double.parseDouble(temp) > 24)
			fps = 30;
		else
			fps = (int) Double.parseDouble(temp);
		
		return new Stream(true, id, game, viewerCount, viewers, streamStart, resolution, fps);
	}

	public static ArrayList<String> getViewers(String channel) throws IOException{
		URL url = new URL("http://tmi.twitch.tv/group/user/" + channel.toLowerCase() + "/chatters");
		ArrayList<String> viewers = new ArrayList<String>();
		
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String line = null;
		
		while (!(line = br.readLine()).contains("\"viewers\":"));
		while (!(line = br.readLine()).contains("]") && !line.contains("}"))
			viewers.add(line.split("\"")[1]);
		
		return viewers;
	}
	
	public static ArrayList<String> getModerators(String channel) throws IOException
	{
		URL url = new URL("http://tmi.twitch.tv/group/user/" + channel.toLowerCase() + "/chatters");
		ArrayList<String> mods = new ArrayList<String>();
		
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String line = null;
		
		while (!(line = br.readLine()).contains("\"moderators\": ["));
		while (!(line = br.readLine()).contains("]") && !line.contains("}"))
			mods.add(line.split("\"")[1]);
		
		return mods;
	}
}
