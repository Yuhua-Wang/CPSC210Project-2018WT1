package parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class Parser {

    public void readWeather(JSONObject jsonData, String city) throws JSONException {
        JSONArray weatherinfo = jsonData.getJSONArray("weather");
        JSONObject weathertype = weatherinfo.getJSONObject(0);
        JSONObject temperature = jsonData.getJSONObject("main");

        parseWeather(weathertype,temperature, city);
    }

    private void parseWeather(JSONObject weathertype,JSONObject temperature, String city) throws JSONException {
        String type = weathertype.getString("description");
        Double tempInKelvin = temperature.getDouble("temp");

        DecimalFormat f = new DecimalFormat("##.00");
        Double temp = Double.valueOf(f.format(convertToCelsius(tempInKelvin)));
        System.out.println("Today the temperature in " + city +" is: " + temp.toString() + "°C " + "with " + type);
    }

    private double convertToCelsius(double kelvin){
        return kelvin - 273.15;
    }
}
