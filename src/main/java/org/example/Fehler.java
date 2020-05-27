package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used to display an error when the user
 * requests a city that isn't found in the api databank
 */
public class Fehler extends JFrame {
    /**
     * constructor to create an instance of Fehler
     */
    public Fehler(){
        Dimension b = new Dimension(500,500);
        this.setPreferredSize(b);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JButton a = new JButton("ok");
        JLabel FehlerMeldung = new JLabel("Leider wurde die von ihnen eingegeben Stadt nicht gefunden");
        ActionListener ok = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        };
        a.addActionListener(ok);
        this.add(a,BorderLayout.SOUTH);
        this.add(FehlerMeldung,BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

    }

}
