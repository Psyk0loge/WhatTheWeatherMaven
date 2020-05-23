package org.example;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    RequestWeather b = new RequestWeather();
    Weather cuweather =new Weather();

    public GUI(){
        this.setLayout(new BorderLayout());

        /***
         * Top is a JPanel with a Gridlayout that orders the Elements in a row
         * The JPanel is placed at the top of the user Interface
         */
        JPanel Top = new JPanel(new GridLayout(1,4));
        JTextField StadtEingabe = new JTextField("Bitte Stadt eingeben");
        JLabel kurzBeschreibung = new JLabel("fff");
        JLabel datum = new JLabel("fff");
        JLabel tag = new JLabel("fff");
        Top.add(StadtEingabe);
        Top.add(kurzBeschreibung);
        Top.add(datum);
        Top.add(tag);


        JPanel Left = new JPanel(new GridLayout(3,2));
        JLabel Niederschlag = new JLabel("Niederschlag");
        JTextField NiederschlagAusgabe = new JTextField("Niederschlag");
        JLabel Luftfeuchtigkeit = new JLabel("Luftfeuchtigkeit");
        JTextField LuftfeuchtigkeitsAusgabe = new JTextField("Luftfeuchtigkeit");
        JLabel Wind = new JLabel("Wind");
        JTextField WindAusgabe = new JTextField("WindAusgabe");
        Left.add(Niederschlag);
        Left.add(NiederschlagAusgabe);
        Left.add(Luftfeuchtigkeit);
        Left.add(LuftfeuchtigkeitsAusgabe);
        Left.add(Wind);
        Left.add(WindAusgabe);

        /**
         * Center Panel
         */
        JPanel Abfrage = new JPanel(new GridLayout(3,1));
        JLabel Anweisung = new JLabel("Bitte geben Sie eine Stadt ein");
        JTextField EingabeStadt = new JTextField();
        JButton start = new JButton("WetterAbfragen");
        Abfrage.add(Anweisung);
        Abfrage.add(EingabeStadt);
        Abfrage.add(start);





        this.add(Top,BorderLayout.NORTH);
        this.add(Left,BorderLayout.EAST);
        this.add(Abfrage,BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        Dimension preffered = new Dimension(600,600);
        setPreferredSize(preffered);
        setVisible(true);
        this.pack();

        cuweather = b.RequestTheWeather();


    }

    public static void main(String[] args) {
       new GUI();
       // System.out.println(c.a.getDescription());


    }
}
