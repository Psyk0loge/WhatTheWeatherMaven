package org.example;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class DayPreview extends JPanel{
    //Stuff for nextDay Preview
    JPanel forcastDay = new JPanel(new GridLayout(3, 1));
    JLabel Tag = new JLabel("test");
    JLabel iconAusgabe = new JLabel();
    JPanel Temps = new JPanel(new GridLayout(1, 2));
    JLabel minTemp=new JLabel("Temp");
    JLabel maxTemp= new JLabel("Temp");
    public DayPreview() {
        forcastDay.add(Tag);
        forcastDay.add(iconAusgabe);
        Temps.add(minTemp);
        Temps.add(maxTemp);
        forcastDay.add(Temps);
        this.add(forcastDay);
        setVisible(true);

    }
    public void setTag(String tag){
        Tag.setText(tag);
    }
    public void setIconAusgabe(ImageIcon a){
       iconAusgabe.setIcon(a);
    }
    public void setMinTemp(double minimalTemp){
        String temp = Double.toString(minimalTemp);
        this.minTemp.setText(temp);
    }
    public void setMaxTemp(double maximalTemp){
        String temp = Double.toString(maximalTemp);
        this.maxTemp.setText(temp);
    }




}
