package org.example;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class DayPreview extends JPanel{
    //Stuff for nextDay Preview
    JPanel forcastDay = new JPanel(new GridLayout(3, 1));
    JLabel Tag = new JLabel("test");
    JLabel iconAusgabe = new JLabel();
    JPanel Temps = new JPanel(new GridLayout(2, 2));
    JLabel lbl_minTemp = new JLabel("MinTemp:");
    JLabel minTemp=new JLabel("Temp");
    JLabel lbl_maxTemp = new JLabel("MaxTemp:");
    JLabel maxTemp= new JLabel("Temp");
    public DayPreview() {
        forcastDay.add(Tag);
        forcastDay.add(iconAusgabe);
        Temps.add(lbl_minTemp);
        Temps.add(minTemp);
        Temps.add(lbl_maxTemp);
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
        this.minTemp.setText(temp+" Grad");
    }
    public void setMaxTemp(double maximalTemp){
        String temp = Double.toString(maximalTemp);
        this.maxTemp.setText(temp+" Grad");
    }




}
