package presentation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class test extends JFrame {

    private static final String API_KEY = "db36bac23c2f4e161a46a11c64fb9260";
    static List<wheatherModel> list = new ArrayList<>();

    static tableModel tm = new tableModel();
    JTable table = new JTable(tm);
    JScrollPane jsp = new JScrollPane(table);

    public test(String city){
        // Replace "tunisia" with the name of your city
        setSize(500,150);
        setLocationRelativeTo(null);
        this.add(jsp);
        setVisible(true);
        fetchWeather(city);

    }

    public static void main(String[] args) {
        new test("tunisia");
    }

    public static void fetchWeather(String city) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&units=metric&appid=" + API_KEY;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(response.toString());
            displayWeather(jsonData);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void displayWeather(JSONObject data) {
        JSONObject cityObject = (JSONObject) data.get("city");
        String cityName = (String) cityObject.get("name");
        JSONArray forecastData = (JSONArray) data.get("list");

        for (int i = 0; i < forecastData.size(); i+=9) {
            JSONObject item = (JSONObject) forecastData.get(i);
            Date date = new Date((long) item.get("dt") * 1000);
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            String day = dayFormat.format(date);

            JSONObject weather = (JSONObject) ((JSONArray) item.get("weather")).get(0);
            String icon = (String) weather.get("icon");
            String description = (String) weather.get("description");

            JSONObject main = (JSONObject) item.get("main");
            double temp = (double) main.get("temp");
            long humidity = (long) main.get("humidity");

            JSONObject wind = (JSONObject) item.get("wind");
            double speed = (double) wind.get("speed");

            System.out.println("Day: " + day);
            System.out.println("Description: " + description);
            System.out.println("Temperature: " + temp + "°C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Wind speed: " + speed + " km/h");
            System.out.println();
            list.add(new wheatherModel(day,temp+"",description,humidity+"",speed+""));
            tm.loadData(list);

        }

        System.out.println("Weather in " + cityName);
    }
}
