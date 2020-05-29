package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * this class makes two requests to the API-Provider.
 * the request is triggered when the button btn_getDataListener is pressed
 * From the first request we get the longitude and latitude for the second request.
 * The second request provides the data the we use and display on the GUI
 */

public class RequestWeather {
    /***
     * appid is a variable that safes the API-Key for weather requests
     */
    private final String appid= "9ffde3100f0029f1e405581994739b1e";
    /**
     * is the first part of the URL for our first request as a String
     */
    private final String WebsideForRequest ="http://api.openweathermap.org/data/2.5/weather?q=";
    /**
     * ist the middle part of the URl for the first request
     */
    private final String URLmidCode ="&units=metric&appid=";

    private HttpURLConnection connection;

    /**
     * This is the first part of an URL used to start a request to get the icon
     */
    private final String WebsideForIcon="http://openweathermap.org/img/w/";
    /**
     * This String is the end part of the URL to request the icon provided in the data
     */
    private final String WebsideForIconEnd=".png";


    /**
     * This is the first part of the URL used to request the data safed as String
     */
    private final String WebsideDataRequest ="https://api.openweathermap.org/data/2.5/onecall?";
    /**
     * This is the middle part of the URL to request the get the Data
     */
    private String URLmidDataRequest = "&%20exclude=minutely&appid=";
    /**
     * this is a String to safe small parts of the 2nd URl for Data Request as String
     */
    private String LatURL="lat=";
    /**
     * this is a String to safe small parts of the 2nd URl for Data Request as String
     */
    private String LonURL="&lon=";
    /**
     * a String variable to safe the requested city
     */
    private String city;

    /**
     * Method to set the city variable
     * @param city
     */
    public void setCity(String city){
        this.city = city;
    }

    /**
     * Variable to safe the latitude provided by the first request
     */
    public double Lat;
    /**
     * Variable to safe the longitude provided by the first request
     */
    public double Lon;

    /**
     * constructor Method of RequestWeather, it returns an instance of the currentWeather class
     * @return
     */
    public Weather RequestWeather(){
       return verbindung(true);
    }

    /**
     *This Method parses the JSON-File we recieve for our request
     * to safe the Longitude and the Latitude
     * * @param response ist ein Parameter, der sachen macht
     */
    public void parse1(String response) {
        JSONObject GeoStats;
            try {
                GeoStats = new JSONObject(response);
                double lon = GeoStats.getJSONObject("coord").getDouble("lon");
                double lat = GeoStats.getJSONObject("coord").getDouble("lat");
                Lon = lon;
                Lat = lat;
                System.out.println(lon);
                System.out.println(lat);
            } catch (JSONException e) {
                e.printStackTrace();
                Fehler a = new Fehler();
                System.out.println("LUL");
            }

    }
    /***
     *This method parses the JSON of the second request we make to the API-Url an safes the values in an Object of Weather
     * @param responseBody ist das JSON das von unserem zweiten Request an die Api kommt und eingelesen wurde
     *
     * @return
     */
    public currentWeather parse2(String responseBody){
        JSONObject currentWeather;
        currentWeather a = new currentWeather();
        try {
            currentWeather = new JSONObject(responseBody);
            JSONObject WeatherStats = (currentWeather.getJSONObject("current"));
            a.setCity(city);
            a.setTemp(WeatherStats.getDouble("temp"));
            a.setDescription( WeatherStats.getJSONArray("weather").getJSONObject(0).getString("description"));
            a.setHumidity(WeatherStats.getInt("humidity"));
            a.setCloudiness(WeatherStats.getDouble("clouds"));
            a.setWindSpeed(WeatherStats.getDouble("wind_speed"));
            String icon = WeatherStats.getJSONArray("weather").getJSONObject(0).getString("icon");
            String URLSting=WebsideForIcon+icon+WebsideForIconEnd;
            URL url = new URL(URLSting);
            ImageIcon image = new ImageIcon(url);
            a.img_Weather=image;
            for(int i=0;i<47;i++){
              JSONObject zwischenWeather= (JSONObject) (currentWeather.getJSONArray("hourly").getJSONObject(i));
               currentWeather zwischenWeatherWeather = new currentWeather();
               double temp =zwischenWeatherWeather.getTemp();
                a.setNext48Hours(i,temp);
            }

                JSONArray daily= (currentWeather.getJSONArray("daily"));
                for(int i=0;i<7;i++) {
                    DailyForcastWeather zwischenWetter = new DailyForcastWeather();
                    JSONObject zwischenWeather = daily.getJSONObject(i);
                    zwischenWetter.city = city;
                    zwischenWetter.setMaxTemp(zwischenWeather.getJSONObject("temp").getDouble("max"));
                    zwischenWetter.setMinTemp(zwischenWeather.getJSONObject("temp").getDouble("min"));
                    zwischenWetter.setDescription(zwischenWeather.getJSONArray("weather").getJSONObject(0).getString("description"));
                    zwischenWetter.setHumidity(zwischenWeather.getInt("humidity"));
                    zwischenWetter.setCloudiness(zwischenWeather.getInt("clouds"));
                    zwischenWetter.setWindSpeed(zwischenWeather.getDouble("wind_speed"));
                    String icon2 = zwischenWeather.getJSONArray("weather").getJSONObject(0).getString("icon");
                    String URLSting2=WebsideForIcon+icon2+WebsideForIconEnd;
                    URL url2 = new URL(URLSting2);
                    ImageIcon image2 = new ImageIcon(url2);
                    zwischenWetter.img_Weather=image2;
                    a.setNext7Days(i, zwischenWetter);
                }
            return a;
        } catch (JSONException | MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(a.getDescription());
       return a;
    }

    /***
     * Die Methode stellt eine Verbindung mit dem Server des APi Anbieters auf und macht einen Request
     * Die URL setzt sich aus den String konstanten zusammen. Insgesammt gibt es zwei Abfragen, zuerst eine Abfrage
     * von der wir uns die Longitude und die Latitude zurückgeben um diese dann für einen Request an die eigentliche API
     * zu verwenden.
     * @param geoAbfrage mit diesem Parameter ändert sich das Verhalten innerhalb der Methode
     * @return
     */
    public currentWeather verbindung(boolean geoAbfrage){
        String URLString;
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        currentWeather b = new currentWeather();
        if(geoAbfrage){
            URLString = WebsideForRequest + city + URLmidCode +appid;
        }else{
            URLString = WebsideDataRequest + LatURL+Lat+LonURL+Lon+URLmidDataRequest+appid;;
        }
        try {
            URL url = new URL(URLString);
            System.out.println(url);
            connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            if(status>299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line=reader.readLine())!=null){
                    responseContent.append(line);
                }
                reader.close();
            }else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line=reader.readLine())!=null){
                    responseContent.append(line);
                }
                reader.close();
            }
            if(geoAbfrage){
                parse1(responseContent.toString());
               b= verbindung(false);
                connection.disconnect();
            }else{
                System.out.println(Lon+" "+Lat);
                b= parse2(responseContent.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            connection.disconnect();
        }
        return b;
    }
}
