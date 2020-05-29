package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.net.URL;

public class DayPreview extends JPanel{
    /**
     * Border to surround the DayPreview panels
     */
    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    /**
     * the panel to preview the day forecast
     */
    JPanel forcastDay = new JPanel(new GridLayout(3, 1));
    /**
     * label to display the workday
     */
    JLabel lbl_Tag = new JLabel("test");
    /**
     * label to display the icon od the previewDay
     */
    JLabel lbl_iconAusgabe = new JLabel();
    /**
     * panel to display the temperatures
     */
    JPanel temps = new JPanel(new GridLayout(2, 1));
    /**
     * Label to s
     */
    JLabel lbl_minTemp = new JLabel("MinTemp:");
    /**
     * label to display the
     */
    JLabel lbl_maxTemp = new JLabel("MaxTemp:");

    /**
     * creates an instance of DayPreview
     */
    public DayPreview() {
        forcastDay.add(lbl_Tag);
        Dimension d = new Dimension(125,125);
        forcastDay.setPreferredSize(d);
        forcastDay.add(lbl_iconAusgabe);
        temps.add(lbl_minTemp);
        temps.add(lbl_maxTemp);
        forcastDay.add(temps);
        forcastDay.setBorder(raisedbevel);
        this.add(forcastDay);
        setVisible(true);
    }

    /**
     * sets the text of lbl_Tag to tag
     * @param tag parameter the we want to set as text for lbl_tag
     */
    public void setTag(String tag){
        lbl_Tag.setText(tag);
    }

    /**
     *
     * @param a the icon of the current weather we want to set
     */
    public void setIconAusgabe(ImageIcon a){
        lbl_iconAusgabe.setIcon(a);
    }

    /**
     * sets the text of the label
     *
     * @param minimalTemp the minimum temperature to display
     */
    public void setMinTemp(double minimalTemp){
        String temp = Double.toString(minimalTemp);
        this.lbl_minTemp.setText("minTemp: "+temp+" Grad");
    }

    /**
     * set the text of lbl_maxTemp to the parameter maximalTemp
     * @param maximalTemp the maximal temperature to display
     */
    public void setMaxTemp(double maximalTemp){
        String temp = Double.toString(maximalTemp);
        this.lbl_maxTemp.setText("maxTemp: "+temp+" Grad");
    }




}
