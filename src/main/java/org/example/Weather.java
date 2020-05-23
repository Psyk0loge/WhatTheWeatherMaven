package org.example;

public class Weather {
    /**
     * safes the value for temperature
     */
    private double temp;
    /**
     * provides a short description for the weather
     */
    private String description;
    /**
     * safes an hourly forcast for the temperatures
     */
    private double[] temerature;
    /**
     * safes an forecast of the next 4 days in an array
     */
    private Weather[] Next4Days = new Weather[4];
    /**
     * varaible to safe the humidity
     */
    private int humidity;
    /**
     * variable to safe the cloudines in percent
     */
    private int cloudines;

    /**
     * normal set method to set the description of a weather
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * set methode for the array which safes the weather for the next 4 days
     * @param next4Days
     */
    public void setNext4Days(Weather[] next4Days) {
        Next4Days = next4Days;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setCloudines(int cloudines) {
        this.cloudines = cloudines;
    }

    /***
     * This Method sets the temp of the object of weather.
     * It also converts the temperature value from kelvin to celsius
     * @param gradKelvin
     */
    public void setTemp(double gradKelvin){
        double Umrechentemp=gradKelvin-273.15;
        Umrechentemp= Umrechentemp *100;
        Umrechentemp = Math.round(Umrechentemp);
        Umrechentemp = Umrechentemp/100;
        this.temp=Umrechentemp;
    }
    //Konstruktoren
    public Weather() {
    }


    public double getTemp() {
        return temp;
    }

    public String getDescription() {
        return description;
    }
}
