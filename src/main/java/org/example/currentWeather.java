package org.example;

public class currentWeather extends Weather {
    /**
     * safes the value for temperature
     */
    private double temp;
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

    /**
     * Getter of variable temp
     * @return temp (double)
     */
    public double getTemp() {
        return temp;
    }
}
