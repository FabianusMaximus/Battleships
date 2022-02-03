package com.mustache.gui;

import com.mustache.gui.controll.PlaceFieldController;
import com.mustache.main.Controller;
import com.mustache.objects.GameLabel;
import com.mustache.translate.Translate;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

@Getter
@Setter
public class PlaceField {

    private Container contentPane;
    private Controller controller;
    private Translate translater = new Translate("english", "german");

    private JPanel gamePanel = new JPanel(new GridLayout(10,10));
    private JPanel shipFieldPanel = new JPanel(new GridLayout(5,1));
    private GameLabel[] gameComponents = new GameLabel[100];

    private JLabel[] ships = new JLabel[5];
    private JButton readyButton = new JButton(translater.translated("Ready"));

    public PlaceField(Controller con) {
        controller = con;
        setupContentPane();
        setupPlaceField();
        setupShipField();
        con.setWindow(contentPane);
    }

    private void setupContentPane() {
        contentPane = controller.getWindow().getContentPane();
        contentPane.removeAll();
    }

    private Container setupPlaceField() {
        gamePanel.setBounds(50,50,400,400);
        gamePanel.setOpaque(true);
        gamePanel.setBorder(new LineBorder(Color.BLACK));
        gamePanel.setBackground(Color.BLACK);
        contentPane.add(gamePanel);
        return contentPane;
    }

    public void setFieldAsShip(int id) {
        gameComponents[id].setShip(true);
    }

    public void setFieldAsNotPlaceable(int id) {
        gameComponents[id].setPlaceable(false);
    }

    private void setupShipField() {
        shipFieldPanel.setBounds(550, 50 , 200, 200);
        shipFieldPanel.setBorder(new LineBorder(Color.BLACK));
        shipFieldPanel.setOpaque(true);
        shipFieldPanel.setBackground(Color.BLACK);
        setupShipFieldPanel();
        readyButton.setBounds(550, 400, 200, 50);
        readyButton.addActionListener(e -> controller.setReady(true));
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
        lbl.setOpaque(true);
        lbl.setBackground(Color.lightGray);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.setSelectedShip(id);
            }
        });
        return lbl;
    }
}
