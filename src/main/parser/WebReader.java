package parser;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class WebReader {
    private String apiKey = "03c00b959df93f7937e98cfd8cc0a045";
    private String api = "http://api.openweathermap.org/data/2.5/weather?q=Vancouver&APPID=";
    private String city;

    public WebReader(){
        city = "Vancouver";
    }

    public void getWeather() throws MalformedURLException, IOException {

        BufferedReader br = null;

        try {
            String theURL = api + apiKey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            JSONObject jsonData = new JSONObject(sb.toString());

            Parser parser = new Parser();

            parser.readWeather(jsonData,city);

            //System.out.println(jsonData);

        } catch (JSONException e) {
        } catch (UnknownHostException e){
            System.out.println("not able to obtain weather info");
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}
