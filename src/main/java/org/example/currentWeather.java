package org.example;

public class currentWeather extends Weather {
    /**
     * safes the value for temperature
     */
    private double temp;
    /***
     * This Method sets the temp of the object of weather.
     * It also converts the temperature value from kelvin to celsius
     * @param kelvin
     */
    public void setTemp(double kelvin){
       double convertTemp= convertTemp(kelvin) *100;
        convertTemp=round(convertTemp,2);
        convertTemp = convertTemp/100;
        this.temp=convertTemp;
    }

    /**
     * Getter of variable temp
     * @return temp (double)
     */
    public double getTemp() {
        return temp;
    }
}
