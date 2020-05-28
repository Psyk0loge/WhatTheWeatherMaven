package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * In this class the user interface is designed
 */

public class GUI extends JFrame{
    /**
     * The content Panel is the main Panel of the JFrame, it devides the screen into 3 sections
     * to display different information 1.(top) current weather Information 2.(center) temperature curve 3. forecast for next 7 days
     */
    JPanel content = new JPanel(new BorderLayout());
    /**
     * Top is the panel that displays the provides place for 2 panels to display the current Weather stats
     */
    JPanel Top = new JPanel(new GridLayout(1,2));
    /**
     * TopLeft is a panel that is added to the panel Top to display the following informations: windSpeed, humidity, cloudines
     * it will be desplayed on the right side
     */
    JPanel TopLeft = new JPanel(new GridLayout(4,2));
    /**
     * TopRight is a panel that is added to the panel Top to display the textField for entering the search city, a JButton to start the search
     * a JTextField to display the weekday and the currentTime, an icon of the weather, and the current temperature in degrees
     */
    JPanel TopRight = new JPanel(new GridLayout(3,1));
    /**
     * center is a JPanel to add the a linechart to represent the temperature
     */
    JPanel Center = new JPanel();
    /**
     * Bottom is a Panel that is seperated into 7 colums in the colums panels of the class DayPreview are added
     */
    JPanel Bottom = new JPanel(new GridLayout(1,7));
    /**
     * an instance of RequestWeather to provide weather data
     */
    RequestWeather b = new RequestWeather();


    /***
     * humidityOutput is a label that will output the humidity
     */
    JLabel lbl_humidityOutput = new JLabel("humidityOutput");
    /***
     * lbl_windSpeedOutput is a label that is overwritten to display current wind speed
     */
    JLabel lbl_windSpeedOutput = new JLabel("windSpeedOutPut");
    /**
     * lbl_cloudinessOutput is a label that is overwritten to display the current cloudiness in percent
     */
    JLabel lbl_cloudinessOutput = new JLabel("cloudinessOutput");
    /**
     * lbl_shortDescription is a label that is overwritten to output a short description of the current weather
     */
    JLabel lbl_shortDescription = new JLabel();
    /**
     * lbl_temp is a label that is overwritten to display the the current temperaturen in celsius
     */
    JLabel lbl_temp = new JLabel();
    /**
     * txt_enterCity is a textField to enter the city of which the user wants to get the weather information for
     */
    JTextField txt_enterCity = new JTextField();
    /**
     * btn_getData is a button that starts the api-request to get the weather data
     * its function is implemented in the inner class btn_getDataListener
     */
    JButton btn_getData = new JButton("start Request");
    /**
     * lbl_weekDay is a label that is overwritten to display the current day
     */
    JLabel lbl_weekDay = new JLabel("Monday");
    /**
     * lbl_currentTime is a label to display the current time
     */
    JLabel lbl_currentTime = new JLabel("Uhrzeit");
    /**
     * lbl_ImageAusgabe is a label to display an icon of the current weather
     */
    JLabel lbl_ImageAusgabe = new JLabel();
    /**
     * is a label to display a graph with an forecast for the temperature of the next 24 hours
     */
    JLabel lbl_temperatureGraph = new JLabel("Hier kommt später der Graph");
    /**
     * days is an arrays that safes 7 objects of the class DayPreview[]
     */
    DayPreview[] days = new DayPreview[7];


    /**
     * Konstruktor of the GUI
     */
    public GUI(){
        System.out.println(lbl_humidityOutput.getHeight());
        System.out.println(lbl_humidityOutput.getWidth());
        lbl_humidityOutput.setSize(467, 20);
        Dimension a = new Dimension(10,10);
        TopLeft.setMaximumSize(a);
        Dimension d = new Dimension(950,650);
        this.setPreferredSize(d);
        TopLeft.add(txt_enterCity);
        TopLeft.add(btn_getData);
        TopLeft.add(lbl_weekDay);
        TopLeft.add(lbl_currentTime);
        TopLeft.add(lbl_ImageAusgabe );
        TopLeft.add(lbl_temp);
        TopLeft.add(lbl_shortDescription);
        Top.add(TopLeft);


        TopRight.add(lbl_windSpeedOutput);
        TopRight.add(lbl_humidityOutput);
        TopRight.add(lbl_cloudinessOutput);
        Top.add(TopRight);
        content.add(Top,BorderLayout.NORTH);

        Center.add(lbl_temperatureGraph);
        content.add(Center);

        for(int i =0;i<7;i++){
            days[i]= new DayPreview();
           Bottom.add(days[i]);
        }
        content.add(Bottom,BorderLayout.SOUTH);
        btn_getData.addActionListener(btn_getDataListener);


      this.add(content);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setVisible(true);
      this.pack();
    }


    ActionListener btn_getDataListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            b.setCity(txt_enterCity.getText());
            currentWeather cuWeather = b.verbindung(true);
            txt_enterCity.setText(cuWeather.getCity());
            lbl_shortDescription.setText(cuWeather.getDescription());
            double windSpeed = cuWeather.getWindSpeed();
            String windSpeedS = Double.toString(windSpeed);
            double Luftfeuchte = cuWeather.getHumidity();
            String Luftfeuchtigkeit = Luftfeuchte +" %";
            lbl_humidityOutput.setText(" humidity: "+Luftfeuchtigkeit);
            lbl_windSpeedOutput.setText("wind speed: "+windSpeedS +" KM/H");
            double bewoelkung = cuWeather.getCloudiness();
            lbl_cloudinessOutput.setText("cloudiness: "+bewoelkung+" %");
            double temp=cuWeather.getTemp();
            lbl_temp.setText(temp+" degrees");
            lbl_ImageAusgabe .setIcon(cuWeather.img_Weather);
            for(int i=0;i<7;i++){
                DayPreview d = days[i];
                d.setMaxTemp(cuWeather.Next7Days[i].getMaxTemp());
                d.setMinTemp(cuWeather.Next7Days[i].getMinTemp());
                d.setIconAusgabe(cuWeather.Next7Days[i].img_Weather);
            }

        }
    };


    public static void main(String[] args) {
        new GUI();
    }
}
