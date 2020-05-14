//*************************************************************************
// Stream.java
// Author: Ashton Honeggar
// 
// 
//*************************************************************************
package bot;

import java.util.ArrayList;

public class Stream {
	boolean isLive;
	long id;
	String game;
	int viewerCount;
	ArrayList<String> viewers = new ArrayList<String>();
	long streamStart;
	int resolution;
	int fps;
	
	public Stream(boolean isLive, long id, String game, int viewerCount, ArrayList<String> viewers,
			long streamStart, int resolution, int fps)
	{
		this.isLive = isLive;
		this.id = id;
		this.game = game;
		this.viewerCount = viewerCount;
		this.viewers = viewers;
		this.streamStart = streamStart;
		this.resolution = resolution;
		this.fps = fps;
	}
	
	public Boolean getLiveStatus() 
	{
		return isLive;
	}
	
	public long getId()
	{
		return id;
	}
	
	public String getGame()
	{
		return game;
	}
	
	public int getViewerCount()
	{
		return viewerCount;
	}
	
	public ArrayList<String> getViewers()
	{
		return viewers;
	}
	
	public long getStartTime()
	{
		return streamStart;
	}

	public int getResolution()
	{
		return resolution;
	}
	
	public int getFPS()
	{
		return fps;
	}
	
	public String getUptime() {
		if (!isLive)
			return "channel is not live.";
		
		String time = "Uptime: ";
		long difference;
		int hour, min, sec;
		difference = System.currentTimeMillis() - streamStart;
		hour = (int) (difference / 3600000);
		difference -= (hour * 3600000);
		min = (int) (difference / 60000);
		difference -= (min * 60000);
		sec = (int) (difference / 1000);
		
		if (hour > 0)
			time = time.concat(hour + " hrs, ");
		time = time.concat(min + " min, ");
		time = time.concat(sec + "sec.");
		return time;
	}
	
	public static String getUptime(Stream stream) {
		if (!stream.getLiveStatus())
			return "Channel is not live.";
		
		String time = "Uptime: ";
		long start = stream.getStartTime();
		long difference;
		int hour, min, sec;
		difference = System.currentTimeMillis() - start;
		hour = (int) (difference / 3600000);
		difference -= (hour * 3600000);
		min = (int) (difference / 60000);
		difference -= (min * 60000);
		sec = (int) (difference / 1000);
		
		if (hour > 0)
			time = time.concat(hour + " hrs, ");
		time = time.concat(min + " min, ");
		time = time.concat(sec + "sec.");
		return time;
	}
}