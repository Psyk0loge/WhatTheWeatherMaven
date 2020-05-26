package org.example;

import javax.swing.*;

public class Weather {
    /**
     * Variable to safe the Citry of which we safe the weather
     */
    public String city;
    /**
     * provides a short description for the weather
     */
    public String description;
    /**
     * Variable to safe the Weather
     */
    public ImageIcon img_Weather;
    /**
     * varaible to safe the humidity
     */
    public int humidity;
    /**
     * variable to safe the cloudines in percent
     */
    public double cloudines;
    /**
     * safes an hourly Weather forecast
     */
    public double[] Next48Hours= new double[48];
    /**
     * safes an forecast of the next 4 days in an array
     */
    public DailyForcastWeather[] Next7Days = new DailyForcastWeather[7];

    /**
     * Variable to safe the day
     */
    public String Tag;
    /**
     * Variable to safe the Windspeed
     */
    public double windSpeed;

    /**
     * Method to set the WindSpeed
     * @param windSpeed is the Windspeed that should be set
     */
    public void setWindSpeed(double windSpeed) {
      double windSpeedKMH = windSpeed *3.6;
        this.windSpeed = windSpeedKMH;
    }

    /**
     * get-Method for WindSpeed
     * @return double windSpeed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * normal set method to set the description of a weather
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * set methode for the array which safes the weather for the next 4 days
     * @param next7Day
     */
    public void setNext7Days(int x, DailyForcastWeather next7Day) {
        Next7Days[x] = next7Day;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setCloudines(double cloudines) {
        this.cloudines = cloudines;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNext48Hours(int x,double forcast) {
        this.Next48Hours[x] = forcast;
    }

    /***
     * This Method sets the temp of the object of weather.
     * It also converts the temperature value from kelvin to celsius
     * @param gradKelvin
     */
    public static double umrechnenTemp(double gradKelvin){
        double Umrechentemp=gradKelvin-273.15;
        Umrechentemp= Umrechentemp *100;
        Umrechentemp = Math.round(Umrechentemp);
        Umrechentemp = Umrechentemp/100;
        return Umrechentemp;
    }

    //Konstruktoren
    public Weather() {
    }

    public String getCity() {
        return city;
    }



    public String getDescription() {
        return description;
    }


    public Weather[] getNext7Days() {
        return Next7Days;
    }

    public DailyForcastWeather getNextDay(int x){
        return Next7Days[x];
    }

    public double getHumidity() {
        return  humidity;
    }

    public Weather(String city,  String description, int humidity, int cloudines) {
        this.city = city;
        this.description = description;
        this.humidity = humidity;
        this.cloudines = cloudines;
    }


    public double getCloudines() {
        return cloudines;
    }
}
