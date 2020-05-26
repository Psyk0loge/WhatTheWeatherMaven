package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RequestWeather {
    //Api-Key
    /***
     * appid is a variable that safes the API-Key for weather requests
     */
    private final String appid= "9ffde3100f0029f1e405581994739b1e";

    //URL parts to get the longitude and the latitude
    private final String WebsideForRequest ="http://api.openweathermap.org/data/2.5/weather?q=";
    private final String URLmidCode ="&units=metric&appid=";
    private HttpURLConnection connection;

    //URL stuff to get the data
    private final String WebsideDataRequest ="https://api.openweathermap.org/data/2.5/onecall?";
    private String URLmidDataRequest = "&%20exclude=hourly&appid=";
    private String LatURL="lat=";
    private String LonURL="&lon=";
    private String city;

    public void setCity(String city){
        this.city = city;
    }

    //changable lon and lat Strings for dynamic request
    public double Lat;
    public double Lon;

    public Weather RequestWeather(){
       return verbindung(true);
    }

    /**
     *This Method parses the JSON-File we recieve for our request
     * to safe the Longitude and the Latitude
     * * @param response ist ein Parameter, der sachen macht
     */
    public void parse1(String response)  {

        JSONObject GeoStats = null;
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
            a.setCity(city);
            a.setTemp(currentWeather.getJSONObject("current").getDouble("temp"));
            a.setDescription( currentWeather.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("description"));
            a.setHumidity(currentWeather.getJSONObject("current").getInt("humidity"));
            a.setCloudines(currentWeather.getJSONObject("current").getDouble("clouds"));
            a.setWindSpeed(currentWeather.getJSONObject("current").getDouble("wind_speed"));
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
                    zwischenWetter.setCloudines(zwischenWeather.getInt("clouds"));
                    zwischenWetter.setWindSpeed(zwischenWeather.getDouble("wind_speed"));
                    a.setNext7Days(i, zwischenWetter);
                }
            return a;
        } catch (JSONException e) {
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
