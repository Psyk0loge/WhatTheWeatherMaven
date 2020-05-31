package org.example;

/**
 * A Class to safe the daily forcast of weather it therefore provides variables like maxTemp an minTemp
 */
public class DailyForcastWeather extends Weather {
    private double maxTemp;
    private double minTemp;


    /**
     * returns the maximum temperature
     * @return maximum temperature
     */
    public double getMaxTemp() { return maxTemp; }

    /**
     * sets the maximum temperature
     * @param maxTemp
     */
    public void setMaxTemp(double maxTemp) {
        this.maxTemp = convertTemp(maxTemp);
    }

    /**
     * returns the minimum temperature of the object
     * @return
     */
    public double getMinTemp() {
        return convertTemp(minTemp);
    }

    /**
     * sets the minimum temperature to minTemp
     * @param minTemp the temperature the minimal temperature should be set to
     */
    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }





}
