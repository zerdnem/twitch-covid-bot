//*************************************************************************
// ThreadRunner.java
// Author: Ashton Honeggar
// 
// 
//*************************************************************************
package bot;

import java.io.IOException;

public class ThreadRunner extends Thread {
	private String threadName;
	
	ThreadRunner(String name) {
		super(name);
		threadName = name;
		start();
	}
	public void run(){
		if (threadName == "TimerThread")
			try {
				Timer.startTimer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if (threadName == "BotThread")
			RunBot.startBot(this);
	}
}
