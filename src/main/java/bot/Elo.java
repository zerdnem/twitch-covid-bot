//*************************************************************************
// 
// Author: Ashton Honeggar
// 
//
//*************************************************************************
package bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Elo {
	public static String CasualElo(String player) throws IOException
	{
		URL url = new URL("http://smite.guru/stats/hr/" + player + "/casual");
		String str = "null";

		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String line = null;

		while ((line = br.readLine()) != null && !line.contains("<img src=\"//cdn.smitegu.ru/assets/img/queues/sgs.png\" class=\"league-img\">"));
		if (line == null)
			return str;
		else
			while ((line = br.readLine()) != null && !line.contains("<strong>"));
		if (line == null)
			return str;
		else 
			str = line.split(">")[1].split("<")[0];
		return str;
	}

	public static String RankedElo(String player) throws IOException
	{
		URL url = new URL("http://smite.guru/stats/hr/" + player + "/league");
		String str = "";

		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String line = null;

		while ((line = br.readLine()) != null && !line.contains("Competitive</a></li>"));

		if (line == null)
			return str;
		else
			for (int i = 0; i < 3; i++)
			{
				while ((line = br.readLine()) != null && !line.contains("<strong>"));

				if (line == null)
					return str;
			}

		str = line.split(">")[1].split("<")[0];
		return str;
	}
}
