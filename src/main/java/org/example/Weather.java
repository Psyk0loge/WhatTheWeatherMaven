package org.example;

import javax.swing.*;

public class Weather {
    /**
     * variable to safes the city of which the user wants to know the weather
     */
    public String city;
    /**
     * provides a short description for the weather
     */
    public String description;
    /**
     * variable to safe the weather
     */
    public ImageIcon img_Weather;
    /**
     * varaible to safe the humidity
     */
    public int humidity;
    /**
     * variable to safe the cloudiness in percent
     */
    public double cloudiness;
    /**
     * safes an the temperatures hourly for the next 46 hours
     */
    public double[] Next48Hours= new double[48];
    /**
     * safes 7 DailyForcastWeathers in an array to provide an forecast for the next 7 days
     */
    public DailyForcastWeather[] Next7Days = new DailyForcastWeather[7];

    /**
     * variable to safe the day when request was made
     */
    public String day;
    /**
     * variable to safe the wind speed
     */
    public double windSpeed;

    /**
     * method to set the windSpeed
     * @param windSpeed is the windspeed that should want to set from the api it is given in meter/seconds, but we want to display
     *  have windSpeed in kilometers per hour
     */
    public void setWindSpeed(double windSpeed) {
      double windSpeedKMH = round(windSpeed *3.6,2);
      this.windSpeed = windSpeedKMH;
    }

    /**
     * return the variable windSpeed
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
     * sets at a given object of DailyForcastWeather at given index x in array
     * @param next7Day
     */
    public void setNext7Days(int x, DailyForcastWeather next7Day) {
        Next7Days[x] = next7Day;
    }

    /**
     * @param humidity humidity in percent
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    /**
     * cloudiness in percent
     * @param cloudiness
     */
    public void setCloudiness(double cloudiness) {
        this.cloudiness = cloudiness;
    }

    /**
     *
     * @param city the city that was requested as String
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * method to safe the temperatures of the next 48 hours in an array
     * @param x index of the array
     * @param forcast temperature in x hours
     */
    public void setNext48Hours(int x,double forcast) {
        this.Next48Hours[x] = forcast;
    }

    /***
     *  sets the temp of the object of weather.
     * Since the temperature given by the api-request is in kelvin
     * the value has to be converted to celsius
     * @param kelvin the current temperature in kelvin
     */
    public double convertTemp(double kelvin){
        double Umrechentemp=kelvin-273.15;
        Umrechentemp = round(Umrechentemp,2);
        return Umrechentemp;
    }


    /**
     * creates an empty weather object
     */
    public Weather() {
    }
    /**
     *
     * @return returns the requested city
     */
    public String getCity() {
        return city;
    }
    /**
     *
     * @return returns a short description of the weather
     */
    public String getDescription() {
        return description;
    }
    /**
     *
     * @return returns an array with seven weather objects
     */
    public Weather[] getNext7Days() {
        return Next7Days;
    }

    /**
     * returns the weather object for a given index
     * @param x index x
     * @return returns the weather object for that index
     */
    public DailyForcastWeather getNextDay(int x){
        return Next7Days[x];
    }

    /**
     *
     * @return return int humidity
     */
    public double getHumidity() {
        return  humidity;
    }

    /**
     * Constructor to create a weather object with
     * @param city the reqested city
     * @param description a short description of the weather
     * @param humidity the humidity
     * @param cloudiness the cloudiness
     */
    public Weather(String city,  String description, int humidity, int cloudiness) {
        this.city = city;
        this.description = description;
        this.humidity = humidity;
        this.cloudiness = cloudiness;
    }

    /**
     * a method to round variables to a given place in the number
     * @param value the variable we want to round
     * @param decimalPoints the place at which we want to round
     * @return the rounded Variable
     */
    public static double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }
    /**
     *
     * @return int cloudiness in percent
     */
    public double getCloudiness() {
        return cloudiness;
    }
}
