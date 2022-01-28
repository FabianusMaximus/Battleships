package com.mustache.gui;

import com.mustache.main.Controller;
import com.mustache.objects.GameLabel;
import com.mustache.objects.Ship;
import com.mustache.translate.Translate;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class PlaceField {

    private Container contentPane;
    private Translate translater = new Translate("english", "german");

    private JPanel gamePanel = new JPanel(new GridLayout(10,10));
    private JPanel shipFieldPanel = new JPanel(new GridLayout(5,1));
    private GameLabel[] gameComponents = new GameLabel[100];

    private JLabel[] ships = new JLabel[5];
    private JButton readyButton = new JButton(translater.translated("Ready"));

    private Controller controller;

    public PlaceField(Controller con) throws IOException {
        controller = con;
        contentPane = con.getWindow().getContentPane();
        contentPane.removeAll();
        setupShipField();
        con.setWindow(contentPane);
    }

    private void setupPlaceField() {
        for(int i = 0; i < 100; i++) {
            gameComponents[i] = new GameLabel(i, false, false);
        }
    }


    private void setupShipField() {
        shipFieldPanel.setBounds(550, 50 , 200, 200);
        shipFieldPanel.setBorder(new LineBorder(Color.BLACK));
        setupShipFieldPanel();
        readyButton.setBounds(550, 400, 200, 50);
        readyButton.addActionListener(e -> controller.setReady());
        contentPane.add(readyButton);
        contentPane.add(shipFieldPanel);
    }

    private void setupShipFieldPanel() {
        ships[0] = addShipAsLabel("Battleship", 0);
        ships[1] = addShipAsLabel("Cruiser", 1);
        ships[2] = addShipAsLabel("Frigate", 2);
        ships[3] = addShipAsLabel("Frigate", 3);
        ships[4] = addShipAsLabel("Minesweeper", 4);

        for(JLabel lbl : ships) {
            shipFieldPanel.add(lbl);
        }
    }

    private JLabel addShipAsLabel(String name, int id) {
        JLabel lbl = new JLabel(translater.translated(name));
        lbl.setBorder(new LineBorder(Color.BLACK));
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.setShip(id);
            }
        });
        return lbl;
    }
}
