package com.mustache.gui;

import com.mustache.objects.GameLabel;
import com.mustache.translate.Translate;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

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
    private JButton reset = new JButton(translater.translated("Reset"));

    public PlaceField(Container contentPane) {
        this.contentPane = contentPane;
        placeField();
        setupShipField();
    }

    public void placeField() {
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
        reset.setBounds(550, 350, 200, 50);
        readyButton.setBounds(550, 400, 200, 50);
        contentPane.add(shipFieldPanel);
        contentPane.add(reset);
        contentPane.add(readyButton);
        setupShips();
    }

    private void setupShips() {
        for(int i = 0; i < 5; i++) {
            ships[i] = new JLabel();
            ships[i].setVerticalTextPosition(SwingConstants.CENTER);
            ships[i].setHorizontalAlignment(SwingConstants.CENTER);
            ships[i].setOpaque(true);
            ships[i].setBackground(Color.lightGray);
            ships[i].setBorder(new LineBorder(Color.BLACK));
            shipFieldPanel.add(ships[i]);
        }
    }
}
