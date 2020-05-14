package bot;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;

public class GetCoronaData {
    public static final String CORONA_METER = "https://www.worldometers.info/coronavirus/";
    public static ArrayList<GetCoronaData> result = new ArrayList<>();

    public String country, cases, deaths, recovered, critical;

    public static ArrayList<GetCoronaData> getInfectedCountriesData(String url) throws IOException {
        Elements table = scrape(url, "table#main_table_countries_today");
        Elements rows = table.select("tr");
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            setData(cols.get(0).text(),
                    cols.get(1).text(),
                    cols.get(3).text().isEmpty() ? "0" : cols.get(3).text(),
                    cols.get(5).text().isEmpty() ? "0" : cols.get(5).text(),
                    cols.get(6).text().isEmpty() ? "0" : cols.get(6).text()
            );
        }
        return result;
    }

    public static GetCoronaData[] getCoronaDataFromJson(String url) {
        GetCoronaData[] data = new GetCoronaData[]{};
        try (FileReader reader = new FileReader("data.json")) {
            Gson json = new Gson();
            data = json.fromJson(reader, GetCoronaData[].class);
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }

    private static Elements scrape(String url, String elem) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.select(elem);
    }

    private static void setData(String country, String cases, String deaths, String recovered, String critical) {
        GetCoronaData info = new GetCoronaData();
        info.country = country;
        info.cases = cases;
        info.deaths = deaths;
        info.recovered = recovered;
        info.critical = critical;
        result.add(info);
    }
}
