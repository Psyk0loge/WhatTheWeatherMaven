package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GUI extends JFrame{
    //Gui Stuff
    JPanel content = new JPanel(new GridLayout(3,1));
    JPanel Top = new JPanel(new GridLayout(1,2));
    JPanel TopLeft = new JPanel(new GridLayout(4,2));
    JPanel TopRight = new JPanel(new GridLayout(3,1));
    JPanel Center = new JPanel();
    JPanel Bottom = new JPanel(new GridLayout(1,7));

    RequestWeather b = new RequestWeather();



    JLabel LuftfeuchtigkeitsAusgabe = new JLabel("Luftfeuchtigkeit");
    JLabel WindAusgabe = new JLabel("WindAusgabe");
    JLabel Bewölkungsausgabe= new JLabel("Bewölkungsausgabe");
    JLabel kurzBeschreibung = new JLabel();
    JLabel Temp = new JLabel();
    JTextField EingabeStadt = new JTextField();
    JButton startAbfrage = new JButton("Windgeschwindigkeit");
    JLabel TagAusgabe = new JLabel("Tag");
    JLabel Uhrzeit = new JLabel("Uhrzeit");
    JLabel ImageAusgabe = new JLabel();
    JLabel TemperaturVerlauf = new JLabel("Hier kommt später der Graph");
    DayPreview[] tage = new DayPreview[7];



    public GUI(){
        Dimension a = new Dimension(10,10);
        TopLeft.setMaximumSize(a);
        Dimension d = new Dimension(650,650);
        this.setPreferredSize(d);
        TopLeft.add(EingabeStadt);
        TopLeft.add(startAbfrage);
        TopLeft.add(TagAusgabe);
        TopLeft.add(Uhrzeit);
        TopLeft.add(ImageAusgabe);
        TopLeft.add(Temp);
        TopLeft.add(kurzBeschreibung);
        Top.add(TopLeft);


        TopRight.add(WindAusgabe);
        TopRight.add(LuftfeuchtigkeitsAusgabe);
        TopRight.add(Bewölkungsausgabe);
        Top.add(TopRight);
        content.add(Top);

        Center.add(TemperaturVerlauf);
        content.add(Center);

        for(int i =0;i<7;i++){
           tage[i]= new DayPreview();
           Bottom.add(tage[i]);
        }
        content.add(Bottom);
        startAbfrage.addActionListener(btn_startAbfrage);


      this.add(content);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setVisible(true);
      this.pack();




    }
    ActionListener btn_startAbfrage = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            b.setCity(EingabeStadt.getText());
            currentWeather cuWeather = b.verbindung(true);
            EingabeStadt.setText(cuWeather.getCity());
            kurzBeschreibung.setText(cuWeather.getDescription());
            double windSpeed = cuWeather.getWindSpeed();
            String windSpeedS = Double.toString(windSpeed);
            double Luftfeuchte = cuWeather.getHumidity();
            String Luftfeuchtigkeit = Luftfeuchte +" %";
            LuftfeuchtigkeitsAusgabe.setText("Luftfeuchtigkeit: "+Luftfeuchtigkeit);
            WindAusgabe.setText("Windgeschwindigkeit: "+windSpeedS +" KM/H");
            double bewölkung = cuWeather.getCloudines();
            Bewölkungsausgabe.setText("Bewölkung: "+Double.toString(bewölkung)+" %");
            double temp=cuWeather.getTemp();
            Temp.setText("Aktuell: "+Double.toString(temp)+" Grad");
            ImageAusgabe.setIcon(cuWeather.img_Weather);
            for(int i=0;i<7;i++){
                DailyForcastWeather c = cuWeather.getNextDay(i);
                DayPreview d = tage[i];
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
