package org.example;

public class GUI {
    RequestWeather b = new RequestWeather();
    Weather a=new Weather();

    public GUI(){
        a= b.RequestTheWeather();
    }

    public static void main(String[] args) {
        GUI c = new GUI();
        System.out.println(c.a.getDescription());

    }
}
