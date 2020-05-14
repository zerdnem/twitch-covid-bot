//*************************************************************************
// 
// Author: Ashton Honeggar
// 
//
//*************************************************************************
package bot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;

public class JoinListen extends ListenerAdapter {
	public void onJoin(JoinEvent event) throws Exception
	{
		if (event.getUser().getNick().equalsIgnoreCase(Preferences.getBotName()))
		{
			event.getChannel().send().message("/color blueviolet");
		}
		/*else 
			event.getChannel().send().message("Hello, and welcome to the stream, " + 
					event.getUser().getNick() + "!");*/
	}
}
