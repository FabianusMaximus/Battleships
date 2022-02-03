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

    private Translate translater = new Translate("english", "german");

    private Container contentPane;

    private JPanel gamePanel = new JPanel(new GridLayout(10,10));
    private JPanel shipFieldPanel = new JPanel(new GridLayout(5,1));
    private GameLabel[] gameComponents = new GameLabel[100];

    private JLabel[] ships = new JLabel[5];
    private JButton readyButton = new JButton(translater.translated("Ready"));

    public PlaceField(Container contentPane) {
        this.contentPane = contentPane;
        placeField();
        setupShipField();
    }

    public void placeField() {
        for(int i = 0; i < 100; i++) {
            gameComponents[i] = new GameLabel(i);
            gamePanel.add(gameComponents[i]);
        }
        gamePanel.setBounds(50,50,400,400);
        gamePanel.setLayout(new GridLayout(10,10));
        gamePanel.setBorder(new LineBorder(Color.BLACK));
        gamePanel.setOpaque(true);
        gamePanel.setBackground(Color.BLACK);
        contentPane.add(gamePanel);
    }

    private void setupShipField() {
        shipFieldPanel.setBounds(550, 50 , 200, 200);
        shipFieldPanel.setBorder(new LineBorder(Color.BLACK));
        shipFieldPanel.setOpaque(true);
        shipFieldPanel.setBackground(Color.BLACK);
        setupShipFieldPanel();
        readyButton.setBounds(550, 400, 200, 50);
        contentPane.add(shipFieldPanel);
        contentPane.add(readyButton);
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
            }
        });
        return lbl;
    }
}
