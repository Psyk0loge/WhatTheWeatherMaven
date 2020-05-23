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

    //changable lon and lat Strings for dynamic request
    public double Lat;
    public double Lon;

    public RequestWeather(){

    }

    public Weather RequestTheWeather(){
       return verbindung(true);
    }


    /**
     *This Method parses the JSON-File we recieve for our request
     * to safe the Longitude and the Latitude
     * * @param response ist ein Parameter, der sachen macht
     */
    public void parse1(String response)  {

        JSONObject WeatherStats = null;
        try {
            WeatherStats = new JSONObject(response);
            double lon = WeatherStats.getJSONObject("coord").getDouble("lon");
            double lat = WeatherStats.getJSONObject("coord").getDouble("lat");
            Lon = lon;
            Lat = lat;
            System.out.println(lon);
            System.out.println(lat);
            double[] geoData = {lon,lat};
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
    public Weather parse2(String responseBody){
        JSONObject currentWeather = null;
        Weather a = new Weather();
        try {
            currentWeather = new JSONObject(responseBody);
            a.setTemp(currentWeather.getJSONObject("current").getDouble("temp"));
            a.setDescription( currentWeather.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("description"));
            return a;
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
    public Weather verbindung(boolean geoAbfrage){
        String URLString;
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        Weather b = new Weather();
        if(geoAbfrage){
            System.out.println("Von was für einer Stadt wollen Sie das Wetter wissen?");
            String city;
            Scanner scan = new Scanner(System.in);
            city = scan.nextLine();
            scan.close();
            URLString = WebsideForRequest + city + URLmidCode +appid;
        }else{
            URLString = WebsideDataRequest + LatURL+Lat+LonURL+Lon+URLmidDataRequest+appid;;
        }
        try {
            URL url = new URL(URLString);
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
