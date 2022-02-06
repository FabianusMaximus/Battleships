package com.mustache.gui;

import com.mustache.objects.GameLabel;
import com.mustache.objects.ShipPlaceLabel;
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
    private GameLabel[] gameComponents = new GameLabel[100];

    private JPanel shipPanel = new JPanel(new GridLayout(5,1));
    private ShipPlaceLabel[] shipPlaceLabels;

    private JLabel readyButton = new JLabel(translater.translated("Ready"));
    private JLabel reset = new JLabel(translater.translated("Reset"));

    public PlaceField(Container contentPane) {
        this.contentPane = contentPane;
        placeField();
        shipField();
        placeReadyReset();
    }

    private void placeReadyReset() {
        readyButton.setBounds(740,400,200,50);
        readyButton.setOpaque(true);
        readyButton.setBackground(Color.lightGray);
        readyButton.setBorder(new LineBorder(Color.BLACK));
        readyButton.setVerticalAlignment(SwingConstants.CENTER);
        readyButton.setHorizontalAlignment(SwingConstants.CENTER);
        reset.setBounds(740, 350, 200, 50);
        reset.setOpaque(true);
        reset.setBackground(Color.lightGray);
        reset.setBorder(new LineBorder(Color.BLACK));
        reset.setVerticalAlignment(SwingConstants.CENTER);
        reset.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(readyButton);
        contentPane.add(reset);
        contentPane.revalidate();
    }

    private void placeField() {
        gamePanel.setBounds(50,50,400,400);
        gamePanel.setBorder(new LineBorder(Color.BLACK));
        gamePanel.setOpaque(true);
        gamePanel.setBackground(Color.BLACK);
        contentPane.add(gamePanel);
    }

    private void shipField() {
        shipPanel.setBounds(740, 50, 200, 200);
        shipPanel.setBorder(new LineBorder(Color.BLACK));
        shipPanel.setOpaque(true);
        shipPanel.setBackground(Color.BLACK);
        contentPane.add(shipPanel);
    }

    public void setGameComponents(GameLabel[] lbl) {
        gameComponents = lbl;
        for(int i = 0; i < lbl.length; i++) {
            gamePanel.add(gameComponents[i]);
        }
        gamePanel.revalidate();
    }

    public void setShipComponents(ShipPlaceLabel[] lbl) {
        shipPlaceLabels = lbl;
        for(int i = 0; i < lbl.length; i++) {
            shipPanel.add(shipPlaceLabels[i]);
        }
        shipPanel.revalidate();
    }
}
