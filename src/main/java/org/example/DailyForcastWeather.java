package org.example;

public class DailyForcastWeather extends Weather {
    private double maxTemp;
    private double minTemp;

    /**
     * safes an forecast of the next 4 days in an array
     */
    public DailyForcastWeather[] Next7Days = new DailyForcastWeather[7];
    /**
     * set methode for the array which safes the weather for the next 4 days
     * @param next7Day
     */
    public void setNext7Days(int x, DailyForcastWeather next7Day) {
        Next7Days[x] = next7Day;
    }

    public double getMaxTemp() { return maxTemp; }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = convertTemp(maxTemp);
    }

    public double getMinTemp() {
        return convertTemp(minTemp);
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }





}
